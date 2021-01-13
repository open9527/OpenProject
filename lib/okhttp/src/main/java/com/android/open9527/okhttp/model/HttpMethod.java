package com.android.open9527.okhttp.model;

import androidx.annotation.NonNull;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public enum HttpMethod {

    /** GET 请求 */
    GET("GET"),

    /** Post 请求 */
    POST("POST"),

    /** Head 请求 */
    HEAD("HEAD"),

    /** Delete 请求 */
    DELETE("DELETE"),

    /** Put 请求 */
    PUT("PUT"),

    /** Patch 请求 */
    PATCH("PATCH");

    /** 请求方式 */
    private final String mMethod;

    HttpMethod(String method) {
        this.mMethod = method;
    }

    @NonNull
    @Override
    public String toString() {
        return mMethod;
    }
}