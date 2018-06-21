package com.sunzn.http.client.sample;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

/**
 * Created by sunzn on 2017/6/23.
 */

public class ParamManager {

    public static String getAdvertParam() {

        AdvertParam param = CookieManager.getAdvertParam(1, 0, 1, 1);

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Column.class, "name", "mapper");

        Log.e("OKHTTP3",JSON.toJSONString(param, filter));

        return JSON.toJSONString(param, filter);

    }

}
