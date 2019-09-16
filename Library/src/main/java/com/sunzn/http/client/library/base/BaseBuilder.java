package com.sunzn.http.client.library.base;

import com.sunzn.http.client.library.request.RequestCall;

import java.util.Map;

public abstract class BaseBuilder<T extends BaseBuilder> {

    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;

    public abstract T url(String url);

    public abstract T tag(Object tag);

    public abstract T addHeader(String key, String value);

    public abstract T headers(Map<String, String> headers);

    public abstract RequestCall build();

}
