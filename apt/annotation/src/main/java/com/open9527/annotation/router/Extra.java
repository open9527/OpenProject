package com.open9527.annotation.router;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author open_9527
 * Create at 2021/3/1
 **/

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface Extra {

    String name() default "";
    
}
