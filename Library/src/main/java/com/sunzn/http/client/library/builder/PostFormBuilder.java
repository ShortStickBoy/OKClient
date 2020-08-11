package com.sunzn.http.client.library.builder;

import androidx.annotation.NonNull;

import com.sunzn.http.client.library.base.BaseBuilder;
import com.sunzn.http.client.library.request.PostFormRequest;
import com.sunzn.http.client.library.request.RequestCall;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PostFormBuilder extends BaseBuilder<PostFormBuilder> implements HasParams<PostFormBuilder> {

    private List<FileInput> files = new ArrayList<>();

    @Override
    public PostFormBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public PostFormBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public PostFormBuilder addHeader(String key, String value) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, value);
        return this;
    }

    @Override
    public PostFormBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostFormRequest(url, tag, headers, params, files).build();
    }

    @Override
    public PostFormBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public PostFormBuilder addParams(String key, String value) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, value);
        return this;
    }

    public PostFormBuilder files(List<FileInput> inputs) {
        this.files.addAll(inputs);
        return this;
    }

    public PostFormBuilder files(Map<String, File> files) {
        for (String name : files.keySet()) {
            File file = files.get(name);
            if (file != null && file.exists()) {
                String fileName = file.getName();
                this.files.add(new FileInput(name, fileName, file));
            }
        }
        return this;
    }

    public PostFormBuilder files(String name, Map<String, File> files) {
        for (String filename : files.keySet()) {
            this.files.add(new FileInput(name, filename, files.get(filename)));
        }
        return this;
    }

    public PostFormBuilder addFile(String name, String filename, File file) {
        this.files.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput {
        public String name;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file) {
            this.name = name;
            this.filename = filename;
            this.file = file;
        }

        @NonNull
        @Override
        public String toString() {
            return "FileInput{" + "name='" + name + '\'' + ", filename='" + filename + '\'' + ", file=" + file + '}';
        }
    }

}
