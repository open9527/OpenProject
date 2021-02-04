package com.android.open9527.common.net.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public final class ImageLoadUtils {

    @SuppressLint("CheckResult")
    public static void loadImage(@NonNull ImageLoadConfig imageLoadConfig) {

        RequestOptions requestOptions = new RequestOptions();

        //占位图 正在请求图片的时候展示的图片
        if (imageLoadConfig.getPlaceholderResourceId() > 0) {
            requestOptions.placeholder(imageLoadConfig.getPlaceholderResourceId());
        }

        //错误图 如果请求失败的时候展示的图片 （如果没有设置，还是展示placeholder的占位符）

        if (imageLoadConfig.getErrorResourceId() > 0) {
            requestOptions.error(imageLoadConfig.getErrorResourceId());
        }


        // 如果请求的url/model为 null 的时候展示的图片 （如果没有设置，还是展示placeholder的占位符）
        if (imageLoadConfig.getFallbackDrawable() != null) {
            requestOptions.fallback(imageLoadConfig.getFallbackDrawable());
        }

        //磁盘缓存策略() DiskCacheStrategy.RESOURCE
        if (imageLoadConfig.getStrategy() != null) {
            requestOptions.diskCacheStrategy(imageLoadConfig.getStrategy());
        }

        //是否跳过内存缓存
        requestOptions.skipMemoryCache(imageLoadConfig.getSkipMemoryCache());

        //是否只加载缓存图片
        requestOptions.onlyRetrieveFromCache(imageLoadConfig.getOnlyRetrieveFromCache());

        //配置图片宽高(px)
        if (imageLoadConfig.getWidth() > 0 && imageLoadConfig.getHeight() > 0) {
            requestOptions.override(imageLoadConfig.getWidth(), imageLoadConfig.getHeight());
        }
        //配置图片圆角
        if (imageLoadConfig.getRadius() > 0 && imageLoadConfig.getCornerType() != null) {
            requestOptions.apply(getBitmapTransform(imageLoadConfig.getRadius(), 0, imageLoadConfig.getCornerType()));
        }
        GlideApp.with(imageLoadConfig.getImageView())
                .asBitmap()
                .load(imageLoadConfig.getUri() == null ? imageLoadConfig.getUrl() : imageLoadConfig.getUri())
                .apply(requestOptions)
                .into(new GlideBitmapImageViewTarget(imageLoadConfig.getImageView(), imageLoadConfig.getCallBack()));
    }

    @SuppressLint("CheckResult")
    public static void loadGifImage(@NonNull ImageLoadConfig imageLoadConfig) {
        RequestOptions requestOptions = new RequestOptions();

        //占位图 正在请求图片的时候展示的图片
        if (imageLoadConfig.getPlaceholderResourceId() > 0) {
            requestOptions.placeholder(imageLoadConfig.getPlaceholderResourceId());
        }

        //错误图 如果请求失败的时候展示的图片 （如果没有设置，还是展示placeholder的占位符）

        if (imageLoadConfig.getErrorResourceId() > 0) {
            requestOptions.error(imageLoadConfig.getErrorResourceId());
        }


        // 如果请求的url/model为 null 的时候展示的图片 （如果没有设置，还是展示placeholder的占位符）
        if (imageLoadConfig.getFallbackDrawable() != null) {
            requestOptions.fallback(imageLoadConfig.getFallbackDrawable());
        }

        //磁盘缓存策略() DiskCacheStrategy.RESOURCE
        if (imageLoadConfig.getStrategy() != null) {
            requestOptions.diskCacheStrategy(imageLoadConfig.getStrategy());
        }

        //是否跳过内存缓存
        requestOptions.skipMemoryCache(imageLoadConfig.getSkipMemoryCache());

        //是否只加载缓存图片
        requestOptions.onlyRetrieveFromCache(imageLoadConfig.getOnlyRetrieveFromCache());

        //配置图片圆角
        if (imageLoadConfig.getRadius() > 0 && imageLoadConfig.getCornerType() != null) {
            requestOptions.apply(getBitmapTransform(imageLoadConfig.getRadius(), 0, imageLoadConfig.getCornerType()));
        }
        //配置图片宽高(px)
        if (imageLoadConfig.getWidth() > 0 && imageLoadConfig.getHeight() > 0) {
            requestOptions.override(imageLoadConfig.getWidth(), imageLoadConfig.getHeight());
        }

        GlideApp.with(imageLoadConfig.getImageView())
                .asGif()
                .load(imageLoadConfig.getUrl())
                .apply(requestOptions)
                .into(new GlideImageViewTarget(imageLoadConfig.getImageView(), imageLoadConfig.getCallBack(), -1));
    }


    public static RequestOptions getBitmapTransform(
            int radius, int margin,
            RoundedCornersTransformation.CornerType cornerType) {
        return RequestOptions.bitmapTransform(getTransformation(radius, margin, cornerType));
    }


    public static Transformation<Bitmap> getTransformation(
            int radius, int margin,
            RoundedCornersTransformation.CornerType cornerType) {
        return new MultiTransformation<>(
                new CenterCrop(),
                new RoundedCornersTransformation(radius, margin, cornerType));
    }


    public static void resumeLoad(@NonNull View view) {
        GlideApp.with(view).resumeRequests();
    }

    public static void pauseLoad(@NonNull View view) {
        GlideApp.with(view).pauseRequests();
    }

    public static void clearImageView(@NonNull ImageView imageView) {
        GlideApp.with(imageView).clear(imageView);
    }

    public static void clear(@NonNull Context context) {
        //ByIo
        GlideApp.get(context).clearDiskCache();
        //runOnUiThread
//        GlideApp.get(context).clearMemory();
    }

}
