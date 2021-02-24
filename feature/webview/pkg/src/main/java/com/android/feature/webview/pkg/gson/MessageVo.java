package com.android.feature.webview.pkg.gson;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class MessageVo implements Serializable {
    public static final String UPDATE_WEB_VIEW_HEIGHT = "updateWebViewHeight";

    private String callbackId;
    private String responseId;
    private String responseData;
    private String handlerName;

    public String getCallbackId() {
        return callbackId;
    }

    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }



    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    @Override
    public String toString() {
        return "Message{" +
                "callbackId='" + callbackId + '\'' +
                ", responseId='" + responseId + '\'' +
                ", responseData='" + responseData + '\'' +
                ", handlerName='" + handlerName + '\'' +
                '}';
    }
}
