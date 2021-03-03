package com.android.feature.webview.pkg.net.article.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/3/2
 **/
public class DataVo implements Serializable {

    @SerializedName("datas")
    private List<ContentVo> dataList;

    public List<ContentVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<ContentVo> dataList) {
        this.dataList = dataList;
    }
}
