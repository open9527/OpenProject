package com.android.open9527.okhttp.listener;

import okhttp3.Call;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public interface OnHttpListener<T> {

    /**
     * 请求开始
     */
    default void onStart(Call call) {}

    /**
     * 请求成功
     */
    default  void onSucceed(T result){}

    /**
     * 请求出错
     */
    default  void onFail(Exception e){}

    /**
     * 请求结束
     */
    default void onEnd(Call call) {}
}