package com.android.open9527.common.net.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public class GlideBitmapImageViewTarget extends BitmapImageViewTarget {

    private static final String TAG = "GlideImageViewTarget";

    private ImageCallBack callBack;

    public GlideBitmapImageViewTarget(@NonNull ImageView view, ImageCallBack callBack) {
        super(view);
        this.callBack = callBack;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (callBack != null) {
            callBack.onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (callBack != null) {
            callBack.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (callBack != null) {
            callBack.onDestroy();
        }
    }

    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {
        super.onLoadStarted(placeholder);

        if (callBack != null) {
            callBack.onLoadStarted();
        }
    }

    @Override
    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
        super.onResourceReady(resource, transition);
        if (callBack != null) {
            callBack.onResourceReady(resource);
        }
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);
        if (callBack != null) {
            callBack.onLoadFailed();
        }
    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {
        super.onLoadCleared(placeholder);
        if (callBack != null) {
            callBack.onLoadCleared();
        }
    }


}
