package com.android.open9527.common.net.data;

/**
 * @author open_9527
 * Create at 2021/1/8
 * <p>
 * eg:按需修改
 **/
public class HttpData<T> {
    /**
     * 返回码
     */
    private int errorCode;
    /**
     * 提示语
     */
    private String errorMsg;
    /**
     * 数据
     */
    private T data;

    public int getCode() {
        return errorCode;
    }

    public String getMessage() {
        return errorMsg;
    }

    public T getData() {
        return data;
    }

}
