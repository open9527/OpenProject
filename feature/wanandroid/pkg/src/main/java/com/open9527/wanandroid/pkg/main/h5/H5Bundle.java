package com.open9527.wanandroid.pkg.main.h5;

import com.open9527.wanandroid.pkg.bean.BaseBundleData;

/**
 * @author open_9527
 * Create at 2021/1/18
 **/
public class H5Bundle extends BaseBundleData {
    private String url;
    private String title;


    public H5Bundle(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public H5Bundle(String url) {
        this(url, "");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
