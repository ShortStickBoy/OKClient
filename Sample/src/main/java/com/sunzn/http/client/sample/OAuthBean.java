package com.sunzn.http.client.sample;

/**
 * Created by sunzn on 2016/6/30.
 */
public class OAuthBean {

    private String AccessToken;
    private String TokenType;
    private int ExpiresIn;
    private String RefreshToken;
    private String Scope;

    public OAuthBean(String accessToken, int expiresIn, String refreshToken, String scope, String tokenType) {
        AccessToken = accessToken;
        ExpiresIn = expiresIn;
        RefreshToken = refreshToken;
        Scope = scope;
        TokenType = tokenType;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public int getExpiresIn() {
        return ExpiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        ExpiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return RefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        RefreshToken = refreshToken;
    }

    public String getScope() {
        return Scope;
    }

    public void setScope(String scope) {
        Scope = scope;
    }

    public String getTokenType() {
        return TokenType;
    }

    public void setTokenType(String tokenType) {
        TokenType = tokenType;
    }
}
