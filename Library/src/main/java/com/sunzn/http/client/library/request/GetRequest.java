package com.sunzn.http.client.library.request;

import com.sunzn.http.client.library.base.BaseRequest;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

public class GetRequest extends BaseRequest {

    public GetRequest(String url, Map<String, String> headers, Map<String, String> params) {
        super(url, headers, params);
    }

    @Override
    public RequestBody buildRequestBody() {
        return null;
    }

    @Override
    public Request buildRequest(RequestBody body) {
        return builder.get().build();
    }

}
