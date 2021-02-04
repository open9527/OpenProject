package com.android.annotation.pkg.annotation.eg;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author open_9527
 * Create at 2021/2/4
 **/
public class InjectUtils {

    public static void injectLayout(Object context) {
        //获取Class对象，这里context就是传递过来的Activity
        Class<?> clazz = context.getClass();
        //获取@ContentView注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            //获取注解中的布局文件
            int layoutId = contentView.value();
            try {
                //利用反射调用Activity中的setContentView方法，将布局设置给MainActivity
                Method setContentViewMethod = clazz.getMethod("setContentView", int.class);
                setContentViewMethod.invoke(context, layoutId);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
