package com.open9527.webview.bridge.impl;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.MapUtils;
import com.open9527.webview.bridge.IWebView;
import com.open9527.webview.bridge.impl.vo.WebBaseVo;
import com.open9527.webview.bridge.impl.vo.WebBusinessVo;
import com.open9527.webview.bridge.impl.vo.WebConfigShareVo;
import com.open9527.webview.bridge.impl.vo.WebConfigUIVo;
import com.open9527.webview.bridge.impl.vo.WebHeightVo;
import com.open9527.webview.bridge.impl.vo.WebMediaVo;
import com.open9527.webview.bridge.impl.vo.WebOpenAlbumVo;
import com.open9527.webview.bridge.impl.vo.WebOpenVo;
import com.open9527.webview.bridge.impl.vo.WebOpenVideoVo;
import com.open9527.webview.bridge.impl.vo.WebPayVo;
import com.open9527.webview.bridge.impl.vo.WebPhotoVo;
import com.open9527.webview.bridge.impl.vo.WebPicEditVo;
import com.open9527.webview.bridge.impl.vo.WebSchemaVo;
import com.open9527.webview.bridge.impl.vo.WebShearPlateTextVo;
import com.open9527.webview.bridge.impl.vo.WebSubscriptionVo;
import com.open9527.webview.bridge.impl.vo.WebThemeQueryParam;

import java.util.Map;


public class WebAgreementImpl implements IWebAgreement {
    private static final String TAG = "WebAgreementImpl";

    private IWebView mIWebView;

    public WebAgreementImpl(@NonNull IWebView iWebView) {
        this.mIWebView = iWebView;
    }

    public static WebAgreementImpl newInstance(@NonNull IWebView iWebView) {
        return new WebAgreementImpl(iWebView);
    }

