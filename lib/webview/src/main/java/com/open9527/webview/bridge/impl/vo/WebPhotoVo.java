package com.open9527.webview.bridge.impl.vo;


public class WebPhotoVo extends WebBaseVo {

    private static final String TYPE_VIDEO = "video";
    private static final String TYPE_IMAGE = "image";
    private static final String TYPE_ALL = "all";
    private int video; //video:300 单位 秒 string
    private int image; //image:30  单位 M  string
    private String type;  //video/image/all

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isImage() {
        return TYPE_IMAGE.equals(getType());
    }

    public boolean isVideo() {
        return TYPE_VIDEO.equals(getType());
    }

    public boolean isAll() {
        return TYPE_ALL.equals(getType());
    }
}
