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
 * 忽略注解（这个参数或者请求头将不会被发送到后台）
 **/

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface HttpIgnore {
}
