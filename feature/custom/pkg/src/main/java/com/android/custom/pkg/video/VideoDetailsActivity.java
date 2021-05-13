package com.android.custom.pkg.video;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.databinding.VideoDetailsActivityBinding;
import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.net.glide.ImageLoadUtils;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.video.common.player.VideoView;
import com.android.open9527.video.ui.StandardVideoController;
import com.android.open9527.video.ui.component.PrepareView;
import com.blankj.utilcode.util.BarUtils;

/**
 * @author open_9527
 * Create at 2021/5/12
 **/
public class VideoDetailsActivity extends BaseCommonActivity {

    private VideoDetailsViewModel mViewModel;
    private VideoView mVideoView;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(VideoDetailsViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.video_details_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initStatusBar() {
        BarUtils.transparentStatusBar(this);

    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        mVideoView = ((VideoDetailsActivityBinding) getBinding()).videoView;
        mVideoView.setLifecycleOwner(this);
        mVideoView.setLooping(true);
        mVideoView.setUrl(mViewModel.valueVideoUrl.get()); //设置视频地址
        StandardVideoController mController = new StandardVideoController(this);

        PrepareView prepareView = new PrepareView(this);//准备播放界面
        ImageView thumb = prepareView.findViewById(R.id.thumb);//封面图
        ImageLoadUtils.loadImage(ImageLoadConfig.with(thumb)
                .setUrl(mViewModel.valueVideoCoverUrl.get()));
        mController.addDefaultControlComponent(prepareView, mViewModel.valueVideoTitle.get(), false);
        mVideoView.setVideoController(mController);
        mVideoView.start(); //开始播放，不调用则不自动播放
    }


    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            onBackPressed();
        };
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        mVideoView.pause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mVideoView.resume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mVideoView.release();
//    }


    @Override
    public void onBackPressed() {
        if (!mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
