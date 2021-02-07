package com.android.open9527.image.pkg.load;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/1/25
 **/
public class ImageVo implements Serializable {

    @SerializedName("url")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
