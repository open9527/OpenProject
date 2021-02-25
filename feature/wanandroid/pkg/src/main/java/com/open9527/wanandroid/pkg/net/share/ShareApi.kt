package com.open9527.wanandroid.pkg.net.share

import com.android.open9527.okhttp.annotation.HttpIgnore
import com.android.open9527.okhttp.config.IRequestApi

/**
 * @author open_9527
 * Create at 2021/1/14
 */
class ShareApi : IRequestApi {
    override fun getApi(): String {
        //user_article/list/0/json
        return "user_article/list/$page/json"
    }

    @HttpIgnore
    private var page: String? = null
    fun setPage(page: Int): ShareApi {
        this.page = page.toString()
        return this
    }
}