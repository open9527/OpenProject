package com.open9527.webview.bridge.impl.vo;

import java.io.Serializable;

public class WebTokenQueryParam implements Serializable {
    private String token;

    public WebTokenQueryParam(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
