package com.android.custom.app;

import com.android.custom.pkg.BuildConfig;
import com.android.open9527.common.application.CommonApplication;
import com.android.open9527.filter.AppFilter;
import com.android.open9527.video.common.player.VideoViewConfig;
import com.android.open9527.video.common.player.VideoViewManager;
import com.android.open9527.video.exo.ExoMediaPlayerFactory;
import com.open9527.umeng.UmengClient;

/**
 * @author open_9527
 * Create at 2021/1/5
 **/
public class CustomApp extends CommonApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        UmengClient.init(this);
        AppFilter.with(this);
        initVideo();
    }

    private void initVideo() {
        //播放器配置，注意：此为全局配置，按需开启
        VideoViewManager.setConfig(VideoViewConfig.newBuilder()
                .setLogEnabled(BuildConfig.DEBUG)//调试的时候请打开日志，方便排错
                .setPlayerFactory(ExoMediaPlayerFactory.create())
//                .setRenderViewFactory(SurfaceRenderViewFactory.create())
//                .setEnableOrientation(true)
//                .setEnableAudioFocus(false)
//                .setScreenScaleType(VideoView.SCREEN_SCALE_MATCH_PARENT)
//                .setAdaptCutout(false)
//                .setPlayOnMobileNetwork(true)
//                .setProgressManager(new ProgressManagerImpl())
                .build());

    }
}
