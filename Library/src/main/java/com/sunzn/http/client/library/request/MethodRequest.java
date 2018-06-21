package com.sunzn.http.client.library.request;

import android.text.TextUtils;

import com.sunzn.http.client.library.base.BaseRequest;
import com.sunzn.http.client.library.utils.Exceptions;
import com.sunzn.http.client.library.utils.Method;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;

public class MethodRequest extends BaseRequest {

    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private RequestBody requestBody;
    private String content;
    private String method;

    public MethodRequest(String url, Map<String, String> headers, Map<String, String> params, String method, String content, RequestBody requestBody) {
        super(url, headers, params);
        this.requestBody = requestBody;
        this.content = content;
        this.method = method;
    }

    @Override
    public RequestBody buildRequestBody() {
        if (requestBody == null && TextUtils.isEmpty(content) && HttpMethod.requiresRequestBody(method)) {
            Exceptions.illegalArgument("requestBody and content can not be null in method:" + method);
        }
        if (requestBody == null && !TextUtils.isEmpty(content)) {
            requestBody = RequestBody.create(MEDIA_TYPE_PLAIN, content);
        }
        return requestBody;
    }

    @Override
    public Request buildRequest(RequestBody body) {
        switch (method) {
            case Method.PUT:
                builder.put(body);
                break;
            case Method.DELETE:
                if (body == null) {
                    builder.delete();
                } else {
                    builder.delete(body);
                }
                break;
            case Method.HEAD:
                builder.head();
                break;
            case Method.PATCH:
                builder.patch(body);
                break;
        }
        return builder.build();
    }

}
