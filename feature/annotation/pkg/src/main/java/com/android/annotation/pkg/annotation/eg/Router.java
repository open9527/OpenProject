package com.android.annotation.pkg.annotation.eg;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author open_9527
 * Create at 2021/2/4
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Router {
    /**
     * 路由的路径
     * @return              字符串
     */
    String path();

    /**
     * 将路由节点进行分组，可以实现动态加载
     * @return              字符串，默认值是 ""
     */
    String group() default "";
}
