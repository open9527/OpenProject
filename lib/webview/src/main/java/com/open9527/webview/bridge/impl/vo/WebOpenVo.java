package com.open9527.webview.bridge.impl.vo;

/**
 * 内置页面接口
 */
public class WebOpenVo extends WebBaseVo{
    private String actionUrl;  //链接地址，定义参考《通用跳转链接》
    private boolean closing;   //打开链接后，是否关闭本页面
    private int currentIndex; //当前打开第几个图片
    private ImagesBean images;  //图片资源列表


    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public boolean isClosing() {
        return closing;
    }

    public void setClosing(boolean closing) {
        this.closing = closing;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public static class ImagesBean extends WebBaseVo {
        public String sourceUrl;       //原图路径
        public String thumbUrl;   //缩略图路径

    }

    //打开"评论"页面
    public static class CommentBean extends WebBaseVo {
        private String targetType;   //评论对象类型。content:稿件; comment:评论
        private String targetId;          //评论对象ID
        private String content;  //评论内容

        public String getTargetType() {
            return targetType;
        }

        public String getTargetId() {
            return targetId;
        }

        public String getContent() {
            return content;
        }
    }

    //打开"举报"页面
    public static class ReportBean extends WebBaseVo {
        private String targetType;   //评论对象类型。content:稿件; comment:评论
        private String targetId;          //评论对象ID
        private String content;  //评论内容

        public String getTargetType() {
            return targetType;
        }

        public String getTargetId() {
            return targetId;
        }

        public String getContent() {
            return content;
        }
    }
//
//    //打开"更多"页面
//    public static class MoreBoxBean extends WebBaseBean {
//        public String wechat;       //"分享到微信"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
//        public String wechatMoment; //"分享到朋友圈"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
//        public String qq;       //"分享到QQ"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
//        public String qqZone;     //"分享到QQ空间"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
//        public String weibo;      //"分享到微博"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
//        public String theme;      //"字体"与"配色"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
//        public String report;     //"举报"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
//        public String copyLink;     //"拷贝链接"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
//    }


}
