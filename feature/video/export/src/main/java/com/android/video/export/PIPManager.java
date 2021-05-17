package com.android.video.export;

import android.view.View;

import com.android.open9527.video.common.player.VideoView;
import com.android.open9527.video.common.player.VideoViewManager;
import com.android.video.export.controller.FloatController;


/**
 * 悬浮播放
 */

public class PIPManager {
    public static final String TAG = "PIPManager";
    private static PIPManager instance;
    private VideoView mVideoView;
    private FloatView mFloatView;
    private FloatController mFloatController;
    private boolean mIsShowing;
    private int mPlayingPosition = -1;
    private Class mActClass;


    private PIPManager() {
        mVideoView = new VideoView(com.blankj.utilcode.util.Utils.getApp());
        VideoViewManager.instance().add(mVideoView, TAG);
        mFloatController = new FloatController(com.blankj.utilcode.util.Utils.getApp());
        mFloatView = new FloatView(com.blankj.utilcode.util.Utils.getApp(), 0, 0);
    }

    public static PIPManager getInstance() {
        if (instance == null) {
            synchronized (PIPManager.class) {
                if (instance == null) {
                    instance = new PIPManager();
                }
            }
        }
        return instance;
    }

    public void startFloatWindow() {
        if (mIsShowing) return;
        Utils.removeViewFormParent(mVideoView);
        mVideoView.setVideoController(mFloatController);
        mFloatController.setPlayState(mVideoView.getCurrentPlayState());
        mFloatController.setPlayerState(mVideoView.getCurrentPlayerState());
        mFloatView.addView(mVideoView);
        mFloatView.addToWindow();
        mIsShowing = true;
    }

    public void stopFloatWindow() {
        if (!mIsShowing) return;
        mFloatView.removeFromWindow();
        Utils.removeViewFormParent(mVideoView);
        mIsShowing = false;
    }

    public void setPlayingPosition(int position) {
        this.mPlayingPosition = position;
    }

    public int getPlayingPosition() {
        return mPlayingPosition;
    }

    public void pause() {
        if (mIsShowing) return;
        mVideoView.pause();
    }

    public void resume() {
        if (mIsShowing) return;
        mVideoView.resume();
    }

    public void reset() {
        if (mIsShowing) return;
        Utils.removeViewFormParent(mVideoView);
        mVideoView.release();
        mVideoView.setVideoController(null);
        mPlayingPosition = -1;
        mActClass = null;
    }

    public boolean onBackPress() {
        return !mIsShowing && mVideoView.onBackPressed();
    }

    public boolean isStartFloatWindow() {
        return mIsShowing;
    }

    /**
     * 显示悬浮窗
     */
    public void setFloatViewVisible() {
        if (mIsShowing) {
            mVideoView.resume();
            mFloatView.setVisibility(View.VISIBLE);
        }
    }

    public void setActClass(Class cls) {
        this.mActClass = cls;
    }

    public Class getActClass() {
        return mActClass;
    }

}
