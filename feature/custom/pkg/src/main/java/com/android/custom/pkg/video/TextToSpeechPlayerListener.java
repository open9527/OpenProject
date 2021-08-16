package com.android.custom.pkg.video;

/**
 * @author open_9527
 * Create at 2021/8/13
 **/

public interface TextToSpeechPlayerListener {
    // 准备完成
    default void prepared() {
    }

    // 开始播放
    default void start() {
    }

    // 暂停
    default void pause() {
    }

    // 停止播放
    default void stop() {
    }

    //播放错误监听
    default void onError() {

    }

    //播放完成监听
    default void onCompletion() {
    }

}
