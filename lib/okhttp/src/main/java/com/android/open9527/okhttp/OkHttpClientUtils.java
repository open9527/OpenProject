package com.android.open9527.okhttp;


import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author open_9527
 * Create at 2021/1/12
 * <p>
 * eg:
 **/
public final class OkHttpClientUtils {

    private OkHttpClientUtils() {
    }

    private static class OkHttpClientInstance {
        private static final OkHttpClient INSTANCE = new OkHttpClient.Builder()
                .proxy(Proxy.NO_PROXY)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpClient getInstance() {
        return OkHttpClientInstance.INSTANCE;
    }

}
