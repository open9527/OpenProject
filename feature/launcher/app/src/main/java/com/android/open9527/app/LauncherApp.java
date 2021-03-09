package com.android.open9527.app;

import com.android.open9527.common.application.CommonApplication;
import com.open9527.router.Router;

/**
 * @author open_9527
 * Create at 2021/1/5
 **/
public class LauncherApp extends CommonApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        DoraemonKit.install(this);
        Router.init(this);
    }
}