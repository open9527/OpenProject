package com.android.annotation.pkg.annotation.eg;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author open_9527
 * Create at 2021/2/4
 * eg:
 * <p> 自定义了一个AnnotationTest注解，那下面我们建个类来看看如何使用。
 * <p> @Target是ElementType.TYPE，表示是作用在类、接口或者枚举上面的。
 * <p> @Retention是RetentionPolicy.RUNTIME，表示在运行时期。
 * <p> 内部定义了一个返回值为int的方法，表示使用注解时要传递一个int型的参数。
 **/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {
    int value();

}
