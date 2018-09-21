package com.sunzn.http.client.library.handler;

import com.sunzn.http.client.library.base.BaseHandler;

import okhttp3.Response;

public abstract class ByteHandler extends BaseHandler<byte[]> {

    @Override
    public byte[] parseResponse(Response response) throws Exception {
        return response.body().bytes();
    }

}
