package com.android.annotation.pkg.annotation.eg;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author open_9527
 * Create at 2021/2/4
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface ContentView {

    int value();
}
