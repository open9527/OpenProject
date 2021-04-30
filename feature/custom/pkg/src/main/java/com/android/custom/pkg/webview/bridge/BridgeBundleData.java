package com.android.custom.pkg.webview.bridge;

import android.text.TextUtils;

import com.android.open9527.common.bundle.BaseBundleData;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.open9527.webview.bridge.impl.vo.BridgeControlType;
import com.open9527.webview.bridge.impl.vo.WebBaseVo;
import com.open9527.webview.bridge.impl.vo.WebConfigShareVo;
import com.open9527.webview.bridge.impl.vo.WebConfigUIVo;


import java.util.ArrayList;
import java.util.Map;


public class BridgeBundleData extends BaseBundleData {
    private boolean requireToken;
    private String tokenParam;

    private String link;
    private String target;
    @SerializedName("content_type")
    private String contentType;
    @SerializedName("content_id")
    private String contentId;

    //标题条设置
    @SerializedName("enable_header")
    private boolean isHeaderTitleBarShow = true;
    @SerializedName("header_title")
    private String headerTitle;
    @SerializedName("header_back")
    private boolean isHeaderBackShow = true;
    private boolean isHeaderBackEnabled = true;
    @SerializedName("header_close")
    private boolean isHeaderCloseShow = false;
    private boolean isHeaderCloseEnabled = true;
    @SerializedName("header_share")
    private boolean isHeaderShareShow = false;
    private boolean isHeaderShareEnabled = true;
    private boolean isHeaderVoiceShow = true;
    private boolean isHeaderVoiceEnabled = true;

    @SerializedName("enable_panel")
    private boolean enablePanel = false;
    @SerializedName("enable_comment")
    private boolean enableComment = false;

    //底边栏设置
    @SerializedName("enable_footer")
    private boolean enableFooter = false;

    @SerializedName("footer_input")
    private String footerInput = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    @SerializedName("footer_favorite")
    private String footerFavorite = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    @SerializedName("footer_share")
    private String footerShare = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    @SerializedName("footer_like")
    private String footerLike = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;

    //快捷分享区设置
    @SerializedName("enable_shortcut")
    private boolean isShortcutShow = false;
    private String shortcutLike = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String shortcutWechat = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String shortcutWechatMoment = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String shortcutQQ = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String shortcutQQZone = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String shortcutWeiBo = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;

    //"更多"弹出框设置
    private String moreBoxWechat = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String moreBoxWechatMoment = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String moreBoxQQ = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String moreBoxQQZone = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String moreBoxWeibo = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String moreBoxTheme = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String moreBoxCopyLink = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private String moreBoxReport = BridgeControlType.BTN_CONTROL_TYPE_ENABLE;
    private int footerCommentCount = 0;
    private int footerLikeCount = 0;

    // 设置分享数据
    private Map<String, ShareData> shareData;

    // 设置分享数据
    private WebConfigUIVo.ThemeBean theme;

    public boolean isRequireToken() {
        return requireToken;
    }

    public void setRequireToken(boolean requireToken) {
        this.requireToken = requireToken;
    }

    public String getTokenParam() {
        return tokenParam;
    }

