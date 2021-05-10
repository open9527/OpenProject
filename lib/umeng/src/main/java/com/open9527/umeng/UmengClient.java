package com.open9527.umeng;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;

/**
 * @author open_9527
 * Create at 2021/3/9
 **/
public final class UmengClient {

    /**
     * 初始化友盟相关 SDK
     */
    public static void init(Application application) {

        try {
            Bundle metaData = application.getPackageManager().getApplicationInfo(application.getPackageName(), PackageManager.GET_META_DATA).metaData;
            UMConfigure.init(application, String.valueOf(metaData.get("UMENG_APPKEY")), "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
            // 初始化各个平台的 Key
            PlatformConfig.setWeixin(String.valueOf(metaData.get("WX_APPID")), String.valueOf(metaData.get("WX_APPKEY")));
            PlatformConfig.setQQZone(String.valueOf(metaData.get("QQ_APPID")), String.valueOf(metaData.get("QQ_APPKEY")));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分享
     *
     * @param activity Activity对象
     * @param platform 分享平台
     * @param data     分享内容
     * @param listener 分享监听
     */
    public static void share(Activity activity, Platform platform, UmengShare.ShareData data, UmengShare.OnShareListener listener) {
        if (isAppInstalled(activity, platform)) {
            new ShareAction(activity)
                    .setPlatform(platform.getThirdParty())
                    .withMedia(data.createWeb())
//                    .withMedia(data.createVideo())
                    .setCallback(listener != null ? new UmengShare.ShareListenerWrapper(platform.getThirdParty(), listener) : null)
                    .share();
            return;
        }
        // 当分享的平台软件可能没有被安装的时候
        if (listener != null) {
            listener.onError(platform, new PackageManager.NameNotFoundException(platform.getThirdParty().getName()+" Is not installed"));
        }
    }

    /**
     * 登录
     *
     * @param activity Activity对象
     * @param platform 登录平台
     * @param listener 登录监听
     */
    public static void login(Activity activity, Platform platform, UmengLogin.OnLoginListener listener) {
        if (isAppInstalled(activity, platform)) {
            try {
                // 删除旧的第三方登录授权
                UMShareAPI.get(activity).deleteOauth(activity, platform.getThirdParty(), null);
                // 要先等上面的代码执行完毕之后
                Thread.sleep(200);
                // 开启新的第三方登录授权
                UMShareAPI.get(activity).getPlatformInfo(activity, platform.getThirdParty(), listener != null ? new UmengLogin.LoginListenerWrapper(platform.getThirdParty(), listener) : null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        // 当登录的平台软件可能没有被安装的时候
        if (listener != null) {
            listener.onError(platform, new PackageManager.NameNotFoundException(platform.getThirdParty().getName()+" Is not installed"));
        }
    }

    /**
     * 设置回调
     */
    public static void onActivityResult(Activity activity, int requestCode, int resultCode, @Nullable Intent data) {
        UMShareAPI.get(activity).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 判断 App 是否安装
     */
    public static boolean isAppInstalled(Activity activity, Platform platform) {
        return UMShareAPI.get(activity).isInstall(activity, platform.getThirdParty());
    }

    /**
     * 内存泄漏解决方案
     * 在使用分享或者授权的Activity中，重写onDestory()方法：
     *
     * @param activity
     */
    public static void release(Activity activity) {
        UMShareAPI.get(activity).release();
    }

}