package com.android.open9527.image.export.api;

import com.android.open9527.common.bundle.BaseBundleData;

/**
 * @author open_9527
 * Create at 2021/2/22
 **/
public class ImageBundle extends BaseBundleData {
    private String name;
    private String type;
    private String url;

    public ImageBundle(String name, String url) {
        this(name, "", url);
    }

    public ImageBundle(String name, String type, String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageBundle{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