    public void setTokenParam(String tokenParam) {
        this.tokenParam = tokenParam;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public boolean isHeaderTitleBarShow() {
        return isHeaderTitleBarShow;
    }

    public void setHeaderTitleBarShow(boolean headerTitleBarShow) {
        isHeaderTitleBarShow = headerTitleBarShow;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public boolean isHeaderBackShow() {
        return isHeaderBackShow;
    }

    public void setHeaderBackShow(boolean headerBackShow) {
        isHeaderBackShow = headerBackShow;
    }

    public boolean isHeaderBackEnabled() {
        return isHeaderBackEnabled;
    }

    public void setHeaderBackEnabled(boolean headerBackEnabled) {
        isHeaderBackEnabled = headerBackEnabled;
    }

    public boolean isHeaderCloseShow() {
        return isHeaderCloseShow;
    }

    public void setHeaderCloseShow(boolean headerCloseShow) {
        isHeaderCloseShow = headerCloseShow;
    }

    public boolean isHeaderCloseEnabled() {
        return isHeaderCloseEnabled;
    }

    public void setHeaderCloseEnabled(boolean headerCloseEnabled) {
        isHeaderCloseEnabled = headerCloseEnabled;
    }

    public boolean isHeaderShareShow() {
        return isHeaderShareShow;
    }

    public void setHeaderShareShow(boolean headerShareShow) {
        isHeaderShareShow = headerShareShow;
    }

    public boolean isHeaderShareEnabled() {
        return isHeaderShareEnabled;
    }

    public void setHeaderShareEnabled(boolean headerShareEnabled) {
        isHeaderShareEnabled = headerShareEnabled;
    }

    public boolean isHeaderVoiceShow() {
        return isHeaderVoiceShow;
    }

    public void setHeaderVoiceShow(boolean headerVoiceShow) {
        isHeaderVoiceShow = headerVoiceShow;
    }

    public boolean isHeaderVoiceEnabled() {
        return isHeaderVoiceEnabled;
    }

    public void setHeaderVoiceEnabled(boolean headerVoiceEnabled) {
        isHeaderVoiceEnabled = headerVoiceEnabled;
    }

    public boolean isEnablePanel() {
        return enablePanel;
    }

    public void setEnablePanel(boolean enablePanel) {
        this.enablePanel = enablePanel;
    }

    public boolean isEnableComment() {
        return enableComment;
    }

    public void setEnableComment(boolean enableComment) {
        this.enableComment = enableComment;
    }

    public boolean isEnableFooter() {
        return enableFooter;
    }

    public void setEnableFooter(boolean enableFooter) {
        this.enableFooter = enableFooter;
    }

    public String getFooterInput() {
        return footerInput;
    }

    public void setFooterInput(String footerInput) {
        this.footerInput = footerInput;
    }

    public String getFooterFavorite() {
        return footerFavorite;
    }

    public void setFooterFavorite(String footerFavorite) {
        this.footerFavorite = footerFavorite;
    }

    public String getFooterShare() {
        return footerShare;
    }

    public void setFooterShare(String footerShare) {
        this.footerShare = footerShare;
    }

    public String getFooterLike() {
        return footerLike;
    }

    public void setFooterLike(String footerLike) {
        this.footerLike = footerLike;
    }

    public boolean isShortcutShow() {
        return isShortcutShow;
    }

    public void setShortcutShow(boolean shortcutShow) {
        isShortcutShow = shortcutShow;
    }

    public String getShortcutLike() {
        return shortcutLike;
    }

    public void setShortcutLike(String shortcutLike) {
        this.shortcutLike = shortcutLike;
    }

    public String getShortcutWechat() {
        return shortcutWechat;
    }

    public void setShortcutWechat(String shortcutWechat) {
        this.shortcutWechat = shortcutWechat;
    }

    public String getShortcutWechatMoment() {
        return shortcutWechatMoment;
    }

    public void setShortcutWechatMoment(String shortcutWechatMoment) {
        this.shortcutWechatMoment = shortcutWechatMoment;
    }

    public String getShortcutQQ() {
        return shortcutQQ;
    }

    public void setShortcutQQ(String shortcutQQ) {
        this.shortcutQQ = shortcutQQ;
    }

    public String getShortcutQQZone() {
        return shortcutQQZone;
    }

    public void setShortcutQQZone(String shortcutQQZone) {
        this.shortcutQQZone = shortcutQQZone;
    }

    public String getShortcutWeiBo() {
        return shortcutWeiBo;
    }

    public void setShortcutWeiBo(String shortcutWeiBo) {
        this.shortcutWeiBo = shortcutWeiBo;
    }

    public String getMoreBoxWechat() {
        return moreBoxWechat;
    }

    public void setMoreBoxWechat(String moreBoxWechat) {
        this.moreBoxWechat = moreBoxWechat;
    }

    public String getMoreBoxWechatMoment() {
        return moreBoxWechatMoment;
    }

    public void setMoreBoxWechatMoment(String moreBoxWechatMoment) {
        this.moreBoxWechatMoment = moreBoxWechatMoment;
    }

    public String getMoreBoxQQ() {
        return moreBoxQQ;
    }

    public void setMoreBoxQQ(String moreBoxQQ) {
        this.moreBoxQQ = moreBoxQQ;
    }

    public String getMoreBoxQQZone() {
        return moreBoxQQZone;
    }

    public void setMoreBoxQQZone(String moreBoxQQZone) {
        this.moreBoxQQZone = moreBoxQQZone;
    }

    public String getMoreBoxWeibo() {
        return moreBoxWeibo;
    }

    public void setMoreBoxWeibo(String moreBoxWeibo) {
        this.moreBoxWeibo = moreBoxWeibo;
    }

    public String getMoreBoxTheme() {
        return moreBoxTheme;
    }

    public void setMoreBoxTheme(String moreBoxTheme) {
        this.moreBoxTheme = moreBoxTheme;
    }

    public String getMoreBoxCopyLink() {
        return moreBoxCopyLink;
    }

    public void setMoreBoxCopyLink(String moreBoxCopyLink) {
        this.moreBoxCopyLink = moreBoxCopyLink;
    }

    public String getMoreBoxReport() {
        return moreBoxReport;
    }

    public void setMoreBoxReport(String moreBoxReport) {
        this.moreBoxReport = moreBoxReport;
    }

    public int getFooterCommentCount() {
        return footerCommentCount;
    }

    public void setFooterCommentCount(int footerCommentCount) {
        this.footerCommentCount = footerCommentCount;
    }

    public int getFooterLikeCount() {
        return footerLikeCount;
    }

    public void setFooterLikeCount(int footerLikeCount) {
        this.footerLikeCount = footerLikeCount;
    }

    public Map<String, ShareData> getShareData() {
        return shareData;
    }

    public void setShareData(Map<String, ShareData> shareData) {
        this.shareData = shareData;
    }

    public WebConfigUIVo.ThemeBean getTheme() {
        return theme;
    }

    public void setTheme(WebConfigUIVo.ThemeBean theme) {
        this.theme = theme;
    }

    public static ShareData create(WebConfigShareVo.ShareVo shareVo) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(shareVo), ShareData.class);
    }

