package com.open9527.annotation.router;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author open_9527
 * Create at 2021/3/1
 * <p>
 * eg:该类用于路由设置跳转路径
 **/


//@Retention用来修饰这是一个什么类型的注解。这里表示该注解是一个编译时注解。
@Retention(RetentionPolicy.CLASS)
//@Target用来表示这个注解可以使用在哪些地方。比如：类、方法、属性、接口等等。
//这里ElementType.TYPE 表示这个注解可以用来修饰：Class, interface or enum declaration。
@Target(ElementType.TYPE)
public @interface Router {

    /**
     * 路由的路径
     *
     * @return 字符串
     */
    String path();

    /**
     * 将路由节点进行分组，可以实现动态加载
     *
     * @return 字符串，默认值是 ""
     */
    String group() default "";

}
