package com.open9527.webview.bridge.impl;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.MapUtils;

import java.io.Serializable;
import java.util.Map;

public class CallbackParam implements Serializable {

    private static final String CODE = "code";
    private static final String MESSAGE = "message";

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String CANCEL = "cancel";
    private static final String COMPLETE = "complete";

    private String type;
    private Object data;

    private CallbackParam(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toJSON() {
        return GsonUtils.toJson(this);
    }


    public static CallbackParam success(Object data) {
        CallbackParam param = new CallbackParam(SUCCESS);
        param.setData(data);
        return param;
    }


    public static CallbackParam fail(String code, String message) {
        CallbackParam param = new CallbackParam(FAIL);
        Map<String, String> data = MapUtils.newHashMap();
        data.put(CODE, code);
        data.put(MESSAGE, message);
        param.setData(data);
        return param;
    }

    public static CallbackParam fail(Object data) {
        CallbackParam param = new CallbackParam(FAIL);
        param.setData(data);
        return param;
    }

    public static CallbackParam fail() {
        return fail("-1", FAIL);
    }


    public static CallbackParam cancel() {
        CallbackParam param = new CallbackParam(CANCEL);
        return param;
    }

    public static CallbackParam complete() {
        CallbackParam param = new CallbackParam(COMPLETE);
        return param;
    }

}
