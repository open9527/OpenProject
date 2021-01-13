package com.android.open9527.pkg.request;


import com.android.open9527.okhttp.annotation.HttpRename;
import com.android.open9527.okhttp.config.IRequestApi;

/**
 *  搜索文章
 */
public final class SearchBlogsApi implements IRequestApi {

    @Override
    public String getApi() {
        return "article/query/0/json";
    }

    /** 搜索关键字 */
    @HttpRename("k")
    private String keyword;

    public SearchBlogsApi setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
}