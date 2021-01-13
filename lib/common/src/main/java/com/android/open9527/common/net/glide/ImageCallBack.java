package com.android.open9527.common.net.glide;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.resource.gif.GifDrawable;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public interface ImageCallBack {

    default void onStart() {
    }

    default void onStop() {
    }

    default void onDestroy() {
    }

    default void onLoadStarted() {
    }

    default void onGifResourceReady(@NonNull GifDrawable resource) {
    }

    default void onResourceReady(@NonNull Bitmap resource) {
    }

    default void onLoadFailed() {
    }

    default void onLoadCleared() {
    }
}
