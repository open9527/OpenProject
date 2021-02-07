package com.android.open9527.image.pkg.album;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author open_9527
 * Create at 2021/1/27
 **/
public class LifecycleHandler extends Handler implements LifecycleObserver {

    private static final String TAG = "LifecycleHandler";
    private LifecycleOwner mlifecycleOwner;


    public LifecycleHandler(LifecycleOwner mlifecycleOwner) {
        this.mlifecycleOwner = mlifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(Callback callback, LifecycleOwner mlifecycleOwner) {
        super(callback);
        this.mlifecycleOwner = mlifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(Looper looper, LifecycleOwner mlifecycleOwner) {
        super(looper);
        this.mlifecycleOwner = mlifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(Looper looper, Callback callback, LifecycleOwner mlifecycleOwner) {
        super(looper, callback);
        this.mlifecycleOwner = mlifecycleOwner;
        addObserver();
    }

    private void addObserver() {
        mlifecycleOwner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestory() {
        removeCallbacks(null);
        LogUtils.i(TAG, "onDestory");
        mlifecycleOwner.getLifecycle().removeObserver(this);
    }
}
