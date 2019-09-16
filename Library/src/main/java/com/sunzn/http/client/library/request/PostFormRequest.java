package com.sunzn.http.client.library.request;

import com.sunzn.http.client.library.OKClient;
import com.sunzn.http.client.library.base.BaseHandler;
import com.sunzn.http.client.library.base.BaseRequest;
import com.sunzn.http.client.library.builder.PostFormBuilder;

import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostFormRequest extends BaseRequest {

    private List<PostFormBuilder.FileInput> files;

    public PostFormRequest(String url, Object tag, Map<String, String> headers, Map<String, String> params, List<PostFormBuilder.FileInput> files) {
        super(url, tag, headers, params);
        this.files = files;
    }

    @Override
    public RequestBody buildRequestBody() {
        if (files == null || files.isEmpty()) {
            FormBody.Builder builder = new FormBody.Builder();
            addParams(builder);
            return builder.build();
        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            addParams(builder);
            for (int i = 0; i < files.size(); i++) {
                PostFormBuilder.FileInput fileInput = files.get(i);
                RequestBody fileBody = RequestBody.create(fileInput.file, MediaType.parse(guessMimeType(fileInput.filename)));
                builder.addFormDataPart(fileInput.name, fileInput.filename, fileBody);
            }
            return builder.build();
        }
    }

    @Override
    protected RequestBody wrapRequestBody(RequestBody requestBody, final BaseHandler callback) {
        if (callback == null) return requestBody;
        return new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
            @Override
            public void onProgress(final long bytesWritten, final long contentLength) {
                OKClient.getInstance().getDelivery().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.inProgress(bytesWritten * 1.0F / contentLength, contentLength);
                    }
                });
            }
        });
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = null;
        try {
            contentTypeFor = fileNameMap.getContentTypeFor(URLEncoder.encode(path, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    private void addParams(MultipartBody.Builder builder) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""), RequestBody.create(params.get(key), null));
            }
        }
    }

    private void addParams(FormBody.Builder builder) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
    }

    @Override
    public Request buildRequest(RequestBody body) {
        return builder.post(body).build();
    }

}
