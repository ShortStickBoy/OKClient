package com.sunzn.http.client.library;

import android.support.annotation.NonNull;

import com.sunzn.http.client.library.base.BaseClient;
import com.sunzn.http.client.library.base.BaseHandler;
import com.sunzn.http.client.library.builder.GetBuilder;
import com.sunzn.http.client.library.builder.HeadBuilder;
import com.sunzn.http.client.library.builder.MethodBuilder;
import com.sunzn.http.client.library.builder.PostFormBuilder;
import com.sunzn.http.client.library.builder.PostStringBuilder;
import com.sunzn.http.client.library.request.RequestCall;
import com.sunzn.http.client.library.utils.UIExecutor;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static com.sunzn.http.client.library.utils.Method.DELETE;
import static com.sunzn.http.client.library.utils.Method.PATCH;
import static com.sunzn.http.client.library.utils.Method.PUT;

public class OKClient extends BaseClient {

    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private volatile static OKClient mInstance;

    private OkHttpClient mOkHttpClient;
    private UIExecutor mUIExecutor;

    public static OKClient getInstance() {
        return initClient(null);
    }

    public static OKClient initClient(OkHttpClient client) {
        if (mInstance == null) {
            synchronized (OKClient.class) {
                if (mInstance == null) {
                    mInstance = new OKClient(client);
                }
            }
        }
        return mInstance;
    }

    private OKClient(OkHttpClient client) {
        mOkHttpClient = client == null ? new OkHttpClient() : client;
        mUIExecutor = UIExecutor.get();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public Executor getDelivery() {
        return mUIExecutor.initExecutor();
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostFormBuilder post() {
        return new PostFormBuilder();
    }

    public static PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public static HeadBuilder head() {
        return new HeadBuilder();
    }

    public static MethodBuilder put() {
        return new MethodBuilder(PUT);
    }

    public static MethodBuilder delete() {
        return new MethodBuilder(DELETE);
    }

    public static MethodBuilder patch() {
        return new MethodBuilder(PATCH);
    }

    public void execute(RequestCall requestCall, BaseHandler listener) {

        final BaseHandler finalListener = listener;

        requestCall.getCall().enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                execFailure(e, finalListener);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {

                try {
                    if (!finalListener.validateResponse(response)) {
                        execFailure(new IOException("response code is : " + response.code()), finalListener);
                        return;
                    }

                    Object o = finalListener.parseResponse(response);

                    execSuccess(response.code(), response.headers(), o, finalListener);
                } catch (Exception e) {
                    execFailure(e, finalListener);
                } finally {
                    if (response.body() != null) response.body().close();
                }

            }
        });
    }

    private void execSuccess(final int code, final Headers headers, final Object o, final BaseHandler listener) {
        mUIExecutor.execute(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess(code, headers, o);
            }
        });
    }

    private void execFailure(final Exception e, final BaseHandler listener) {
        mUIExecutor.execute(new Runnable() {
            @Override
            public void run() {
                listener.onFailure(e);
            }
        });
    }

}
