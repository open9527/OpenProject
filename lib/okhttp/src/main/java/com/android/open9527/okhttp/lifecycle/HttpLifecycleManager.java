package com.android.open9527.okhttp.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.android.open9527.okhttp.OkHttpUtils;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public final class HttpLifecycleManager implements LifecycleEventObserver {

    /**
     * 绑定组件的生命周期
     */
    public static void bind(LifecycleOwner lifecycleOwner) {
        lifecycleOwner.getLifecycle().addObserver(new HttpLifecycleManager());
    }

    /**
     * 判断宿主是否处于活动状态
     */
    public static boolean isLifecycleActive(LifecycleOwner lifecycleOwner) {
        return lifecycleOwner != null && lifecycleOwner.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED;
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            // 移除监听
            source.getLifecycle().removeObserver(this);
            // 取消请求
            OkHttpUtils.cancel(source);
        }
    }
}