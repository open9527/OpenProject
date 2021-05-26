package com.android.open9527.common.application;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.android.open9527.base.application.BaseApplication;
import com.android.open9527.common.BuildConfig;
import com.android.open9527.common.R;
import com.android.open9527.common.net.data.RequestHandler;
import com.android.open9527.common.net.glide.ImageLoadUtils;
import com.android.open9527.common.net.okhttp.OkHttpClientUtils;
import com.android.open9527.common.net.server.ReleaseServer;
import com.android.open9527.common.net.server.TestServer;
import com.android.open9527.crash.Crash;
import com.android.open9527.crash.ExceptionHandler;
import com.android.open9527.glide.webp.WebpBytebufferDecoder;
import com.android.open9527.glide.webp.WebpResourceDecoder;
import com.android.open9527.okhttp.HttpConfig;
import com.android.open9527.okhttp.config.IRequestInterceptor;
import com.android.open9527.okhttp.config.IRequestServer;
import com.android.open9527.okhttp.model.HttpHeaders;
import com.android.open9527.okhttp.model.HttpParams;
import com.android.open9527.video.common.player.VideoView;
import com.android.open9527.video.common.player.VideoViewConfig;
import com.android.open9527.video.common.player.VideoViewManager;
import com.android.open9527.video.common.render.TextureRenderViewFactory;
import com.android.open9527.video.exo.ExoMediaPlayerFactory;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.ResourceDecoder;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.tencent.smtt.sdk.QbSdk;

import java.io.InputStream;
import java.net.Proxy;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author open_9527
 * Create at 2021/1/6
 **/
public class CommonApplication extends BaseApplication {
    private static final String TAG = "CommonApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        // 本地异常捕捉
//        InitializeService.start(this);
//        initCrash();
        initOkHttp();
        registerGlide();
        initVideo();
        initX5WebView();
    }

    private void initVideo() {
        //播放器配置，注意：此为全局配置，按需开启
        VideoViewManager.setConfig(VideoViewConfig.newBuilder()
                .setLogEnabled(BuildConfig.DEBUG)//调试的时候请打开日志，方便排错
                .setPlayerFactory(ExoMediaPlayerFactory.create())
                .setRenderViewFactory(TextureRenderViewFactory.create())
//                .setEnableOrientation(true)
//                .setEnableAudioFocus(false)
                .setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT)
//                .setAdaptCutout(false)
                .setPlayOnMobileNetwork(true)
//                .setProgressManager(new ProgressManagerImpl())
                .build());

    }


    private void registerGlide() {
        // webp support
//        ResourceDecoder decoder = new WebpResourceDecoder(this);
//        ResourceDecoder byteDecoder = new WebpBytebufferDecoder(this);
//        // use prepend() avoid intercept by default decoder
//        Glide.get(this).getRegistry()
//                .prepend(InputStream.class, Drawable.class, decoder)
//                .prepend(ByteBuffer.class, Drawable.class, byteDecoder);



        //Glide低内存优化操作
        registerComponentCallbacks(new ComponentCallbacks2() {
            @Override
            public void onTrimMemory(int level) {
                //HOME键退出应用程序
                //程序在内存清理的时候执行
                if (level == TRIM_MEMORY_UI_HIDDEN) {
                    ImageLoadUtils.clearMemory(getApplicationContext());
                }
                ImageLoadUtils.trimMemory(getApplicationContext(), level);

//                LogUtils.i("registerGlide", "onTrimMemory:" + level);
            }

            @Override
            public void onConfigurationChanged(@NonNull Configuration newConfig) {

            }

            @Override
            public void onLowMemory() {
                //低内存的时候执行
                ImageLoadUtils.clearMemory(getApplicationContext());
//                LogUtils.i("registerGlide", "onLowMemory:");
            }
        });

    }

    private void initCrash() {
        Crash.install(getApplicationContext(), new ExceptionHandler() {
            @Override
            protected void onUncaughtExceptionHappened(Thread thread, Throwable throwable) {

            }

            @Override
            protected void onBandageExceptionHappened(Throwable throwable) {

            }

            @Override
            protected void onEnterSafeMode() {

            }

            @Override
            protected void onMayBeBlackScreen(Throwable e) {
                //TODO:黑屏,最好是重启app
                AppUtils.relaunchApp(true);
            }

        });
    }

    private void initOkHttp() {
        IRequestServer server;
        if (BuildConfig.DEBUG) {
            server = new TestServer();
        } else {
            server = new ReleaseServer();
        }
        //cookie持久化存储
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this));

        OkHttpClient okHttpClient = OkHttpClientUtils.getInstance().newBuilder()
                .proxy(Proxy.NO_PROXY)
                .cookieJar(cookieJar)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        HttpConfig.with(okHttpClient)
                // 是否打印日志
                .setLogEnabled(BuildConfig.DEBUG)
                // 设置服务器配置
                .setServer(server)
                // 设置请求处理策略
                .setHandler(new RequestHandler(this))
                // 设置请求参数拦截器
                .setInterceptor(new IRequestInterceptor() {
                    @Override
                    public void intercept(String url, String tag, HttpParams params, HttpHeaders headers) {
                        headers.put("timestamp", String.valueOf(System.currentTimeMillis()));
                    }
                })
                // 设置请求重试次数
                .setRetryCount(1)
                // 设置请求重试时间
                .setRetryTime(1000)
                // 添加全局请求参数
//                .addParam("token", "23333")
                // 添加全局请求头
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .into();

    }

    public void initX5WebView() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtils.i(TAG, " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            ClassicsHeader classicsHeader = new ClassicsHeader(context);
            classicsHeader.setAccentColorId(R.color.gray);
            classicsHeader.setPrimaryColor(ContextCompat.getColor(context, R.color.white));
            return classicsHeader;
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            ClassicsFooter classicsFooter = new ClassicsFooter(context).setDrawableSize(20);
            classicsFooter.setDrawableSize(20);
            classicsFooter.setAccentColorId(R.color.gray);//设置强调颜色
            classicsFooter.setPrimaryColor(ContextCompat.getColor(context, R.color.white));
            return classicsFooter;
        });
    }
}
