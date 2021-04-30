package com.open9527.webview.bridge.impl.vo;

public class WebSubscriptionVo extends WebBaseVo {
    public String id;
    public String contentType = "subscription";   //固定为 subscription
    public String title = "白夜天空";     //标题
    public String subtitle = "白夜天空办公室";    //副标题
    public String summary = "白夜天空是由白夜天空办公室创建的订阅号";  //简介
    public String shareUrl = "https://www.shmedia.tech/subscription/0001.html";   //网页访问地址
    public CountBean count;
    public TitleImageBean titleImage;

    //统计数据
    public static class CountBean {
        public int fansCount = 0;       //粉丝数
        public int likeCount = 0;       //获赞数
        public int postCount = 0;       //发布数
        public String follow = "follow";   //当前用户是否关注。follow: 关注；unfollow：未关注；
        public boolean like = false;     //当前用户是否点赞
    }

    //标题图
    public static class TitleImageBean {
        public String sourceUrl = "https://www.shmedia.tech/subscription/0001.png";       //标题图地址
        public String thumbUrl = "https://www.shmedia.tech/subscription/0001_thumb.png";   //标题图缩略图地址
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
