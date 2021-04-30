package com.open9527.webview.bridge.impl.vo;

import com.blankj.utilcode.util.AppUtils;

import java.io.Serializable;

public class WebVersionQueryParam implements Serializable {

    private static final String APP_NAME = "Android";
    private static final String JS_SDK_NAME = "rmt-js-sdk";
    private static final String JS_SDK_VERSION = "2.0.0";

    private AppBean app;
    private JsSdkBean jssdk;

    public WebVersionQueryParam(AppBean app, JsSdkBean jssdk) {
        this.app = app;
        this.jssdk = jssdk;
    }

    public AppBean getApp() {
        return app;
    }

    public void setApp(AppBean app) {
        this.app = app;
    }

    public JsSdkBean getJssdk() {
        return jssdk;
    }

    public void setJssdk(JsSdkBean jssdk) {
        this.jssdk = jssdk;
    }

    /**
     * APP版本信息
     */
    public static class AppBean implements Serializable {
        private String name;
        private String version;

        public AppBean(String name, String version) {
            this.name = name;
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    /**
     * JS版本信息
     */
    public static class JsSdkBean implements Serializable {
        private String name;
        private String version;

        public JsSdkBean(String name, String version) {
            this.name = name;
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static WebVersionQueryParam getWebVersion() {
        return new WebVersionQueryParam(
                new WebVersionQueryParam.AppBean(APP_NAME, AppUtils.getAppVersionName()),
                new WebVersionQueryParam.JsSdkBean(JS_SDK_NAME, JS_SDK_VERSION));
    }
}
