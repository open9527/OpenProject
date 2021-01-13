package com.android.open9527.okhttp.config;

import com.android.open9527.okhttp.annotation.HttpIgnore;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public final class RequestServer implements IRequestServer {

    /**
     * 主机地址
     */
    @HttpIgnore
    private String mHost;

    /**
     * 接口路径
     */
    @HttpIgnore
    private String mPath;

    public RequestServer(String host) {
        this(host, "");
    }

    public RequestServer(String host, String path) {
        mHost = host;
        mPath = path;
    }

    @Override
    public String getHost() {
        return mHost;
    }

    @Override
    public String getPath() {
        return mPath;
    }

    @Override
    public String toString() {
        return mHost + mPath;
    }
}