package com.android.open9527.okhttp.config;

import android.text.TextUtils;
import android.util.Log;

import com.android.open9527.okhttp.HttpConfig;
import com.android.open9527.okhttp.OkHttpUtils;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public final class LogStrategy implements ILogStrategy {

    @Override
    public void print(String log) {
        Log.d(HttpConfig.getInstance().getLogTag(), log != null ? log : "null");
    }

    @Override
    public void json(String json) {
        String text = ILogStrategy.formatJson(json);
        if (TextUtils.isEmpty(text)) {
            return;
        }

        // 打印 Json 数据最好换一行再打印会好看一点
        text = " \n" + text;

        int segmentSize = 3 * 1024;
        long length = text.length();
        if (length <= segmentSize) {
            // 长度小于等于限制直接打印
            print(text);
            return;
        }

        // 循环分段打印日志
        while (text.length() > segmentSize) {
            String logContent = text.substring(0, segmentSize);
            text = text.replace(logContent, "");
            print(logContent);
        }

        // 打印剩余日志
        print(text);
    }

    @Override
    public void print(String key, String value) {
        print(key + " = " + value);
    }

    @Override
    public void print(Throwable throwable) {
        Log.e(HttpConfig.getInstance().getLogTag(), throwable.getMessage(), throwable);
    }

    @Override
    public void print(StackTraceElement[] stackTrace) {
        for (StackTraceElement element : stackTrace) {
            // 获取代码行数
            int lineNumber = element.getLineNumber();
            // 获取类的全路径
            String className = element.getClassName();
            if (lineNumber <= 0 || className.startsWith(OkHttpUtils.class.getPackage().getName())) {
                continue;
            }
            print("RequestClass = (" + element.getFileName() + ":" + lineNumber + ") ");
            break;
        }
    }
}