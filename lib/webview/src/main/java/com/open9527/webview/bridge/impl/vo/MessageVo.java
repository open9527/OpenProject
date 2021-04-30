package com.open9527.webview.bridge.impl.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class MessageVo implements Serializable {

    private String callbackId;
    private String responseId;
    private String responseData;
    private String handlerName;
    private Object data;

    public MessageVo() {

    }

    public MessageVo(String callbackId, String responseId, String responseData, String handlerName, Object data) {
        this.callbackId = callbackId;
        this.responseId = responseId;
        this.responseData = responseData;
        this.handlerName = handlerName;
        this.data = data;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageVo messageVo = (MessageVo) o;
        return Objects.equals(callbackId, messageVo.callbackId) &&
                Objects.equals(responseId, messageVo.responseId) &&
                Objects.equals(responseData, messageVo.responseData) &&
                Objects.equals(handlerName, messageVo.handlerName) &&
                Objects.equals(data, messageVo.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(callbackId, responseId, responseData, handlerName, data);
    }
}
