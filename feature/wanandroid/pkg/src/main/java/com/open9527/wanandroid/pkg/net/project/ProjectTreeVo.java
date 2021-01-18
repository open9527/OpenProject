package com.open9527.wanandroid.pkg.net.project;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/1/18
 **/
public class ProjectTreeVo implements Serializable {

    private String id;

    @SerializedName("name")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
