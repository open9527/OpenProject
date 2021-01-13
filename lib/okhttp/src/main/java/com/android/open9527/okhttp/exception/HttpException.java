package com.android.open9527.okhttp.exception;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public class HttpException extends Exception {

    private String mMessage;

    public HttpException(String message) {
        super(message);
        mMessage = message;
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
        mMessage = message;
    }

    /**
     * 获取错误信息
     */
    @Override
    public String getMessage() {
        return mMessage;
    }
}