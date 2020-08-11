package com.sunzn.http.client.sample;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.sunzn.http.client.library.OKClient;
import com.sunzn.http.client.library.handler.BitmapHandler;
import com.sunzn.http.client.library.handler.FileHandler;
import com.sunzn.http.client.library.handler.TextHandler;
import com.sunzn.utils.library.DeviceUtils;

import java.io.File;
import java.net.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import okhttp3.Headers;
import okhttp3.MediaType;

import static com.sunzn.http.client.sample.Config.App;
import static com.sunzn.http.client.sample.Config.AppKey;
import static com.sunzn.http.client.sample.Config.AppSecret;
import static com.sunzn.http.client.sample.Config.Referer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static final String TAG = "OKClient";

    private String token;

    private LinearLayout holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        holder = findViewById(R.id.container);

        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button0:
                getAuth();
                break;
            case R.id.button1:
                get();
                break;
            case R.id.button2:
                post(token);
                break;
            case R.id.button3:
                postString();
                break;
            case R.id.button4:
                PermissionGen.needPermission(this, 100, PERMISSIONS);
                break;
            case R.id.button5:
                postLogin();
                break;
            case R.id.button6:
                downFile();
                break;
            case R.id.button7:
                cookie();
                break;
            case R.id.button8:
                clearCookie();
                break;
            case R.id.button9:
                put();
                break;
            case R.id.button10:
                image();
                break;
        }
    }

    private void getAuth() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("API-Version", "V5");
        headers.put("AppKey", AppKey);
        headers.put("ClientType", App);
        headers.put("Referer", Referer);
        headers.put("AppSecret", AppSecret);

        Map<String, String> params = new LinkedHashMap<>();
        params.put("appid", Config.CLIENT_ID);
        params.put("secret", Config.CLIENT_SECRET);
        params.put("claimedid", DeviceUtils.getDeviceId(this, "SN1234567889"));
        OKClient.get().url("https://xyz.cnki.net/cnkioauth/api/auth/access/token.html").headers(headers).params(params).build().execute(new TextHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, String response) {
                Log.e(TAG, response);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, e.toString());
            }
        });
    }

    private void get() {

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("API-Version", "V5");
        headers.put("AppKey", AppKey);
        headers.put("ClientType", App);
        headers.put("Referer", Referer);
        headers.put("AppSecret", AppSecret);

        Map<String, String> params = new LinkedHashMap<>();
        params.put("client_id", Config.CLIENT_ID);
        params.put("client_secret", Config.CLIENT_SECRET);
        params.put("client_sn", DeviceUtils.getDeviceId(this, "SN1234567889"));
        params.put("response_type", Config.RESPONSE_TYPE);
        params.put("grant_type", Config.GRANT_TYPE_OAUTH);
        params.put("redirect_uri", Config.REDIRECT_URI);
        params.put("scope", Config.SCOPE);

        OKClient.get().tag(this).url(WebService.getAuthUrl()).headers(headers).params(params).build().proxy(Proxy.NO_PROXY).execute(new TextHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, String response) {
                Log.e(TAG, headers.toString());
                JSONObject object = JSON.parseObject(response);
                token = object.getString("AccessToken");
                Log.e(TAG, response);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, e.toString());
            }
        });
    }

    private void post(String token) {

        Log.e(TAG, token);

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("API-Version", "V5");
        headers.put("AppKey", AppKey);
        headers.put("ClientType", App);
        headers.put("Referer", Referer);
        headers.put("AppSecret", AppSecret);

        Map<String, String> params = new LinkedHashMap<>();
        params.put("client_id", Config.CLIENT_ID);
        params.put("client_secret", Config.CLIENT_SECRET);
        params.put("response_type", Config.RESPONSE_TYPE);
        params.put("grant_type", Config.GRANT_TYPE_OAUTH);
        params.put("redirect_uri", Config.REDIRECT_URI);
        params.put("code", token);

        OKClient.post().url(WebService.getTokenUrl()).headers(headers).params(params).build().execute(new TextHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, String response) {
                Log.e(TAG, response);
                JSONObject object = JSON.parseObject(response);
                String AccessToken = object.getString("access_token");
                String TokenType = object.getString("token_type");
                int ExpiresIn = object.getInteger("expires_in");
                String RefreshToken = object.getString("refresh_token");
                String Scope = object.getString("scope");
                OAuthBean bean = new OAuthBean(AccessToken, ExpiresIn, RefreshToken, Scope, TokenType);
                OAuthUtil.putOAuth(MainActivity.this, bean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void postString() {

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("API-Version", "V5");
        headers.put("AppKey", AppKey);
        headers.put("ClientType", App);
        headers.put("Referer", Referer);
        headers.put("AppSecret", AppSecret);
        headers.put("Authorization", OAuthUtil.getAccessToken(this));

        OKClient.postString().url(WebService.getAdvertListUrl()).headers(headers).content(ParamManager.getAdvertParam()).mediaType(MediaType.parse("application/json; charset=utf-8")).build().execute(new TextHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, String response) {
                Log.e(TAG, response);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, e.toString());
            }
        });
    }

    private void postFile() {

        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        Log.e(TAG, file.getAbsolutePath());
        Log.e(TAG, file.getName());
        if (!file.exists()) {
            Toast.makeText(MainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "multipart/form-data");
        headers.put("X-Forwarded-Identity", "863077027318298");
//        headers.put("X-Forwarded-Identity", "fbee044071864f8599359ef9db399958");
        headers.put("Authorization", OAuthUtil.getAccessToken(this));

        OKClient.post()
                .addFile("multiFile", "messenger_01.png", file)
                .url(WebService.getUploadUrl("face", "png"))
                .headers(headers)
                .build()
                .execute(new TextHandler() {

                    @Override
                    public void inProgress(float progress, long total) {
                        Log.e(TAG, "progress = " + progress);
                    }

                    @Override
                    public void onSuccess(int statusCode, Headers headers, String response) {
                        Log.e(TAG, response);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.e(TAG, e.toString());
                    }
                });
    }

    private void postLogin() {

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("API-Version", "V5");
        headers.put("AppKey", AppKey);
        headers.put("ClientType", App);
        headers.put("Referer", Referer);
        headers.put("AppSecret", AppSecret);
        headers.put("Authorization", OAuthUtil.getAccessToken(this));

        Map<String, String> params = new LinkedHashMap<>();
//        params.put("username", "test001@cnki.net");
//        params.put("password", "cnkicnki");
        params.put("username", "cnki0220");
        params.put("password", "cnki02");
        params.put("usertype", "1");

        OKClient.post()
                .url(WebService.getLoginUrl())
                .headers(headers)
                .params(params)
                .build()
                .connTimeOut(20000)
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext())))
                .execute(new TextHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, String response) {
                        Log.e(TAG, response);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.e(TAG, e.toString());
                    }
                });

    }


    private void downFile() {

//        String url = "http://wap.cnki.net/apk/Android/CNKIClient_Version5.5.6.apk";
        String url = "http://bianke.cnki.net/pulpit/api/GetFileApi/GetBlogPic?fileId=201812200939571372931.jpg";

//        OKClient.get().url(url).build().execute(new FileHandler(Environment.getExternalStorageDirectory().getAbsolutePath(), "CNKI.APK") {
        OKClient.get().url(url).build().execute(new FileHandler(Environment.getExternalStorageDirectory().getAbsolutePath(), "CNKI.jpg") {

            @Override
            public void inProgress(float progress, long total) {
                Log.e(TAG, "Progress = " + progress + "   Total = " + total);
            }

            @Override
            public void onSuccess(int statusCode, Headers headers, File response) {
                Log.e(TAG, "onSuccess :" + response.getAbsolutePath());
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "onError :" + e.getMessage());
            }
        });

    }


    private void cookie() {

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("API-Version", "V5");
        headers.put("AppKey", AppKey);
        headers.put("ClientType", App);
        headers.put("Referer", Referer);
        headers.put("AppSecret", AppSecret);
        headers.put("Authorization", OAuthUtil.getAccessToken(this));

        OKClient.post()
                .url("http://z.bianke.cnki.net/record/getconsumeinfoclient?page=1")
                .headers(headers)
                .build()
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext())))
                .execute(new TextHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, String response) {
                        Log.e(TAG, response);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.e(TAG, e.toString());
                    }
                });
    }


    private void clearCookie() {
        PersistentCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        cookieJar.clear();
        Log.e(TAG, "Cookie 清除成功");
    }

    private void put() {

        OKClient.put().url("http://www.baidu.com").requestBody("测试").build().execute(new TextHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, String response) {
                Log.e(TAG, response);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, e.toString());
            }
        });

    }

    private void image() {
        OKClient.get().url("http://bianke.cnki.net/pulpit/api/GetFileApi/GetBlogPic?fileId=201812200939571372931.jpg").build().execute(new BitmapHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, Bitmap response) {
                holder.setBackground(new BitmapDrawable(getResources(), response));
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Keep
    @PermissionSuccess(requestCode = 100)
    public void onPermissionSuccess() {
        postFile();
    }

    @Keep
    @PermissionFail(requestCode = 100)
    private void onPermissionFail() {
        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OKClient.getInstance().cancel(this);
    }

}
