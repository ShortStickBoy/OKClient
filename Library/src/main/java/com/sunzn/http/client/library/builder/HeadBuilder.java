package com.sunzn.http.client.library.builder;

import com.sunzn.http.client.library.request.MethodRequest;
import com.sunzn.http.client.library.request.RequestCall;
import com.sunzn.http.client.library.utils.Method;

public class HeadBuilder extends GetBuilder {

    @Override
    public RequestCall build() {
        return new MethodRequest(url, tag, headers, params, Method.HEAD, null, null).build();
    }

}
