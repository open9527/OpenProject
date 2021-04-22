package com.android.custom.app;

import com.android.open9527.common.application.CommonApplication;
import com.android.open9527.filter.AppFilter;
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
    }
}
