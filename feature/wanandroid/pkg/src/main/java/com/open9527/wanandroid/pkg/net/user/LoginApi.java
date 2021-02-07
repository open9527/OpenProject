package com.open9527.wanandroid.pkg.net.user;

import com.android.open9527.okhttp.config.IRequestApi;

/**
 * @author open_9527
 * Create at 2021/1/14
 **/
public class LoginApi implements IRequestApi {
    @Override
    public String getApi() {
        return "user/login";
    }

    private String username;
    private String password;

    public LoginApi setUserName(String username) {
        this.username = username;
        return this;
    }

    public LoginApi setPassWord(String password) {
        this.password = password;
        return this;
    }
}
