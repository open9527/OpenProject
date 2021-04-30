package com.open9527.webview.bridge.impl.vo;

import java.util.List;

public class WebConfigUIVo extends WebBaseVo {


    public HeaderBean header;
    public ShortcutsBean shortcuts;
    public PanelsBean panels;
    public CommentsBean comments;
    public FooterBean footer;
    public TickBean tick;
    public MoreBoxBean moreBox;
    public ThemeBean theme;


    //标题条设置
    public static class HeaderBean extends WebBaseVo {
        public boolean enable = true;      //是否显示标题条，默认：true
        public String title = "";   //标题文本
        public String back = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;     //"返回"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String close = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;    //"关闭"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String voice = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;    //"语音"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String more = BridgeControlType.BTN_CONTROL_TYPE_DISMISS;      //"更多"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。

    }

    //快捷分享区设置
    public static class ShortcutsBean extends WebBaseVo {
        public boolean enable = false;          //是否显示快捷分享区，默认：false
        public String like = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;          //"点赞"按钮。dismiss:不显示；disable：显示不可操作；limit：受限操作，必须登陆，且只能发一次；enable：不受限，随便发
        public String wechat = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;     //"分享到微信"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String wechatMoment = BridgeControlType.BTN_CONTROL_TYPE_ENABLE; //"分享到朋友圈"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String qq = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;           //"分享到QQ"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String qqZone = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;    //"分享到QQ空间"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String weibo = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;        //"分享到微博"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。

    }


    //动态功能块设置
    public static class PanelsBean extends WebBaseVo {
        public boolean enable;  //是否显示动态功能块，默认：false
    }

    //评论区设置
    public static class CommentsBean extends WebBaseVo {
        public boolean enable;   //是否显示评论区，默认：false
    }

    //底边栏设置
    public static class FooterBean extends WebBaseVo {

        public boolean enable;//是否显示底边栏，默认：false
        public String input = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;//评论输入框。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String favorite = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;//"收藏"按钮。dismiss:不显示；disable：显示不可操作；light：显示且已收藏，可取消收藏；dark：显示且未收藏，可收藏。
        public String more = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;//"更多"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String like = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;//"点赞"按钮。dismiss:不显示；disable：显示不可操作；light：显示且已点赞，可取消赞；dark：显示且未点赞，可点赞。
        public int commentCount;//评论数
        public int likeCount;//点赞数
    }

    //已读计时器设置
    public static class TickBean extends WebBaseVo {
        public boolean enable;       //是否显示已读计时器，默认：false
        public String totalSeconds;   //最大倒数秒数，倒数时间结束后将增加阅读数，并消失

    }

    //"更多"弹出框设置
    public static class MoreBoxBean extends WebBaseVo {
        public String wechat = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;       //"分享到微信"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String wechatMoment = BridgeControlType.BTN_CONTROL_TYPE_ENABLE; //"分享到朋友圈"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String qq = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;       //"分享到QQ"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String qqZone = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;     //"分享到QQ空间"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String weibo = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;      //"分享到微博"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String theme = BridgeControlType.BTN_CONTROL_TYPE_DISMISS;      //"字体"与"配色"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String report = BridgeControlType.BTN_CONTROL_TYPE_DISMISS;     //"举报"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
        public String copyLink = BridgeControlType.BTN_CONTROL_TYPE_DISMISS;     //"拷贝链接"按钮。dismiss:不显示；disable：显示不可操作；enable：显示且可操作。
    }

    //可选风格设置
    public static class ThemeBean extends WebBaseVo {
        public List<FontSizeBean> fontSizes;
        public List<ColorBean> colors;

        //可选字体方案设置，当选择字体方案时，通过事件 rmt.fontChanged 传递给页面
        public static class FontSizeBean extends WebBaseVo {
            public String id; //字体方案编号
            public String name;    //字体名称
            public String size;    //字体标号
            public boolean active;   //是否处于激活状态
        }

        //可选颜色方案设置，当选择颜色方案时，通过事件 rmt.colorChanged 传递给页面
        public static class ColorBean extends WebBaseVo {
            public String id;              //方案标号
            public String name;          //方案名称public String //方案样本图
            public String imageUrl;
            public boolean active;   //是否处于激活状态
        }
    }

}
