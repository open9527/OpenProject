package com.android.open9527.pkg.request;

import com.android.open9527.okhttp.config.IRequestApi;

/**
 * @author open_9527
 * Create at 2021/1/8
 **/
public class SearchAuthorApi implements IRequestApi {
    @Override
    public String getApi() {
        return "article/list/0/json";
    }

    /** 作者昵称，不支持模糊匹配 */
    private String author;

    public SearchAuthorApi setAuthor(String author) {
        this.author = author;
        return this;
    }
}
