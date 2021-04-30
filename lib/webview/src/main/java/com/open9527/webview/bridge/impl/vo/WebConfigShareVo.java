package com.open9527.webview.bridge.impl.vo;

public class WebConfigShareVo extends WebBaseVo {

    public WechatBean wechat;//设置"分享到微信"数据
    public WechatMomentBean wechatMoment;//设置"分享到朋友圈"数据
    public QqBean qq; //设置"分享到QQ"数据
    public QqZoneBean qqZone;//设置"分享到QQ空间"数据
    public WeiboBean weibo; //设置"分享到微博"数据

    public static class WechatBean extends ShareVo {

    }

    public static class WechatMomentBean extends ShareVo {

    }

    public static class QqBean extends ShareVo {

    }

    public static class QqZoneBean extends ShareVo {

    }

    public static class WeiboBean extends ShareVo {

    }
    public static class ShareVo extends WebBaseVo {

        public String title;     //标题
        public String summary;  //简介
        public String link;              //链接地址
        public String imageUrl;       //配图

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
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
}
