package com.android.open9527.common.application;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.android.open9527.base.application.BaseApplication;
import com.android.open9527.common.BuildConfig;
import com.android.open9527.common.carsh.CrashHandler;
import com.android.open9527.common.net.data.RequestHandler;
import com.android.open9527.common.net.glide.ImageLoadUtils;
import com.android.open9527.common.net.okhttp.OkHttpClientUtils;
import com.android.open9527.common.net.server.ReleaseServer;
import com.android.open9527.common.net.server.TestServer;
import com.android.open9527.crash.Crash;
import com.android.open9527.crash.ExceptionHandler;
import com.android.open9527.glide.GlideHeadInterceptor;
import com.android.open9527.okhttp.HttpConfig;
import com.android.open9527.okhttp.config.IRequestInterceptor;
import com.android.open9527.okhttp.config.IRequestServer;
import com.android.open9527.okhttp.model.HttpHeaders;
import com.android.open9527.okhttp.model.HttpParams;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;


import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author open_9527
 * Create at 2021/1/6
 **/
public class CommonApplication extends BaseApplication {

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
    }

    private void registerGlide() {
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
                .addParam("token", "23333")
                // 添加全局请求头
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .into();

    }

}
