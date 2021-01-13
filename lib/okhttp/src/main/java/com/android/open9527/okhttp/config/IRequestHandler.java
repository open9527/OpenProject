package com.android.open9527.okhttp.config;

import androidx.lifecycle.LifecycleOwner;

import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public interface IRequestHandler {

    /**
     * 请求成功时回调
     *
     * @param lifecycle     有生命周期的对象（例如 Activity、Fragment）
     * @param response      响应对象
     * @param type          解析类型
     * @return              返回结果
     *
     * @throws Exception    如果抛出则回调失败
     */
    Object requestSucceed(LifecycleOwner lifecycle, Response response, Type type) throws Exception;

    /**
     * 请求失败
     *
     * @param lifecycle     有生命周期的对象（例如 Activity、Fragment）
     * @param e             错误对象
     * @return              错误对象
     */
    Exception requestFail(LifecycleOwner lifecycle, Exception e);
}