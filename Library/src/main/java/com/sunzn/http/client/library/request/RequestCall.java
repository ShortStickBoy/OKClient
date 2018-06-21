package com.sunzn.http.client.library.request;

import com.sunzn.http.client.library.OKClient;
import com.sunzn.http.client.library.base.BaseRequest;
import com.sunzn.http.client.library.base.BaseHandler;

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

    public Call getCall() {
        return call;
    }

    public Request getRequest() {
        return request;
    }

    public void execute(BaseHandler callback) {
        buildCall(callback);
        OKClient.getInstance().execute(this, callback);
    }

    private void buildCall(BaseHandler callback) {
        request = generateRequest(callback);
        if (readTimeOut > 0 || writeTimeOut > 0 || connTimeOut > 0 || cookieJar != null) {
            readTimeOut = readTimeOut > 0 ? readTimeOut : OKClient.DEFAULT_MILLISECONDS;
            writeTimeOut = writeTimeOut > 0 ? writeTimeOut : OKClient.DEFAULT_MILLISECONDS;
            connTimeOut = connTimeOut > 0 ? connTimeOut : OKClient.DEFAULT_MILLISECONDS;

            OkHttpClient client = OKClient.getInstance().getOkHttpClient().newBuilder()
                    .readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                    .writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
                    .connectTimeout(connTimeOut, TimeUnit.MILLISECONDS)
                    .cookieJar(cookieJar)
                    .build();

            call = client.newCall(request);
        } else {
            call = OKClient.getInstance().getOkHttpClient().newCall(request);
        }
    }

    private Request generateRequest(BaseHandler callback) {
        return baseRequest.generateRequest(callback);
    }

}
