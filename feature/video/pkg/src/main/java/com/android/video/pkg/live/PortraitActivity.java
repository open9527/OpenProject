package com.android.video.pkg.live;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.net.glide.ImageLoadUtils;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.video.common.player.VideoView;
import com.android.open9527.video.common.player.VideoViewManager;
import com.android.open9527.video.ui.StandardVideoController;
import com.android.open9527.video.ui.component.PrepareView;
import com.android.video.export.component.DebugInfoView;
import com.android.video.pkg.BR;
import com.android.video.pkg.R;
import com.android.video.pkg.databinding.LivePortraitActivityBinding;
import com.blankj.utilcode.util.BarUtils;


/**
 * @author open_9527
 * Create at 2021/5/17
 **/
public class PortraitActivity extends BaseCommonActivity  {

    private LiveViewModel mViewModel;
    private VideoView mVideoView;
    private StandardVideoController mController;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(LiveViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.live_portrait_activity, BR.vm, mViewModel);
    }

    @Override
    public void initStatusBar() {
        BarUtils.transparentStatusBar(this);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        mVideoView = ((LivePortraitActivityBinding) getBinding()).videoView;
        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_MATCH_PARENT);
        mVideoView.setLifecycleOwner(this);
//        mVideoView.startFullScreen();
        mVideoView.setLooping(true);
//        initDefinition();
        //设置视频地址
        mVideoView.setUrl(mViewModel.valueVideoUrl.get());

        mController = new StandardVideoController(this);
        if (VideoViewManager.getConfig().mIsEnableLog) {
            //调试信息
            mController.addControlComponent(new DebugInfoView(this));
        }
        PrepareView prepareView = new PrepareView(this);//准备播放界面
        ImageView thumb = prepareView.findViewById(R.id.thumb);//封面图
        ImageLoadUtils.loadImage(ImageLoadConfig.with(thumb)
                .setUrl(mViewModel.valueVideoCoverUrl.get()));
        mController.addDefaultControlComponent(prepareView, mViewModel.valueVideoTitle.get(), true);
        mVideoView.setVideoController(mController);
        mVideoView.start(); //开始播放，不调用则不自动播放
    }



    @Override
    public void initRequest() {
        mViewModel.request();
//        mViewModel.request0();
//        mViewModel.request1();
//        mViewModel.request2();
//        mViewModel.request3();
//        mViewModel.request4();
    }



    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            onBackPressed();
        };
    }

    @Override
    public void onBackPressed() {
        if (!mController.isLocked()) {
            finish();
        }
    }
}
