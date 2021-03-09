package com.open9527.router.exception;

/**
 * @author open_9527
 * Create at 2021/3/1
 **/
public class NoRouteFoundException extends RuntimeException {

    public NoRouteFoundException(String detailMessage) {
        super(detailMessage);
    }
}
