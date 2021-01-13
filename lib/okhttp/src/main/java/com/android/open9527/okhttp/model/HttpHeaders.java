package com.android.open9527.okhttp.model;


import com.android.open9527.okhttp.HttpConfig;

import java.util.HashMap;
import java.util.Set;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public final class HttpHeaders {

    /** 请求头存放集合 */
    private HashMap<String, String> mHeaders = HttpConfig.getInstance().getHeaders();

    public void put(String key, String value) {
        if (key != null && value != null) {
            if (mHeaders == HttpConfig.getInstance().getHeaders()) {
                mHeaders = new HashMap<>(mHeaders);
            }
            mHeaders.put(key, value);
        }
    }

    public void remove(String key) {
        if (key != null) {
            if (mHeaders == HttpConfig.getInstance().getHeaders()) {
                mHeaders = new HashMap<>(mHeaders);
            }
            mHeaders.remove(key);
        }
    }

    public String get(String key) {
        return mHeaders.get(key);
    }

    public boolean isEmpty() {
        return mHeaders == null || mHeaders.isEmpty();
    }

    public Set<String> getNames() {
        return mHeaders.keySet();
    }

    public HashMap<String, String> getHeaders() {
        return mHeaders;
    }
}