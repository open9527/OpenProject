package com.android.custom.pkg.video.exo;

import android.view.ViewGroup;

/**
 * @author open_9527
 * Create at 2021/5/11
 **/

public interface IPlayTarget {

    ViewGroup getOwner();

    //活跃状态 视频可播放
    void onActive();

    //非活跃状态，暂停它
    void inActive();


    boolean isPlaying();
}
