package com.android.feature.webview.export.api;

import com.android.open9527.common.bundle.BaseBundleData;

/**
 * @author open_9527
 * Create at 2021/2/25
 **/
public final class WebBundle extends BaseBundleData {

    private String url;
    private String title;

    public WebBundle(String url, String title) {
        this.url = url;
        this.title = title;
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

    @Override
    public String toString() {
        return "WebBundle{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
