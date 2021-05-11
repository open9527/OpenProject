package com.android.open9527.glide;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class GlideHeadInterceptor implements Interceptor {

    public static final Map<String, OnProgressListener> LISTENER_MAP = new HashMap<>();

    public static void addListener(String url, OnProgressListener listener) {
        LISTENER_MAP.put(url, listener);
    }

    public static void removeListener(String url) {
        LISTENER_MAP.remove(url);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        //TODO:配置head
        builder.addHeader("key", "value");
        Response response = chain.proceed(builder.build());
        String url = response.request().url().toString();
        ResponseBody body = response.body();
        return response.newBuilder().body(new ProgressResponseBody(url, body)).build();
    }
}