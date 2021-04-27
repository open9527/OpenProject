package com.android.custom.pkg.recycleview.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/4/25
 **/
public class DataVo implements Serializable {

    @SerializedName("datas")
    private List<ContentVo> contentVos;

    public List<ContentVo> getContentVos() {
        return contentVos;
    }

    public void setContentVos(List<ContentVo> contentVos) {
        this.contentVos = contentVos;
    }
}
