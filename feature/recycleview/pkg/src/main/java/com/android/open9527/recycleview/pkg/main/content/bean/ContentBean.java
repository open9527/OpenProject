package com.android.open9527.recycleview.pkg.main.content.bean;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class ContentBean implements Serializable {

    private String title;
    private String content;
    private String imageUrl;

    public ContentBean() {

    }

    public ContentBean(String title, String content, String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public ContentBean(String title, String content) {
        this(title, content, "");
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
