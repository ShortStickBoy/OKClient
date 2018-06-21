package com.sunzn.http.client.library.handler;

import com.sunzn.http.client.library.base.BaseHandler;

import java.io.IOException;

import okhttp3.Response;

public abstract class TextHandler extends BaseHandler<String> {

    @Override
    public String parseResponse(Response response) throws IOException {
        return response.body().string();
    }

}
