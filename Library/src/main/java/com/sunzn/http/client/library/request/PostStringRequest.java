package com.sunzn.http.client.library.request;

import com.sunzn.http.client.library.base.BaseRequest;
import com.sunzn.http.client.library.utils.Exceptions;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostStringRequest extends BaseRequest {

    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private String content;
    private MediaType mediaType;

    public PostStringRequest(String url, Map<String, String> headers, Map<String, String> params, String content, MediaType mediaType) {
        super(url, headers, params);
        this.content = content;
        this.mediaType = mediaType;

        if (this.content == null) {
            Exceptions.illegalArgument("the content can not be null !");
        }

        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }
    }

    @Override
    public RequestBody buildRequestBody() {
        return RequestBody.create(mediaType, content);
    }

    @Override
    public Request buildRequest(RequestBody body) {
        return builder.post(body).build();
    }

}
