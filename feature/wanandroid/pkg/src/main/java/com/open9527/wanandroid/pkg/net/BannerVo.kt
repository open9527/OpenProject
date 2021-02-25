package com.open9527.wanandroid.pkg.net

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author open_9527
 * Create at 2021/1/15
 */
class BannerVo : Serializable {
    var title: String? = null

    @SerializedName("url")
    var link: String? = null

    @SerializedName("imagePath")
    var imageUrl: String? = null
}