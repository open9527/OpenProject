package com.android.feature.webview.pkg.net.article.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/3/2
 **/
public class BannerVo implements Serializable {

    private String title;
    @SerializedName("url")
    private String link;
    @SerializedName("imagePath")
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
