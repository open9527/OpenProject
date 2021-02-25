package com.open9527.wanandroid.pkg.net

import java.io.Serializable

/**
 * @author open_9527
 * Create at 2021/1/14
 */
class ContentVo : Serializable {
    var title: String? = null
    var link: String? = null
    var author: String? = null
    var shareUser: String? = null
    var niceShareDate: String? = null
    var envelopePic: String? = null
    override fun toString(): String {
        return "ContentVo{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", author='" + author + '\'' +
                ", shareUser='" + shareUser + '\'' +
                ", niceShareDate='" + niceShareDate + '\'' +
                ", envelopePic='" + envelopePic + '\'' +
                '}'
    }
}