    @Override
    public void getVersion(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_GET_VERSION);
    }

    @Override
    public void getToken(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_GET_TOKEN);
    }

    @Override
    public void getAccount(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_GET_ACCOUNT);
    }

    @Override
    public void configUI(IResponseCallback<WebConfigUIVo> callback) {
        create(WebConfigUIVo.class, callback, WebBaseVo.HANDLER_NAME_CONFIG_UI);
    }

    @Override
    public void configShare(IResponseCallback<WebConfigShareVo> callback) {
        create(WebConfigShareVo.class, callback, WebBaseVo.HANDLER_NAME_CONFIG_SHARE);
    }

    @Override
    public void getNetworkType(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_GET_NETWORK_TYPE);
    }

    @Override
    public void getLocation(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_GET_LOCATION);
    }

    @Override
    public void getClipboardText(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_GET_CLIPBOARD_TEXT);
    }

    @Override
    public void setClipboardText(IResponseCallback<WebShearPlateTextVo> callback) {
        create(WebShearPlateTextVo.class, callback, WebBaseVo.HANDLER_NAME_SET_CLIPBOARD_TEXT);
    }

    @Override
    public void showHeader(IResponseCallback<WebConfigUIVo.HeaderBean> callback) {
        create(WebConfigUIVo.HeaderBean.class, callback, WebBaseVo.HANDLER_NAME_SHOW_HEADER);
    }

    @Override
    public void hideHeader(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_HIDE_HEADER);
    }

    @Override
    public void showShortcuts(IResponseCallback<WebConfigUIVo.ShortcutsBean> callback) {
        create(WebConfigUIVo.ShortcutsBean.class, callback, WebBaseVo.HANDLER_NAME_SHOW_SHORTCUTS);
    }

    @Override
    public void hideShortcuts(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_HIDE_SHORTCUTS);
    }

    @Override
    public void showPanels(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_SHOW_PANELS);
    }

    @Override
    public void hidePanels(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_HIDE_PANELS);
    }

    @Override
    public void showComments(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_SHOW_COMMENTS);
    }

    @Override
    public void hideComments(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_HIDE_COMMENTS);
    }

    @Override
    public void showFooter(IResponseCallback<WebConfigUIVo.FooterBean> callback) {
        create(WebConfigUIVo.FooterBean.class, callback, WebBaseVo.HANDLER_NAME_SHOW_FOOTER);
    }

    @Override
    public void hideFooter(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_HIDE_FOOTER);
    }

    @Override
    public void showTick(IResponseCallback<WebConfigUIVo.TickBean> callback) {
        create(WebConfigUIVo.TickBean.class, callback, WebBaseVo.HANDLER_NAME_SHOW_TICK);
    }

    @Override
    public void hideTick(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_HIDE_TICK);
    }

    @Override
    public void activeFontSize(IResponseCallback<WebConfigUIVo.ThemeBean.FontSizeBean> callback) {
        create(WebConfigUIVo.ThemeBean.FontSizeBean.class, callback, WebBaseVo.HANDLER_NAME_ACTIVE_FONT_SIZE);
    }

    @Override
    public void getActiveFontSize(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_GET_ACTIVE_FONT_SIZE);
    }

    @Override
    public void activeColorTheme(IResponseCallback<WebConfigUIVo.ThemeBean.ColorBean> callback) {
        create(WebConfigUIVo.ThemeBean.ColorBean.class, callback, WebBaseVo.HANDLER_NAME_ACTIVE_COLOR_THEME);
    }

    @Override
    public void getActiveColorTheme(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_GET_ACTIVE_COLOR_THEME);
    }

    @Override
    public void getBusiness(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_GET_BUSINESS);
    }

    @Override
    public void updateBusiness(IResponseCallback<WebBusinessVo> callback) {
        create(WebBusinessVo.class, callback, WebBaseVo.HANDLER_NAME_UPDATE_BUSINESS);
    }

    @Override
    public void openAction(IResponseCallback<WebOpenVo> callback) {
        create(WebOpenVo.class, callback, WebBaseVo.HANDLER_NAME_OPEN_ACTION);
    }

    @Override
    public void openLogin(IResponseCallback<WebBaseVo> callback) {
        create(WebBaseVo.class, callback, WebBaseVo.HANDLER_NAME_OPEN_LOGIN);
    }

    @Override
    public void openAlbum(IResponseCallback<WebOpenAlbumVo> callback) {
        create(WebOpenAlbumVo.class, callback, WebBaseVo.HANDLER_NAME_OPEN_ALBUM);
    }

    @Override
    public void openComment(IResponseCallback<WebOpenVo.CommentBean> callback) {
        create(WebOpenVo.CommentBean.class, callback, WebBaseVo.HANDLER_NAME_OPEN_COMMENT);
    }

    @Override
    public void openReport(IResponseCallback<WebOpenVo.ReportBean> callback) {
        create(WebOpenVo.ReportBean.class, callback, WebBaseVo.HANDLER_NAME_OPEN_REPORT);
    }

    @Override
    public void openMoreBox(IResponseCallback<WebConfigUIVo.MoreBoxBean> callback) {
        create(WebConfigUIVo.MoreBoxBean.class, callback, WebBaseVo.HANDLER_NAME_OPEN_MORE_BOX);
    }

    @Override
    public void openVideoPlayer(IResponseCallback<WebOpenVideoVo> callback) {
        create(WebOpenVideoVo.class, callback, WebBaseVo.HANDLER_NAME_OPEN_VIDEO_PLAYER);
    }

    @Override
    public void downloadImage(IResponseCallback<WebMediaVo> callback) {
        create(WebMediaVo.class, callback, WebBaseVo.HANDLER_NAME_DOWNLOAD_IMAGE);
    }

    @Override
    public void scanImage(IResponseCallback<WebMediaVo> callback) {
        create(WebMediaVo.class, callback, WebBaseVo.HANDLER_NAME_SCAN_IMAGE);
    }

    @Override
    public void getSubscription(IResponseCallback<WebSubscriptionVo> callback) {
        create(WebSubscriptionVo.class, callback, WebBaseVo.HANDLER_NAME_GET_SUBSCRIPTION);
    }

    @Override
    public void followSubscription(IResponseCallback<WebSubscriptionVo> callback) {
        create(WebSubscriptionVo.class, callback, WebBaseVo.HANDLER_NAME_FOLLOW_SUBSCRIPTION);
    }

    @Override
    public void unfollowSubscription(IResponseCallback<WebSubscriptionVo> callback) {
        create(WebSubscriptionVo.class, callback, WebBaseVo.HANDLER_NAME_UN_FOLLOW_SUBSCRIPTION);
    }

    @Override
    public void updateWebViewHeight(IResponseCallback<WebHeightVo> callback) {
        create(WebHeightVo.class, callback, WebBaseVo.HANDLER_NAME_UPDATE_WEB_VIEW_HEIGHT);
    }

    @Override
    public void showPicEdit(IResponseCallback<WebPicEditVo> callback) {
        create(WebPicEditVo.class, callback, WebBaseVo.HANDLER_NAME_SHOW_PIC_EDIT);
    }

    @Override
    public void getPhoneAndVideo(IResponseCallback<WebPhotoVo> callback) {
        create(WebPhotoVo.class, callback, WebBaseVo.HANDLER_NAME_GET_PHONE_AND_VIDEO);
    }

    @Override
    public void uploadControlVideoOrPic(IResponseCallback<WebPhotoVo> callback) {
        create(WebPhotoVo.class, callback, WebBaseVo.HANDLER_NAME_UPLOAD_CONTROL_VIDEO_OR_PIC);
    }

    @Override
    public void goPay(IResponseCallback<WebPayVo> callback) {
        create(WebPayVo.class, callback, WebBaseVo.HANDLER_NAME_GO_PAY);
    }

    @Override
    public void validateSchema(IResponseCallback<WebSchemaVo> callback) {
        create(WebSchemaVo.class, callback, WebBaseVo.HANDLER_NAME_VALIDATE_SCHEMA);
    }

    @Override
    public void sendReadyEvent() {
        send(WebBaseVo.HANDLER_NAME_READY, null);
    }

    @Override
    public void sendVoiceClickEvent() {
        send(WebBaseVo.HANDLER_NAME_VOICE_CLICK, null);
    }

    @Override
    public void sendLongTouchEvent(int x, int y) {
        Map<String, Integer> params = MapUtils.newHashMap();
        params.put("x", x);
        params.put("y", y);
        send(WebBaseVo.HANDLER_NAME_LONG_TOUCH, GsonUtils.toJson(params));
    }

    @Override
    public void sendThemeChangeEvent(WebThemeQueryParam webThemeQueryParam) {
        send(WebBaseVo.HANDLER_NAME_THEME_CHANGE, GsonUtils.toJson(webThemeQueryParam));
    }

    private void send(String handlerName, String data) {
        mIWebView.callHandler(handlerName, data, null);
    }

    public <T extends WebBaseVo> void create(Class<T> clazz, IResponseCallback<T> responseCallback, String handlerName) {
        LogUtils.i(TAG, "create handlerName: " + handlerName);
        mIWebView.registerHandler(handlerName, (data, callback) -> {
            LogUtils.i(TAG, "registerHandler data: " + data);
            T t = null;
            try {
                t = clazz.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }

            if (!TextUtils.isEmpty(data)) {
                try {
                    t = GsonUtils.fromJson(data, clazz);
                } catch (Exception e) {
                    LogUtils.i(TAG, "registerHandler Exception: " + e.getMessage());
                }
            }

            if (t != null) {
                t.setCallback(callback);
            }
            responseCallback.onCallback(t);
        });
    }

}
