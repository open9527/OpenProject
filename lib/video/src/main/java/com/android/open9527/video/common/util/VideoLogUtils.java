package com.android.open9527.video.common.util;

import android.util.Log;

import com.android.open9527.video.common.player.VideoViewManager;


/**
 * 日志类
 */

public final class VideoLogUtils {

    private VideoLogUtils() {
    }

    private static final String TAG = "VideoPlayerLog";

    private static boolean isDebug = VideoViewManager.getConfig().mIsEnableLog;


    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }

    public static void setDebug(boolean isDebug) {
        VideoLogUtils.isDebug = isDebug;
    }
}