    public static class ShareData extends WebBaseVo {
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

    //更多
    public static void updateMore(BridgeBundleData bundleData, WebConfigUIVo.MoreBoxBean moreBoxBean) {
        bundleData.setMoreBoxWechat(moreBoxBean.wechat);
        bundleData.setMoreBoxWechatMoment(moreBoxBean.wechatMoment);
        bundleData.setMoreBoxQQ(moreBoxBean.qq);
        bundleData.setMoreBoxQQZone(moreBoxBean.qqZone);
        bundleData.setMoreBoxWeibo(moreBoxBean.weibo);
        bundleData.setMoreBoxTheme(moreBoxBean.theme);
        bundleData.setMoreBoxCopyLink(moreBoxBean.copyLink);
        bundleData.setMoreBoxReport(moreBoxBean.report);
    }

    //标题条
    public static void updateHead(BridgeBundleData bundleData, WebConfigUIVo.HeaderBean headerBean) {
        if (headerBean == null) return;
        bundleData.setHeaderTitleBarShow(headerBean.enable);
        bundleData.setHeaderBackShow(BridgeControlType.isShow(headerBean.back));
        bundleData.setHeaderBackEnabled(BridgeControlType.isEnable(headerBean.back));
        bundleData.setHeaderCloseShow(BridgeControlType.isShow(headerBean.close));
        bundleData.setHeaderCloseEnabled(BridgeControlType.isEnable(headerBean.close));
        bundleData.setHeaderShareShow(BridgeControlType.isEnable(headerBean.more));
        bundleData.setHeaderShareEnabled(BridgeControlType.isEnable(headerBean.more));
        bundleData.setHeaderVoiceShow(BridgeControlType.isShow(headerBean.voice));
        bundleData.setHeaderVoiceEnabled(BridgeControlType.isEnable(headerBean.voice));
        if (!TextUtils.isEmpty(headerBean.title)) {
            bundleData.setHeaderTitle(headerBean.title);
        }
    }

