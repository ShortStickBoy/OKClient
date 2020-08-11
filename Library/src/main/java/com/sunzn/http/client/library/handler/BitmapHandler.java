package com.sunzn.http.client.library.handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sunzn.http.client.library.base.BaseHandler;

import okhttp3.Response;

public abstract class BitmapHandler extends BaseHandler<Bitmap> {

    @Override
    public Bitmap parseResponse(Response response) {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

}
