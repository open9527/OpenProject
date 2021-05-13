package com.android.open9527.common.action;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author open_9527
 * Create at 2021/5/13
 *
 *
 **/
public class LifecycleHandler extends Handler implements LifecycleObserver {
    private static final String TAG = "LifecycleHandler";
    private LifecycleOwner mLifecycleOwner;

    public LifecycleHandler(@NonNull LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = lifecycleOwner;
        addObserver();
    }


    public LifecycleHandler(@NonNull Looper looper, @NonNull LifecycleOwner lifecycleOwner) {
        super(looper);
        this.mLifecycleOwner = lifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(@NonNull Looper looper, @NonNull Callback callback, @NonNull LifecycleOwner lifecycleOwner) {
        super(looper, callback);
        this.mLifecycleOwner = lifecycleOwner;
        addObserver();
    }

    private void addObserver() {
        mLifecycleOwner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        removeCallbacksAndMessages(null);
        mLifecycleOwner.getLifecycle().removeObserver(this);
    }
}
