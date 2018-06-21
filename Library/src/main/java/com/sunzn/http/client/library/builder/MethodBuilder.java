package com.sunzn.http.client.library.builder;

import com.sunzn.http.client.library.base.BaseBuilder;
import com.sunzn.http.client.library.request.MethodRequest;
import com.sunzn.http.client.library.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class MethodBuilder extends BaseBuilder<MethodBuilder> {

    private RequestBody requestBody;
    private String method;
    private String content;

    public MethodBuilder(String method) {
        this.method = method;
    }

    @Override
    public MethodBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public MethodBuilder addHeader(String key, String value) {
        if (this.headers == null) {
            this.headers = new LinkedHashMap<>();
        }
        this.headers.put(key, value);
        return this;
    }

    @Override
    public MethodBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public MethodBuilder requestBody(String content) {
        this.content = content;
        return this;
    }

    public MethodBuilder requestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    @Override
    public RequestCall build() {
        return new MethodRequest(url, headers, params, method, content, requestBody).build();
    }

}
