package com.sunzn.http.client.sample;

import android.content.Context;

import com.sunzn.utils.library.AppUtils;
import com.sunzn.utils.library.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by sunzn on 2016/6/6.
 */
public class WebService {

    private static final String ROOT_OAUTH_URL = WebRoot.getGlobalRoot() + "oauthserver";
    private static final String ROOT_API_URL = WebRoot.getGlobalRoot() + "resource";

    /**
     * ╔════════════════════════════════════════════════════════════════════════════════════════════
     * ║ 名称：文件上传接口
     * ╟────────────────────────────────────────────────────────────────────────────────────────────
     * ║ 方式：POST
     * ╚════════════════════════════════════════════════════════════════════════════════════════════
     */
    public static String getUploadUrl(String path, String ext) {
//        return "http://192.168.107.112:8340/scholar/upload/" + path + "/" + ext + "/submit.action";
        return "http://192.168.21.120:8080/upload/" + path + "/" + ext + "/submit.action";
    }

    /**
     * ╔════════════════════════════════════════════════════════════════════════════════════════════
     * ║ 名称：开屏广告列表接口
     * ╟────────────────────────────────────────────────────────────────────────────────────────────
     * ║ 方式：POST
     * ╚════════════════════════════════════════════════════════════════════════════════════════════
     */
    public static String getAdvertListUrl() {
        return ROOT_API_URL + "/api/manualpush/SlidsList";
    }

    /**
     * ╔════════════════════════════════════════════════════════════════════════════════════════════
     * ║ 名称：临时授权
     * ╟────────────────────────────────────────────────────────────────────────────────────────────
     * ║ 方式：GET
     * ╚════════════════════════════════════════════════════════════════════════════════════════════
     */
    public static String getAuthUrl() {
        return ROOT_OAUTH_URL + "/oauth/auth";
    }

    /**
     * ╔════════════════════════════════════════════════════════════════════════════════════════════
     * ║ 名称：正式授权
     * ╟────────────────────────────────────────────────────────────────────────────────────────────
     * ║ 方式：POST
     * ╚════════════════════════════════════════════════════════════════════════════════════════════
     */
    public static String getTokenUrl() {
        return ROOT_OAUTH_URL + "/oauth/token";
    }

    /**
     * ╔════════════════════════════════════════════════════════════════════════════════════════════
     * ║ 名称：登录
     * ╟────────────────────────────────────────────────────────────────────────────────────────────
     * ║ 方式：POST
     * ╚════════════════════════════════════════════════════════════════════════════════════════════
     */
    public static String getLoginUrl() {
        return ROOT_API_URL + "/api/account/login";
    }

}
