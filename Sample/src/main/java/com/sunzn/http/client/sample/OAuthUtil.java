package com.sunzn.http.client.sample;

import android.content.Context;
import android.content.SharedPreferences;

import com.sunzn.utils.library.SPUtils;

/**
 * Created by sunzn on 2016/6/30.
 */
public class OAuthUtil {

    private static final String OAUTH = "OAuth";

    public static void putOAuth(Context context, OAuthBean bean) {
        if (context != null && bean != null) {
            SharedPreferences preferences = context.getSharedPreferences(OAUTH, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("AccessToken", bean.getAccessToken());
            editor.putString("TokenType", bean.getTokenType());
            editor.putInt("ExpiresIn", bean.getExpiresIn());
            editor.putString("RefreshToken", bean.getRefreshToken());
            editor.putString("Scope", bean.getScope());
            editor.putLong("RefreshTime", System.currentTimeMillis());
            editor.apply();
        }
    }

    public static String getAccessToken(Context context) {
        return "Bearer " + SPUtils.getString(context, OAUTH, "AccessToken", "").trim();
    }

}
