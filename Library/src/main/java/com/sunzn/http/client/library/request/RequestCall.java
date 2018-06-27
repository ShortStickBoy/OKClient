package com.sunzn.http.client.library.request;

import com.sunzn.http.client.library.OKClient;
import com.sunzn.http.client.library.base.BaseHandler;
import com.sunzn.http.client.library.base.BaseRequest;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class RequestCall {

    private BaseRequest baseRequest;
    private Request request;
    private Call call;

    private long readTimeOut;
    private long writeTimeOut;
    private long connTimeOut;

    private CookieJar cookieJar;
    private Proxy proxy;

    public RequestCall(BaseRequest request) {
        this.baseRequest = request;
    }

    public RequestCall readTimeOut(long readTimeOut) {
        this.readTimeOut = readTimeOut;
        return this;
    }

    public RequestCall writeTimeOut(long writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
        return this;
    }

    public RequestCall connTimeOut(long connTimeOut) {
        this.connTimeOut = connTimeOut;
        return this;
    }

    public RequestCall cookieJar(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
        return this;
    }

    public RequestCall proxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public Call getCall() {
        return call;
    }

    public Request getRequest() {
        return request;
    }

    public void execute(BaseHandler handler) {
        buildCall(handler);
        OKClient.getInstance().execute(this, handler);
    }

    private void buildCall(BaseHandler callback) {
        request = generateRequest(callback);
        if (isParamChange()) {

            OkHttpClient.Builder builder = OKClient.getInstance().getOkHttpClient().newBuilder();

            if (connTimeOut <= 0) {
                connTimeOut = OKClient.DEFAULT_MILLISECONDS;
            }
            builder = builder.connectTimeout(connTimeOut, TimeUnit.MILLISECONDS);

            if (readTimeOut <= 0) {
                readTimeOut = OKClient.DEFAULT_MILLISECONDS;
            }
            builder = builder.readTimeout(readTimeOut, TimeUnit.MILLISECONDS);

            if (writeTimeOut <= 0) {
                writeTimeOut = OKClient.DEFAULT_MILLISECONDS;
            }
            builder = builder.writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS);

            if (cookieJar != null) {
                builder = builder.cookieJar(cookieJar);
            }

            if (proxy != null) {
                builder = builder.proxy(proxy);
            }

            OkHttpClient client = builder.build();
            call = client.newCall(request);
        } else {
            call = OKClient.getInstance().getOkHttpClient().newCall(request);
        }
    }

    private boolean isParamChange() {
        return connTimeOut > 0 || readTimeOut > 0 || writeTimeOut > 0 || cookieJar != null || proxy != null;
    }

    private Request generateRequest(BaseHandler handler) {
        return baseRequest.generateRequest(handler);
    }

}
