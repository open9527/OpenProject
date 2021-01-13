package com.android.open9527.okhttp.exception;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public final class UnknownException extends HttpException {

    public UnknownException(String message) {
        super(message);
    }

    public UnknownException(String message, Throwable cause) {
        super(message, cause);
    }
}