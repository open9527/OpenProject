package com.android.open9527.okhttp.callback;

import androidx.lifecycle.LifecycleOwner;

import com.android.open9527.okhttp.HttpLog;
import com.android.open9527.okhttp.HttpUtils;
import com.android.open9527.okhttp.config.IRequestHandler;
import com.android.open9527.okhttp.lifecycle.HttpLifecycleManager;
import com.android.open9527.okhttp.listener.OnHttpListener;
import com.android.open9527.okhttp.model.CallProxy;

import okhttp3.Response;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public final class NormalCallback extends BaseCallback {

    private final LifecycleOwner mLifecycle;
    private final OnHttpListener mListener;
    private final IRequestHandler mRequestHandler;

    public NormalCallback(LifecycleOwner lifecycleOwner, CallProxy call, IRequestHandler handler, OnHttpListener listener) {
        super(lifecycleOwner, call);
        mLifecycle = lifecycleOwner;
        mListener = listener;
        mRequestHandler = handler;

        HttpUtils.post(() -> {
            if (mListener == null || !HttpLifecycleManager.isLifecycleActive(getLifecycleOwner())) {
                return;
            }
            mListener.onStart(call);
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onResponse(Response response) throws Exception {
        HttpLog.print("RequestTime：" + (response.receivedResponseAtMillis() - response.sentRequestAtMillis()) + " ms");
        final Object result = mRequestHandler.requestSucceed(mLifecycle, response, HttpUtils.getReflectType(mListener));
        HttpUtils.post( () -> {
            if (mListener == null || !HttpLifecycleManager.isLifecycleActive(getLifecycleOwner())) {
                return;
            }
            mListener.onSucceed(result);
            mListener.onEnd(getCall());
        });
    }

    @Override
    protected void onFailure(Exception e) {
        HttpLog.print(e);
        final Exception exception = mRequestHandler.requestFail(mLifecycle, e);
        HttpUtils.post(() -> {
            if (mListener == null || !HttpLifecycleManager.isLifecycleActive(getLifecycleOwner())) {
                return;
            }
            mListener.onFail(exception);
            mListener.onEnd(getCall());
        });
    }
}