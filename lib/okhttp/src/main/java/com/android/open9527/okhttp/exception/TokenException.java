package com.android.open9527.okhttp.exception;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public final class TokenException extends HttpException {

    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}