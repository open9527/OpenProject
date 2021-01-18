package com.open9527.wanandroid.pkg.net.project;

import com.android.open9527.okhttp.config.IRequestApi;

/**
 * @author open_9527
 * Create at 2021/1/18
 **/
public class ProjectTreeApi implements IRequestApi {
    @Override
    public String getApi() {
        return "project/tree/json";
    }
}
