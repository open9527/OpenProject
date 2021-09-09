package com.android.custom.pkg.aop;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.core.content.ContextCompat;


import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class CheckNetAspect {

    /**
     * 方法切入点
     */
    @Pointcut("execution(@com.android.custom.pkg.aop.CheckNet * *(..))")
    public void method() {
    }

    /**
     * 在连接点进行方法替换
     */
    @Around("method() && @annotation(checkNet)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, CheckNet checkNet) throws Throwable {
        Application application = Utils.getApp();
        if (application != null) {
            ConnectivityManager manager = ContextCompat.getSystemService(application, ConnectivityManager.class);
            if (manager != null) {
                NetworkInfo info = manager.getActiveNetworkInfo();
                // 判断网络是否连接
                if (info == null || !info.isConnected()) {
                    ToastUtils.showLong("当前没有网络连接，请检查网络设置");
                    return;
                }
            }
        }
        //执行原方法
        joinPoint.proceed();
    }
}