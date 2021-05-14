package com.android.video.pkg.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/5/14
 **/
public class TikTokVo implements Serializable {

    @SerializedName("title")
    private String videoTitle;

    @SerializedName("videoDownloadUrl")
    private String videoUrl;

    @SerializedName("coverImgUrl")
    private String videoCoverImgUrl;


    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoCoverImgUrl() {
        return videoCoverImgUrl;
    }

    public void setVideoCoverImgUrl(String videoCoverImgUrl) {
        this.videoCoverImgUrl = videoCoverImgUrl;
    }
}
