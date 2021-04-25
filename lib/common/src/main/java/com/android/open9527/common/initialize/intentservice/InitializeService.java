package com.android.open9527.common.initialize.intentservice;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.android.open9527.common.BuildConfig;
import com.android.open9527.common.net.data.RequestHandler;
import com.android.open9527.common.net.okhttp.OkHttpClientUtils;
import com.android.open9527.common.net.server.ReleaseServer;
import com.android.open9527.common.net.server.TestServer;
import com.android.open9527.crash.Crash;
import com.android.open9527.crash.ExceptionHandler;
import com.android.open9527.okhttp.HttpConfig;
import com.android.open9527.okhttp.config.IRequestServer;
import com.blankj.utilcode.util.AppUtils;

/**
 * @author open_9527
 * Create at 2021/1/6
 * <p>
 * eg:
 * <service
 * android:name=".InitializeService"
 * android:enabled="true"
 * android:exported="false"/>
 **/

public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "InitializeService";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    /**
     * 子线程进行初始化SDK操作
     **/
    private void initApplication() {
//        initCrash();
        initOkHttp();
    }

    private void initCrash() {
        Crash.install(getApplication(), new ExceptionHandler() {
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
        HttpConfig.with(OkHttpClientUtils.getInstance())
                // 是否打印日志
                .setLogEnabled(BuildConfig.DEBUG)
                // 设置服务器配置
                .setServer(server)
                // 设置请求处理策略
                .setHandler(new RequestHandler(this))
                // 设置请求参数拦截器
                .setInterceptor((url, tag, params, headers) -> headers.put("timestamp", String.valueOf(System.currentTimeMillis())))
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
