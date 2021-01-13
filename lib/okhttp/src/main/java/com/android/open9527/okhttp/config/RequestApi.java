package com.android.open9527.okhttp.config;

import com.android.open9527.okhttp.annotation.HttpIgnore;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public final class RequestApi implements IRequestApi {

    /**
     * 接口地址
     */
    @HttpIgnore
    private String mApi;

    public RequestApi(String api) {
        mApi = api;
    }

    @Override
    public String getApi() {
        return mApi;
    }

    @Override
    public String toString() {
        return mApi;
    }
}