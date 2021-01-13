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

    private static volatile OkHttpClient okHttpClient;

    public final static OkHttpClient getClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .proxy(Proxy.NO_PROXY)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return okHttpClient;
    }

}
