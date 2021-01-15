package com.open9527.wanandroid.pkg.net.share;

import com.android.open9527.okhttp.annotation.HttpIgnore;
import com.android.open9527.okhttp.config.IRequestApi;

/**
 * @author open_9527
 * Create at 2021/1/14
 **/
public class ShareApi implements IRequestApi {
    @Override
    public String getApi() {
        //user_article/list/0/json
        return "user_article/list/" + page + "/json";
    }
    @HttpIgnore
    private String page;

    public ShareApi setPage(int page) {
        this.page = String.valueOf(page);
        return this;
    }

}
