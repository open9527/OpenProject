package com.android.video.export.controller;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.open9527.video.common.controller.BaseVideoController;
import com.android.open9527.video.common.player.VideoViewManager;
import com.android.video.export.component.DebugInfoView;


/**
 * TikTok
 */

public class TikTokController extends BaseVideoController {

    public TikTokController(@NonNull Context context) {
        super(context);
    }

    public TikTokController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TikTokController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        //显示调试信息
        if (VideoViewManager.getConfig().mIsEnableLog) {
            addControlComponent(new DebugInfoView(getContext()));
        }
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public boolean showNetWarning() {
        //不显示移动网络播放警告
        return false;
    }
}
