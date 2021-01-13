package com.android.open9527.okhttp;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public final class HttpLog {

    /**
     * 打印分割线
     */
    public static void print() {
        print("----------------------------------------");
    }

    /**
     * 打印日志
     */
    public static void print(String log) {
        if (HttpConfig.getInstance().isLogEnabled()) {
            HttpConfig.getInstance().getLogStrategy().print(log);
        }
    }

    /**
     * 打印 Json
     */
    public static void json(String json) {
        if (HttpConfig.getInstance().isLogEnabled()) {
            HttpConfig.getInstance().getLogStrategy().json(json);
        }
    }

    /**
     * 打印键值对
     */
    public static void print(String key, String value) {
        if (HttpConfig.getInstance().isLogEnabled()) {
            HttpConfig.getInstance().getLogStrategy().print(key, value);
        }
    }

    /**
     * 打印异常
     */
    public static void print(Throwable throwable) {
        if (HttpConfig.getInstance().isLogEnabled()) {
            HttpConfig.getInstance().getLogStrategy().print(throwable);
        }
    }

    /**
     * 打印堆栈
     */
    public static void print(StackTraceElement[] stackTrace) {
        if (HttpConfig.getInstance().isLogEnabled()) {
            HttpConfig.getInstance().getLogStrategy().print(stackTrace);
        }
    }
}