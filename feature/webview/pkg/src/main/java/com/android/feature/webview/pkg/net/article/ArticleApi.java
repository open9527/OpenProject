package com.android.feature.webview.pkg.net.article;

import com.android.open9527.okhttp.annotation.HttpIgnore;
import com.android.open9527.okhttp.config.IRequestApi;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class ArticleApi implements IRequestApi {
    @Override
    public String getApi() {
        return "article/list/" + page + "/json";
    }

    @HttpIgnore
    private String page;

    public ArticleApi setPage(int page) {
        this.page = String.valueOf(page);
        return this;
    }
}
