package com.android.open9527.okhttp.config;


import com.android.open9527.okhttp.model.HttpHeaders;
import com.android.open9527.okhttp.model.HttpParams;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public interface IRequestInterceptor {

    /**
     * 开始请求之前调用
     *
     * @param url     请求地址
     * @param tag     请求标记
     * @param params  请求参数
     * @param headers 请求头参数
     */
    void intercept(String url, String tag, HttpParams params, HttpHeaders headers);
}