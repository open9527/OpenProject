package com.open9527.wanandroid.pkg.net.article;

import com.android.open9527.okhttp.config.IRequestApi;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class BannerApi implements IRequestApi {
    @Override
    public String getApi() {
        return "banner/json";
    }
}
