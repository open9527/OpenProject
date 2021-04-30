package com.open9527.webview.bridge.impl.vo;


import org.json.JSONException;
import org.json.JSONObject;

public class WebPayVo extends WebBaseVo {

    private static final String TYPE_WX = "wx";
    private static final String TYPE_ALIPAY = "alipay";

    private String tradeNo;
    private String appid;
    private String partnerid;
    private String prepayid;
    private String packageValue;
    private String timestamp;
    private String noncestr;
    private String sign;
    private String type;
    private String successUrl;
    private String failUrl;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailUrl() {
        return failUrl;
    }

    public void setFailUrl(String failUrl) {
        this.failUrl = failUrl;
    }

    public JSONObject getJsonObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("appId", appid);
            object.put("timestamp", timestamp);
            object.put("partnerid", partnerid);
            object.put("prepayid", prepayid);
            object.put("noncestr", noncestr);
            object.put("packageValue", packageValue);
            object.put("sign", sign);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

}
