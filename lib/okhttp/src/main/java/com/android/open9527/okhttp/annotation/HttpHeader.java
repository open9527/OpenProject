package com.android.open9527.okhttp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 * 请求头注解（标记这个字段是一个请求头的参数）
 **/

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface HttpHeader {
}
