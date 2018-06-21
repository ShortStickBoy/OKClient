package com.sunzn.http.client.sample;

/**
 * Created by sunzn on 2016/6/30.
 */
public class Config {

    public static final String AppKey = "c08c83771edddc8c22c919ddbdb8e008";

    public static final String AppSecret = "62608e08adc29a8d6dbc9754e659f125";

    public static final String CLIENT_ID = "c08c83771edddc8c22c919ddbdb8e008";

    public static final String CLIENT_SECRET = "62608e08adc29a8d6dbc9754e659f125";

    public static final String RESPONSE_TYPE = "code";

    public static final String GRANT_TYPE_OAUTH = "authorization_code";

    public static final String GRANT_TYPE_REFRESH = "refresh_token";

    public static final String REDIRECT_URI = "http://www.cnki.com.cn";

    public static final String SCOPE = "basic";

    public static final String App = "app";

    public static final String Referer = "http://xyz.cnki.net";

    public static final String PLAT_FORM = "sjzw-android-phone";

    public static class WeiXin {
        /**
         * ╔════════════════════════════════════════════════════════════════════════════════════════
         * ║ 名称：微信 APP_ID
         * ╚════════════════════════════════════════════════════════════════════════════════════════
         */
        public static final String APP_ID = "wxad7b4dcfe250bb7c";

        /**
         * ╔════════════════════════════════════════════════════════════════════════════════════════
         * ║ 名称：微信 MCH_ID
         * ╟────────────────────────────────────────────────────────────────────────────────────────
         * ║ 说明：微信支付的 PartnerID
         * ╚════════════════════════════════════════════════════════════════════════════════════════
         */
        public static final String MCH_ID = "1246483901";
    }

    public static class Sina {
        /**
         * ╔════════════════════════════════════════════════════════════════════════════════════════
         * ║ 名称：新浪 APP_KEY
         * ╚════════════════════════════════════════════════════════════════════════════════════════
         */
        public static final String APP_KEY = "2518114568";
    }

    public static class QQ {
        /**
         * ╔════════════════════════════════════════════════════════════════════════════════════════
         * ║ 名称：QQ APP_ID
         * ╟────────────────────────────────────────────────────────────────────────────────────────
         * ║ 说明：关联 AndroidManifest 下的 AuthActivity
         * ╚════════════════════════════════════════════════════════════════════════════════════════
         */
        public static final String APP_ID = "1101163373";
    }

}
