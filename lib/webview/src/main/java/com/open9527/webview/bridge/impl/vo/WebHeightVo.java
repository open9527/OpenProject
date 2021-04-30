package com.open9527.webview.bridge.impl.vo;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class WebHeightVo extends WebBaseVo {
    public static final String UPDATE_WEB_VIEW_HEIGHT = "updateWebViewHeight";

    private int height = 0;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
