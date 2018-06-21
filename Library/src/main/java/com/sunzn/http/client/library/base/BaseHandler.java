package com.sunzn.http.client.library.base;

import okhttp3.Headers;
import okhttp3.Response;

public abstract class BaseHandler<T> {

    public boolean validateResponse(Response response) {
        return response.isSuccessful();
    }

    public void inProgress(float progress, long total) {
        // TODO
    }

    public abstract T parseResponse(Response response) throws Exception;

    public abstract void onSuccess(int statusCode, Headers headers, T response);

    public abstract void onFailure(Exception e);

}
