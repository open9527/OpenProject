package com.open9527.compiler.router;

/**
 * @author open_9527
 * Create at 2021/3/1
 **/
public class EmptyException extends RuntimeException {

    EmptyException(String message) {
        //自定义异常
        super(message);
    }
}
