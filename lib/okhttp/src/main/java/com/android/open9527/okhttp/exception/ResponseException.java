package com.android.open9527.okhttp.exception;

import okhttp3.Response;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public final class ResponseException extends HttpException {

    private final Response mResponse;

    public ResponseException(String message, Response response) {
        super(message);
        mResponse = response;
    }

    public ResponseException(String message, Throwable cause, Response response) {
        super(message, cause);
        mResponse = response;
    }

    public Response getResponse() {
        return mResponse;
    }
}