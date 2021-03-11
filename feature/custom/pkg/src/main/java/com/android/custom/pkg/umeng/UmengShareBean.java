package com.android.custom.pkg.umeng;

import android.graphics.drawable.Drawable;

import com.open9527.umeng.Platform;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/3/9
 **/
public class UmengShareBean implements Serializable {

    /**
     * 分享图标
     */
    private  Drawable mShareIcon;
    /**
     * 分享名称
     */
    private  String mShareName;
    /**
     * 分享平台
     */
    private  Platform mSharePlatform;

    public UmengShareBean(Drawable icon, String name, Platform platform) {
        mShareIcon = icon;
        mShareName = name;
        mSharePlatform = platform;
    }

    public Drawable getShareIcon() {
        return mShareIcon;
    }

    public String getShareName() {
        return mShareName;
    }

    public Platform getSharePlatform() {
        return mSharePlatform;
    }
}
