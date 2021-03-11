package com.open9527.umeng;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author open_9527
 * Create at 2021/3/9
 **/
public class WeChatUtils {

    /**
     * 调用 微信小程序
     *
     * @param activity
     * @param WeiXinId
     * @param value
     */
    public static void startApplets(@NonNull Activity activity, @NonNull String WeiXinId,@NonNull String value) {
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


    /**
     * 微信支付
     *
     * @param context
     * @param json
     */
    public static void wxPay(@NonNull Context context, @NonNull JSONObject json, @NonNull String wxAppId) {
        try {
            String appId = json.getString("appId");
            IWXAPI api = WXAPIFactory.createWXAPI(context, appId, false);
            //jsonObject中有一下几个值可以获取，然后调用微信sdk支付
            String timestamp = json.getString("timestamp");
            String partnerid = json.getString("partnerid");
            String prepayid = json.getString("prepayid");
            String noncestr = json.getString("noncestr");
            String packageValue = json.getString("packageValue");
            String sign = json.getString("sign");

            PayReq req = new PayReq();
            req.appId = wxAppId;
            req.partnerId = partnerid;
            req.prepayId = prepayid;
            req.nonceStr = noncestr;
            req.timeStamp = timestamp;
            req.packageValue = packageValue;
            req.sign = sign;
            api.sendReq(req);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
