package com.open9527.wanandroid.pkg.net

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author open_9527
 * Create at 2021/1/15
 */
class BannerVo : Serializable {
    var id: String? = null
    var title: String? = null

    @SerializedName("url")
    var link: String? = null

    @SerializedName("imagePath")
    var imageUrl: String? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BannerVo

        if (id != other.id) return false
        if (title != other.title) return false
        if (link != other.link) return false
        if (imageUrl != other.imageUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (link?.hashCode() ?: 0)
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        return result
    }


}