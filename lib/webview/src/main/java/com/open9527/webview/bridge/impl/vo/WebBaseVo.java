package com.open9527.webview.bridge.impl.vo;


import com.open9527.webview.bridge.Callback;

import java.io.Serializable;

public class WebBaseVo implements Serializable {
    public static final String HANDLER_NAME_GET_VERSION = "getVersion";
    public static final String HANDLER_NAME_GET_TOKEN = "getToken";
    public static final String HANDLER_NAME_GET_ACCOUNT = "getAccount";
    public static final String HANDLER_NAME_CONFIG_UI = "configUI";
    public static final String HANDLER_NAME_CONFIG_SHARE = "configShare";
    public static final String HANDLER_NAME_GET_NETWORK_TYPE = "getNetworkType";
    public static final String HANDLER_NAME_GET_LOCATION = "getLocation";
    public static final String HANDLER_NAME_GET_CLIPBOARD_TEXT = "getClipboardText";
    public static final String HANDLER_NAME_SET_CLIPBOARD_TEXT = "setClipboardText";
    public static final String HANDLER_NAME_SHOW_HEADER = "showHeader";
    public static final String HANDLER_NAME_HIDE_HEADER = "hideHeader";
    public static final String HANDLER_NAME_SHOW_SHORTCUTS = "showShortcuts";
    public static final String HANDLER_NAME_HIDE_SHORTCUTS = "hideShortcuts";
    public static final String HANDLER_NAME_SHOW_PANELS = "showPanels";
    public static final String HANDLER_NAME_HIDE_PANELS = "hidePanels";
    public static final String HANDLER_NAME_SHOW_COMMENTS = "showComments";
    public static final String HANDLER_NAME_HIDE_COMMENTS = "hideComments";
    public static final String HANDLER_NAME_SHOW_FOOTER = "showFooter";
    public static final String HANDLER_NAME_HIDE_FOOTER = "hideFooter";
    public static final String HANDLER_NAME_SHOW_TICK = "showTick";
    public static final String HANDLER_NAME_HIDE_TICK = "hideTick";
    public static final String HANDLER_NAME_ACTIVE_FONT_SIZE = "activeFontSize";
    public static final String HANDLER_NAME_GET_ACTIVE_FONT_SIZE= "getActiveFontSize";
    public static final String HANDLER_NAME_ACTIVE_COLOR_THEME = "activeColorTheme";
    public static final String HANDLER_NAME_GET_ACTIVE_COLOR_THEME = "getActiveColorTheme";
    public static final String HANDLER_NAME_GET_BUSINESS = "getBusiness";
    public static final String HANDLER_NAME_UPDATE_BUSINESS = "updateBusiness";
    public static final String HANDLER_NAME_OPEN_ACTION = "openAction";
    public static final String HANDLER_NAME_OPEN_LOGIN = "openLogin";
    public static final String HANDLER_NAME_OPEN_ALBUM = "openAlbum";
    public static final String HANDLER_NAME_OPEN_COMMENT = "openComment";
    public static final String HANDLER_NAME_OPEN_REPORT = "openReport";
    public static final String HANDLER_NAME_OPEN_MORE_BOX = "openMoreBox";
    public static final String HANDLER_NAME_OPEN_VIDEO_PLAYER = "openVideoPlayer";
    public static final String HANDLER_NAME_DOWNLOAD_IMAGE = "downloadImage";
    public static final String HANDLER_NAME_SCAN_IMAGE = "scanImage";
    public static final String HANDLER_NAME_GET_SUBSCRIPTION = "getSubscription";
    public static final String HANDLER_NAME_FOLLOW_SUBSCRIPTION = "followSubscription";
    public static final String HANDLER_NAME_UN_FOLLOW_SUBSCRIPTION = "unfollowSubscription";
    public static final String HANDLER_NAME_UPDATE_WEB_VIEW_HEIGHT = "updateWebViewHeight";
    public static final String HANDLER_NAME_SHOW_PIC_EDIT = "showPicEdit";
    public static final String HANDLER_NAME_GET_PHONE_AND_VIDEO = "getPhoneAndVideo";
    public static final String HANDLER_NAME_UPLOAD_CONTROL_VIDEO_OR_PIC = "uploadControlVideoOrPic";
    public static final String HANDLER_NAME_GO_PAY = "gopay";
    public static final String HANDLER_NAME_VALIDATE_SCHEMA = "validateSchema";
    public static final String HANDLER_NAME_READY = "ready";
    public static final String HANDLER_NAME_VOICE_CLICK = "voiceClick";
    public static final String HANDLER_NAME_LONG_TOUCH = "longTouch";
    public static final String HANDLER_NAME_THEME_CHANGE = "themeChange";


    private String UUID;
    private Callback callback;

    public WebBaseVo() {

    }

    public WebBaseVo(Callback callback) {
        this.callback = callback;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
