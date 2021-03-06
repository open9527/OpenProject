package com.open9527.wanandroid.pkg.net.project;

import com.android.open9527.okhttp.annotation.HttpIgnore;
import com.android.open9527.okhttp.config.IRequestApi;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class ProjectApi implements IRequestApi {
    @Override
    public String getApi() {
        return "project/list/" + page + "/json";
    }

    @HttpIgnore
    private String page;

    private String cid;

    public ProjectApi setPage(int page) {
        this.page = String.valueOf(page);
        return this;
    }

    public ProjectApi setCid(String cid) {
        this.cid = cid;
        return this;
    }
}
