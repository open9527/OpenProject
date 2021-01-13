package com.android.open9527.common.net.server;

import com.android.open9527.okhttp.config.IRequestServer;

/**
 * @author open_9527
 * Create at 2021/1/8
 * <p>
 * eg:测试接口
 **/
public class TestServer implements IRequestServer {
    @Override
    public String getHost() {
        return "https://www.wanandroid.com/";
    }

    @Override
    public String getPath() {
        return "";
    }
}
