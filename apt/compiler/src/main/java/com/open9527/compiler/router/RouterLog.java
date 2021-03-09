package com.open9527.compiler.router;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;


/**
 * @author open_9527
 * Create at 2021/3/1
 **/
public class RouterLog {

    private Messager messager;

    private RouterLog(Messager messager) {
        this.messager = messager;
    }

    public static RouterLog newLog(Messager messager) {
        return new RouterLog(messager);
    }

    public void i(String msg) {
        messager.printMessage(Diagnostic.Kind.NOTE, msg);
    }
}
