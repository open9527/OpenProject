package com.android.custom.pkg.video;


import android.media.MediaPlayer;

/**
 * @author open_9527
 * Create at 2021/8/13
 **/
public interface VoicePlayerListener {
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

    //重置
    default void reset() {
    }


    //播放错误监听
    default void onError(MediaPlayer mediaPlayer, int what, int extra) {
    }

    //播放完成监听
    default void onCompletion(MediaPlayer mediaPlayer) {
    }

    //网络缓冲监听
    default void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
    }

    //进度调整监听
    default void onSeekComplete(MediaPlayer mediaPlayer) {
    }

    //时实播放进度
    default void onSeekBarProgress(int progress) {
    }
}
