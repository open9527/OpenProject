package com.android.open9527.common.binding.videoview;

import android.widget.VideoView;

import androidx.databinding.BindingAdapter;

public class VideoViewBindingAdapter {


    @BindingAdapter(value = {
            "bindVideoViewUrl",
            "bindVideoViewLooping",
            "bindVideoViewAutoPlay",
    },
            requireAll = false)
    public static void setBindingVideoViewAdapter(VideoView videoView, String url, boolean looping, boolean autoPlay) {
        if (videoView == null) return;

        videoView.setVideoPath(url);
        videoView.setOnPreparedListener(player -> {
            if (autoPlay) {
                player.start();
            }
            player.setLooping(looping);
        });
        videoView.setOnCompletionListener(mp -> {
            //TODO: 播放结束操作

        });
    }

}
