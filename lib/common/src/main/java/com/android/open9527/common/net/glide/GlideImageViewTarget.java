package com.android.open9527.common.net.glide;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public class GlideImageViewTarget extends ImageViewTarget<GifDrawable> {


    private static final String TAG = "GlideImageViewTarget";

    private ImageCallBack callBack;
    private int count = -1;

    public GlideImageViewTarget(@NonNull ImageView view, ImageCallBack callBack, int count) {
        super(view);
        this.callBack = callBack;
        this.count = count;
    }

    @Override
    protected void setResource(@Nullable GifDrawable resource) {
        Log.i(TAG, "setResource-->" + resource);
        if (resource != null) {
            resource.setLoopCount(count);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart-->" + "onStart");
        if (callBack != null) {
            callBack.onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.i(TAG, "onStop-->" + "onStop");

        if (callBack != null) {
            callBack.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy-->" + "onDestroy");
        if (callBack != null) {
            callBack.onDestroy();
        }
    }

    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {
        super.onLoadStarted(placeholder);

        Log.i(TAG, "onLoadStarted-->" + "onLoadStarted");

        if (callBack != null) {
            callBack.onLoadStarted();
        }
    }

    @Override
    public void onResourceReady(@NonNull GifDrawable resource, @Nullable Transition<? super GifDrawable> transition) {
        super.onResourceReady(resource, transition);

        Log.i(TAG, "onResourceReady-->" + "onResourceReady");

        if (callBack != null) {
            callBack.onGifResourceReady(resource);
        }
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);

        Log.i(TAG, "onLoadFailed-->" + "onLoadFailed");

        if (callBack != null) {
            callBack.onLoadFailed();
        }
    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {
        super.onLoadCleared(placeholder);

        Log.i(TAG, "onLoadCleared-->" + "onLoadCleared");

        if (callBack != null) {
            callBack.onLoadCleared();
        }
    }

    @Override
    public void setRequest(@Nullable Request request) {
        super.setRequest(request);
        Log.i(TAG, "setRequest-->" + "onLoadCleared");
    }
}
