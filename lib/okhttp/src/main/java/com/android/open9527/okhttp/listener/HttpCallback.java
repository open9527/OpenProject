package com.android.open9527.okhttp.listener;

import okhttp3.Call;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public class HttpCallback<T> implements OnHttpListener<T> {

    private final OnHttpListener mListener;

    public HttpCallback(OnHttpListener listener) {
        mListener = listener;
    }

    @Override
    public void onStart(Call call) {
        if (mListener != null) {
            mListener.onStart(call);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSucceed(T result) {
        if (mListener != null) {
            mListener.onSucceed(result);
        }
    }

    @Override
    public void onFail(Exception e) {
        if (mListener != null) {
            mListener.onFail(e);
        }
    }

    @Override
    public void onEnd(Call call) {
        if (mListener != null) {
            mListener.onEnd(call);
        }
    }
}
