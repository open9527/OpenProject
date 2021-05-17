package com.android.video.export.controller;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.open9527.video.common.controller.GestureVideoController;
import com.android.open9527.video.common.player.VideoViewManager;
import com.android.open9527.video.ui.component.CompleteView;
import com.android.open9527.video.ui.component.ErrorView;
import com.android.video.export.component.DebugInfoView;
import com.android.video.export.component.PipControlView;


/**
 * 悬浮播放控制器
 */
public class FloatController extends GestureVideoController {

    public FloatController(@NonNull Context context) {
        super(context);
    }

    public FloatController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        super.initView();
        //显示调试信息
        if (VideoViewManager.getConfig().mIsEnableLog) {
            addControlComponent(new DebugInfoView(getContext()));
        }
        addControlComponent(new CompleteView(getContext()));
        addControlComponent(new ErrorView(getContext()));
        addControlComponent(new PipControlView(getContext()));
    }
}
