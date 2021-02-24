package com.android.open9527.common.action;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public interface HandlerAction {

    Handler HANDLER = new Handler(Looper.getMainLooper());

    /**
     * 获取 Handler
     */
    default Handler getHandler() {
        return HANDLER;
    }

    /**
     * 延迟执行
     */
    default boolean post(Runnable r) {
        return postDelayed(r, 0);
    }

    /**
     * 延迟一段时间执行
     */
    default boolean postDelayed(Runnable r, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return postAtTime(r, SystemClock.uptimeMillis() + delayMillis);
    }

    /**
     * 在指定的时间执行
     */
    default boolean postAtTime(Runnable r, long uptimeMillis) {
        // 发送和这个 Activity 相关的消息回调
        return HANDLER.postAtTime(r, this, uptimeMillis);
    }

    /**
     * 移除单个消息回调
     */
    default void removeCallbacks(Runnable r) {
        HANDLER.removeCallbacks(r);
    }

    /**
     * 移除全部消息回调
     */
    default void removeCallbacks() {
        HANDLER.removeCallbacksAndMessages(this);
    }
}