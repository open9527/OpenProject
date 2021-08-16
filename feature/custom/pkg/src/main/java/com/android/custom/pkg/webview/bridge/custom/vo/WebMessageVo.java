package com.android.custom.pkg.webview.bridge.custom.vo;


/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class WebMessageVo extends WebBaseVo {

    private String callbackId;
    private String responseId;
    private String responseData;
    private String handlerName;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WebMessageVo{" +
                "callbackId='" + callbackId + '\'' +
                ", responseId='" + responseId + '\'' +
                ", responseData='" + responseData + '\'' +
                ", handlerName='" + handlerName + '\'' +
                ", data=" + data +
                '}';
    }
}
