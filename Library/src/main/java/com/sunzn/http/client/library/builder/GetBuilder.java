package com.sunzn.http.client.library.builder;

import android.net.Uri;
import android.text.TextUtils;

import com.sunzn.http.client.library.base.BaseBuilder;
import com.sunzn.http.client.library.request.GetRequest;
import com.sunzn.http.client.library.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GetBuilder extends BaseBuilder<GetBuilder> implements HasParams<GetBuilder> {

    @Override
    public GetBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public GetBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public GetBuilder addHeader(String key, String value) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, value);
        return this;
    }

    @Override
    public GetBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public RequestCall build() {
        if (params != null && !params.isEmpty()) {
            url = buildUrl(url, params);
        }
        return new GetRequest(url, tag, headers, params).build();
    }

    private String buildUrl(String url, Map<String, String> params) {
        if (!TextUtils.isEmpty(url) && params != null && !params.isEmpty()) {
            Uri.Builder builder = Uri.parse(url).buildUpon();
            Set<String> keys = params.keySet();
            for (String key : keys) {
                builder.appendQueryParameter(key, params.get(key));
            }
            return builder.build().toString();
        } else {
            return url;
        }
    }

    @Override
    public GetBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public GetBuilder addParams(String key, String value) {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        params.put(key, value);
        return this;
    }

}
