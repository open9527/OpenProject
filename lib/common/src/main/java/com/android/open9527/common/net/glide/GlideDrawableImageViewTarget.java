package com.android.open9527.common.net.glide;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.open9527.glide.webp.WebpDrawable;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;


/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public class GlideDrawableImageViewTarget extends DrawableImageViewTarget {


    private static final String TAG = "GlideDrawableImageViewTarget";

    private ImageCallBack callBack;
    private int count = -1;

    public GlideDrawableImageViewTarget(@NonNull ImageView view, ImageCallBack callBack, int count) {
        super(view);
        this.callBack = callBack;
        this.count = count;
    }

    @Override
    protected void setResource(@Nullable Drawable resource) {
        super.setResource(resource);
        if (resource instanceof Animatable) {
            if (resource instanceof GifDrawable) {
                GifDrawable gifDrawable = (GifDrawable) resource;
                gifDrawable.setLoopCount(count);
            }
            if (resource instanceof WebpDrawable) {
                WebpDrawable gifDrawable = (WebpDrawable) resource;
                gifDrawable.setLoopCount(count);
            }

        }
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
    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
        LogUtils.i(TAG, "onResourceReady");
//        setResource(resource);
        super.onResourceReady(resource, transition);
        if (callBack != null) {
            if (resource instanceof Animatable) {
                if (resource instanceof GifDrawable) {
                    GifDrawable gifDrawable = (GifDrawable) resource;
                    callBack.onGifResourceReady(gifDrawable);
                }
                if (resource instanceof WebpDrawable) {
                    WebpDrawable webpDrawable = (WebpDrawable) resource;
                    callBack.onWebpResourceReady(webpDrawable);
                }

            } else {
                callBack.onResourceReady(resource);
            }
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
