package com.sunzn.http.client.library.builder;

import com.sunzn.http.client.library.base.BaseBuilder;
import com.sunzn.http.client.library.request.PostStringRequest;
import com.sunzn.http.client.library.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;

public class PostStringBuilder extends BaseBuilder<PostStringBuilder> {

    private String content;
    private MediaType mediaType;

    public PostStringBuilder content(String content) {
        this.content = content;
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public PostStringBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public PostStringBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public PostStringBuilder addHeader(String key, String value) {
        if (this.headers == null) {
            this.headers = new LinkedHashMap<>();
        }
        headers.put(key, value);
        return this;
    }

    @Override
    public PostStringBuilder addHeaders(Map<String, String> headers) {
        if (this.headers == null) {
            this.headers = new LinkedHashMap<>();
        }
        if (headers != null && !headers.isEmpty()) {
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                this.headers.put(key, headers.get(key));
            }
        }
        return this;
    }

    @Override
    public PostStringBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostStringRequest(url, tag, headers, params, content, mediaType).build();
    }

}
