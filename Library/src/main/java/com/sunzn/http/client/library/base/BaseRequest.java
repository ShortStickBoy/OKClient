package com.sunzn.http.client.library.base;

import com.sunzn.http.client.library.request.RequestCall;
import com.sunzn.http.client.library.utils.Exceptions;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class BaseRequest {

    public String url;
    public Object tag;
    public Map<String, String> headers;
    public Map<String, String> params;
    public Request.Builder builder = new Request.Builder();

    public BaseRequest(String url, Object tag, Map<String, String> headers, Map<String, String> params) {
        this.url = url;
        this.tag = tag;
        this.headers = headers;
        this.params = params;

        if (url == null) Exceptions.illegalArgument("url can not be null.");

        initBuilder();
    }

    private void initBuilder() {
        builder.url(url).tag(tag);
        addHeaders();
    }

    private void addHeaders() {
        Headers.Builder header = new Headers.Builder();
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                header.add(key, headers.get(key));
            }
            builder.headers(header.build());
        }
    }

    public RequestCall build() {
        return new RequestCall(this);
    }

    public abstract RequestBody buildRequestBody();

    public abstract Request buildRequest(RequestBody body);

    protected RequestBody wrapRequestBody(RequestBody requestBody, final BaseHandler callback) {
        return requestBody;
    }

    public Request generateRequest(BaseHandler callback) {
        RequestBody requestBody = buildRequestBody();
        RequestBody wrapRequestBody = wrapRequestBody(requestBody, callback);
        return buildRequest(wrapRequestBody);
    }

}
