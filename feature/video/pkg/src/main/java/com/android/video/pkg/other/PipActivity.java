package com.android.video.pkg.other;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.net.glide.ImageLoadUtils;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.permission.OnPermissionCallback;
import com.android.open9527.permission.Permission;
import com.android.open9527.permission.PermissionsManage;
import com.android.open9527.video.common.player.VideoView;
import com.android.open9527.video.common.player.VideoViewManager;
import com.android.open9527.video.ui.StandardVideoController;
import com.android.open9527.video.ui.component.CompleteView;
import com.android.open9527.video.ui.component.ErrorView;
import com.android.open9527.video.ui.component.GestureView;
import com.android.open9527.video.ui.component.PrepareView;
import com.android.open9527.video.ui.component.TitleView;
import com.android.video.export.PIPManager;
import com.android.video.export.component.DefinitionControlView;
import com.android.video.pkg.BR;
import com.android.video.pkg.R;
import com.android.video.pkg.databinding.PipActivityBinding;
import com.blankj.utilcode.util.ToastUtils;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/5/17
 **/
public class PipActivity extends BaseCommonActivity implements DefinitionControlView.OnRateSwitchListener {

    private PipViewModel mViewModel;
    private PIPManager mPIPManager;
    private DefinitionControlView mDefinitionControlView;
    private VideoView mVideoView;
    private StandardVideoController mController;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(PipViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.pip_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        FrameLayout playerContainer = ((PipActivityBinding) getBinding()).playerContainer;
        mPIPManager = PIPManager.getInstance();
        mVideoView = getVideoViewManager().get(PIPManager.TAG);
        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_ORIGINAL);
        mVideoView.setLooping(true);
        mController = new StandardVideoController(this);
        addControlComponents();
        //配置清晰度选择
        LinkedHashMap<String, String> videos = new LinkedHashMap<>();
        videos.put("标清", mViewModel.valueVideoUrl.get());
        videos.put("高清", mViewModel.valueVideoUrl.get());
        videos.put("超清", mViewModel.valueVideoUrl.get());
        videos.put("4K", "http://vodcnd.cp59.ott.cibntv.net/Act-ss-m3u8-fhd/bab97ed9041c482f994a1b5b81fddda0/905497gfd.m3u8");

        mDefinitionControlView.setData(videos);
        mVideoView.setVideoController(mController);

        if (mPIPManager.isStartFloatWindow()) {
            mPIPManager.stopFloatWindow();
            mController.setPlayerState(mVideoView.getCurrentPlayerState());
            mController.setPlayState(mVideoView.getCurrentPlayState());
        } else {
            mPIPManager.setActClass(PipActivity.class);
            ImageView thumb = mController.findViewById(R.id.thumb);
            ImageLoadUtils.loadImage(ImageLoadConfig.with(thumb)
                    .setUrl(mViewModel.valueVideoCoverUrl.get()));
            mVideoView.setUrl(videos.get("标清"));//默认播放标清
            mVideoView.start();
        }
        playerContainer.addView(mVideoView);

    }

    private void addControlComponents() {
        CompleteView completeView = new CompleteView(this);
        ErrorView errorView = new ErrorView(this);
        PrepareView prepareView = new PrepareView(this);
        prepareView.setClickStart();
        TitleView titleView = new TitleView(this);
        titleView.setTitle(mViewModel.valueVideoTitle.get());
        mDefinitionControlView = new DefinitionControlView(this);
        mDefinitionControlView.setOnRateSwitchListener(this);
        GestureView gestureView = new GestureView(this);
        mController.addControlComponent(completeView, errorView, prepareView, titleView, mDefinitionControlView, gestureView);
    }


    @Override
    public void onRateChange(String url) {
        mVideoView.setUrl(url);
        mVideoView.replay(false);
    }

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            onBackPressed();
        };

        public View.OnClickListener speedClick = v -> {
            if (mVideoView.getSpeed() == 1.0f) {
                mVideoView.setSpeed(1.5f);
                mViewModel.valueSpeedTitle.set("1.5f");
                showLongToast("1.5f");
            } else if (mVideoView.getSpeed() == 1.5f) {
                mVideoView.setSpeed(2.0f);
                mViewModel.valueSpeedTitle.set("2.0f");
                showLongToast("2.0f");
            } else if (mVideoView.getSpeed() == 2.0f) {
                mVideoView.setSpeed(1.0f);
                mViewModel.valueSpeedTitle.set("1.0f");
                showLongToast("1.0f");
            }
        };
        public View.OnClickListener screenScaleTypeClick = v -> {
            if (mVideoView.getScreenScaleType() == VideoView.SCREEN_SCALE_DEFAULT) {
                mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_16_9);
                mViewModel.valueScreenScaleType.set("16:9");

            } else if (mVideoView.getScreenScaleType() == VideoView.SCREEN_SCALE_16_9) {
                mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_4_3);
                mViewModel.valueScreenScaleType.set("4:3");

            } else if (mVideoView.getScreenScaleType() == VideoView.SCREEN_SCALE_4_3) {
                mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_ORIGINAL);
                mViewModel.valueScreenScaleType.set("原始大小");

            } else if (mVideoView.getScreenScaleType() == VideoView.SCREEN_SCALE_ORIGINAL) {
                mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_MATCH_PARENT);
                mViewModel.valueScreenScaleType.set("填充");

            } else if (mVideoView.getScreenScaleType() == VideoView.SCREEN_SCALE_MATCH_PARENT) {
                mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
                mViewModel.valueScreenScaleType.set("居中剪裁");

            } else if (mVideoView.getScreenScaleType() == VideoView.SCREEN_SCALE_CENTER_CROP) {
                mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT);
                mViewModel.valueScreenScaleType.set("默认");
            }
        };

        public View.OnClickListener doScreenShotClick = v -> {
            mViewModel.valueShotBitmap.set(mVideoView.doScreenShot());
        };


        public View.OnClickListener pipClick = PipActivity.this::startFloatWindow;
    }


    @Override
    public void onBackPressed() {
        if (mPIPManager.onBackPress()) return;
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPIPManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPIPManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPIPManager.reset();
    }


    public void startFloatWindow(View view) {
        PermissionsManage.with(this)
                .permission(Permission.SYSTEM_ALERT_WINDOW)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        mPIPManager.startFloatWindow();
                        mPIPManager.resume();
                        finish();
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        ToastUtils.showShort("获取悬浮窗权限失败，请手动授予权限");
                    }
                });
    }

    protected VideoViewManager getVideoViewManager() {
        return VideoViewManager.instance();
    }
}
