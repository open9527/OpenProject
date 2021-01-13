package com.android.open9527.okhttp.exception;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public final class TimeoutException extends HttpException {

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}