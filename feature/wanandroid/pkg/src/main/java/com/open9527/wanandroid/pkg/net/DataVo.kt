package com.open9527.wanandroid.pkg.net

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author open_9527
 * Create at 2021/1/14
 */
class DataVo : Serializable {
    @SerializedName("datas")
    var dataList: List<ContentVo>? = null
}