    //快捷
    public static void updateShortcuts(BridgeBundleData bundleData, WebConfigUIVo.ShortcutsBean shortcutsBean) {
        bundleData.setShortcutShow(shortcutsBean.enable);
        bundleData.setShortcutLike(shortcutsBean.like);
        bundleData.setShortcutWechat(shortcutsBean.wechat);
        bundleData.setShortcutWechatMoment(shortcutsBean.wechatMoment);
        bundleData.setShortcutQQ(shortcutsBean.qq);
        bundleData.setShortcutQQZone(shortcutsBean.qqZone);
        bundleData.setShortcutWeiBo(shortcutsBean.weibo);
    }

    //底部菜单栏
    public static void updateFooter(BridgeBundleData bundleData, WebConfigUIVo.FooterBean footerBean) {
        bundleData.setEnableFooter(footerBean.enable);
        bundleData.setFooterInput(footerBean.input);
        bundleData.setFooterFavorite(footerBean.favorite);
        bundleData.setFooterLike(footerBean.like);
        bundleData.setFooterShare(footerBean.more);
        bundleData.setFooterCommentCount(footerBean.commentCount);
        bundleData.setFooterLikeCount(footerBean.likeCount);
    }

    //更新风格选项设置
    public static void updateTheme(BridgeBundleData bundleData, WebConfigUIVo.ThemeBean themeBean) {
        WebConfigUIVo.ThemeBean thisTheme = new WebConfigUIVo.ThemeBean();
        thisTheme.fontSizes = new ArrayList<>();
        thisTheme.colors = new ArrayList<>();

        if (themeBean != null) {
            if (themeBean.fontSizes != null) {
                thisTheme.fontSizes.addAll(themeBean.fontSizes);
            }

            if (themeBean.colors != null) {
                thisTheme.colors.addAll(themeBean.colors);
            }
        }
        bundleData.setTheme(thisTheme);
    }

    public static void activeFontSize(BridgeBundleData bundleData, String activeFontSizeId) {
        WebConfigUIVo.ThemeBean theme = bundleData.getTheme();
        if (theme != null && theme.fontSizes != null) {
            for (WebConfigUIVo.ThemeBean.FontSizeBean fontSize : theme.fontSizes) {
                fontSize.active = StringUtils.equals(fontSize.id, activeFontSizeId);
            }
        }
    }

    public static WebConfigUIVo.ThemeBean.FontSizeBean currentActiveFontSize(BridgeBundleData bundleData) {
        WebConfigUIVo.ThemeBean theme = bundleData.getTheme();
        if (theme != null && theme.fontSizes != null) {
            for (WebConfigUIVo.ThemeBean.FontSizeBean fontSize : theme.fontSizes) {
                if (fontSize.active) {
                    return fontSize;
                }
            }
        }
        return null;
    }

    public static void activeThemeColor(BridgeBundleData bundleData, String activeThemeColorId) {
        WebConfigUIVo.ThemeBean theme = bundleData.getTheme();
        if (theme != null && theme.colors != null) {
            for (WebConfigUIVo.ThemeBean.ColorBean themeColor : theme.colors) {
                themeColor.active = StringUtils.equals(themeColor.id, activeThemeColorId);
            }
        }
    }

    public static WebConfigUIVo.ThemeBean.ColorBean currentActiveThemeColor(BridgeBundleData bundleData) {
        WebConfigUIVo.ThemeBean theme = bundleData.getTheme();
        if (theme != null && theme.colors != null) {
            for (WebConfigUIVo.ThemeBean.ColorBean themeColor : theme.colors) {
                if (themeColor.active) {
                    return themeColor;
                }
            }
        }
        return null;
    }

}

