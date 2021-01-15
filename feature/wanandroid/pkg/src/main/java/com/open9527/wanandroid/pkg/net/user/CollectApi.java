package com.open9527.wanandroid.pkg.net.user;

import com.android.open9527.okhttp.annotation.HttpIgnore;
import com.android.open9527.okhttp.config.IRequestApi;

/**
 * @author open_9527
 * Create at 2021/1/14
 **/
public class CollectApi implements IRequestApi {
    @Override
    public String getApi() {
        //lg/collect/list/0/json
        return "lg/collect/list/" + page + "/json";
    }
    @HttpIgnore
    private String page;

    public CollectApi setPage(int page) {
        this.page = String.valueOf(page);
        return this;
    }
}
