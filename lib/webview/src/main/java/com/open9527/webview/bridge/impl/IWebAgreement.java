package com.open9527.webview.bridge.impl;


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

public interface IWebAgreement {


    /* **********************************1.基础接口*********************************************** */

    /**
     * 添加JS-SDK版本
     * 1.APP版本
     * 2.JS-SDK版本
     */
    void getVersion(IResponseCallback<WebBaseVo> callback);

    /**
     * 添加当前会话 Token
     */
    void getToken(IResponseCallback<WebBaseVo> callback);

    /**
     * 添加当前登录用户信息
     */
    void getAccount(IResponseCallback<WebBaseVo> callback);


    /* **********************************2.设置"接口*********************************************** */

    /**
     * "设置UI元素
     */
    void configUI(IResponseCallback<WebConfigUIVo> callback);

    /***
     * 设置分享数据
     */
    void configShare(IResponseCallback<WebConfigShareVo> callback);

    /* **********************************3.设备环境接口*********************************************** */

    /**
     * 添加网络环境
     */
    void getNetworkType(IResponseCallback<WebBaseVo> callback);

    /**
     * 添加地理位置
     */
    void getLocation(IResponseCallback<WebBaseVo> callback);

    /**
     * 添加剪切板文本
     */
    void getClipboardText(IResponseCallback<WebBaseVo> callback);

    /**
     * 获取剪切板文本
     */
    void setClipboardText(IResponseCallback<WebShearPlateTextVo> callback);

    /* **********************************4.UI接口*********************************************** */

    /**
     * 显示标题条
     */
    void showHeader(IResponseCallback<WebConfigUIVo.HeaderBean> callback);

    /**
     * 隐藏标题条
     */
    void hideHeader(IResponseCallback<WebBaseVo> callback);

    /**
     * 显示快捷操作区
     */
    void showShortcuts(IResponseCallback<WebConfigUIVo.ShortcutsBean> callback);

    /**
     * 隐藏快捷操作区
     */
    void hideShortcuts(IResponseCallback<WebBaseVo> callback);

    /**
     * 显示动态功能块
     */
    void showPanels(IResponseCallback<WebBaseVo> callback);

    /**
     * 隐藏动态功能块
     */
    void hidePanels(IResponseCallback<WebBaseVo> callback);

    /**
     * 显示评论区
     */
    void showComments(IResponseCallback<WebBaseVo> callback);

    /**
     * 隐藏评论区
     */
    void hideComments(IResponseCallback<WebBaseVo> callback);

    /**
     * 显示底边栏
     */
    void showFooter(IResponseCallback<WebConfigUIVo.FooterBean> callback);

    /**
     * 隐藏底边栏
     */
    void hideFooter(IResponseCallback<WebBaseVo> callback);

    /**
     * 显示已读计时器
     */
    void showTick(IResponseCallback<WebConfigUIVo.TickBean> callback);

    /**
     * 激活页面风格菜单中的字体
     */
    void activeFontSize(IResponseCallback<WebConfigUIVo.ThemeBean.FontSizeBean> callback);

    /**
     * 获取当前激活的字体
     */
    void getActiveFontSize(IResponseCallback<WebBaseVo> callback);

    /**
     * 激活页面风格菜单中的配色方案
     */
    void activeColorTheme(IResponseCallback<WebConfigUIVo.ThemeBean.ColorBean> callback);

    /**
     * 获取当前激活的配色方案
     */
    void getActiveColorTheme(IResponseCallback<WebBaseVo> callback);

    /**
     * 隐藏已读计时器
     */
    void hideTick(IResponseCallback<WebBaseVo> callback);

    /**
     * 设置WEB视图高度
     */
    void updateWebViewHeight(IResponseCallback<WebHeightVo> callback);

    /* **********************************6.业务内容接口************************************************/

    /**
     * 获取业务内容设置
     */
    void getBusiness(IResponseCallback<WebBaseVo> callback);

    /**
     * 更新业务内容设置
     */
    void updateBusiness(IResponseCallback<WebBusinessVo> callback);

    /* **********************************7.业务内容接口************************************************/

    /**
     * 打开"通用跳转链接"
     */
    void openAction(IResponseCallback<WebOpenVo> callback);

    /**
     * 打开"登录"页面
     */
    void openLogin(IResponseCallback<WebBaseVo> callback);

    /**
     * 打开"预览图片"页面
     */
    void openAlbum(IResponseCallback<WebOpenAlbumVo> callback);

    /**
     * 打开"评论"页面
     */
    void openComment(IResponseCallback<WebOpenVo.CommentBean> callback);

    /**
     * 打开"举报"页面
     */
    void openReport(IResponseCallback<WebOpenVo.ReportBean> callback);


    /**
     * 打开"更多"页面
     */
    void openMoreBox(IResponseCallback<WebConfigUIVo.MoreBoxBean> callback);

    /**
     * 打开"全屏视频播放"页面
     */
    void openVideoPlayer(IResponseCallback<WebOpenVideoVo> callback);

    /* **********************************8.内置操作接口*********************************************** */

    /**
     * 下载图片到相册
     */
    void downloadImage(IResponseCallback<WebMediaVo> callback);


    /**
     * 扫描图片（识别二维码）
     */
    void scanImage(IResponseCallback<WebMediaVo> callback);

    /* **********************************9.订阅号接口*********************************************** */

    /**
     * 获取订阅号信息
     */
    void getSubscription(IResponseCallback<WebSubscriptionVo> callback);

    /**
     * 关注订阅号
     */
    void followSubscription(IResponseCallback<WebSubscriptionVo> callback);

    /**
     * 取消关注订阅号
     */
    void unfollowSubscription(IResponseCallback<WebSubscriptionVo> callback);

    /**
     * 图片保存,识别
     */

    void showPicEdit(IResponseCallback<WebPicEditVo> callback);

    /**
     * 上传图片
     */
    void getPhoneAndVideo(IResponseCallback<WebPhotoVo> callback);


    void uploadControlVideoOrPic(IResponseCallback<WebPhotoVo> callback);

    /**
     * 支付
     */
    void goPay(IResponseCallback<WebPayVo> callback);

    /**
     * 检查schema是否可用
     */
    void validateSchema(IResponseCallback<WebSchemaVo> callback);


    /* **********************************9.通知事件接口*********************************************** */

    /**
     * //系统就绪后会执行ready方法
     * //所有接口调用都必须在ready获得结果之后
     */
    void sendReadyEvent();

    /**
     * 发送播放语音事件
     */
    void sendVoiceClickEvent();

    /**
     * 发送长按屏幕事件
     *
     * @param x //横坐标，单位：像素
     * @param y //纵坐标，单位：像素
     */
    void sendLongTouchEvent(int x, int y);

    /**
     * 变更页面风格事
     */
    void sendThemeChangeEvent(WebThemeQueryParam webThemeQueryParam);

}
