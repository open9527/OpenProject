package com.open9527.umeng;

import android.app.Activity;

import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author open_9527
 * Create at 2021/3/9
 **/
public class WeChatUtils {

    public static void startApplets(Activity activity, String WeiXinId, String value) {
        try {
            if (UmengClient.isAppInstalled(activity, Platform.WECHAT)) {
                return;
            }
            IWXAPI api = WXAPIFactory.createWXAPI(activity, WeiXinId);
            WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
            // 填小程序原始id
            req.userName = value;
            //拉起小程序页面的可带参路径，不填默认拉起小程序首页
            // req.path = path;
            // 可选打开 开发版，体验版和正式版
            req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;
            api.sendReq(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
