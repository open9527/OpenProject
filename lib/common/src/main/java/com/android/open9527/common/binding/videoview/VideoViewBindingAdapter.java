package com.android.open9527.common.binding.videoview;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.widget.VideoView;

import androidx.databinding.BindingAdapter;

public class VideoViewBindingAdapter {


    @BindingAdapter(value = {
            "bindVideoViewUrl",
            "bindVideoViewLooping",
            "bindVideoViewAutoPlay",
            "bindVideoViewListener",
    },
            requireAll = false)
    public static void setBindingVideoViewAdapter(VideoView videoView, String url, boolean looping, boolean autoPlay, IVideoView iVideoView) {
        if (videoView == null) return;
        if (TextUtils.isEmpty(url)) return;
        videoView.setVideoPath(url);
        videoView.setOnPreparedListener(player -> {
            if (autoPlay) {
                player.start();

            }
            player.setOnInfoListener((mp, what, extra) -> {
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                    if (iVideoView != null) {
                        iVideoView.start();
                    }
                videoView.setBackgroundColor(Color.TRANSPARENT);
                return true;
            });
            player.setLooping(looping);
        });
        videoView.setOnCompletionListener(mp -> {
            if (iVideoView != null) {
                iVideoView.end();
            }
        });
    }

}
