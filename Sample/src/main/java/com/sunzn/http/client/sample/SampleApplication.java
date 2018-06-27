package com.sunzn.http.client.sample;

import android.app.Application;

import com.sunzn.http.client.library.OKClient;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        initOKClient();
    }

    private void initOKClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(40000L, TimeUnit.MILLISECONDS)
                .proxy(Proxy.NO_PROXY)
                .build();
        OKClient.initClient(client);
    }

}
