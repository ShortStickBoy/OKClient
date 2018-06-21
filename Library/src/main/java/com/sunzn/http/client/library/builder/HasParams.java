package com.sunzn.http.client.library.builder;

import com.sunzn.http.client.library.base.BaseBuilder;

import java.util.Map;

public interface HasParams<T extends BaseBuilder<T>> {

    T params(Map<String, String> params);

    T addParams(String key, String value);

}
