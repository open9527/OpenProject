package com.android.video.export.player;


import android.content.Context;

import com.android.open9527.video.exo.ExoMediaPlayer;
import com.google.android.exoplayer2.source.MediaSource;

/**
 * 自定义ExoMediaPlayer，目前扩展了诸如边播边存，以及可以直接设置Exo自己的MediaSource。
 */
public class CustomExoMediaPlayer extends ExoMediaPlayer {

    public CustomExoMediaPlayer(Context context) {
        super(context);
    }

    public void setDataSource(MediaSource dataSource) {
        mMediaSource = dataSource;
    }
}
