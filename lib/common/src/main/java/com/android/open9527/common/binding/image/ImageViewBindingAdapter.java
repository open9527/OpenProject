package com.android.open9527.common.binding.image;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.android.open9527.common.net.glide.ImageCallBack;
import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.net.glide.ImageLoadUtils;
import com.android.open9527.common.widget.LoadImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ImageViewBindingAdapter {

    @BindingAdapter(value = {"bindIvResId", "bindIvDrawable", "bindIvColor",}, requireAll = false)
    public static void setBindingIvResId(ImageView imageView, int resId, Drawable drawable, int color) {
        if (imageView == null) return;

        if (resId > 0) {
            imageView.setImageResource(resId);

        } else if (drawable != null) {
            imageView.setImageDrawable(drawable);

        } else if (color > 0) {
            imageView.setImageDrawable(new ColorDrawable(ContextCompat.getColor(imageView.getContext(), color)));
        }


    }


    @BindingAdapter(value = {"bindIvSelect", "bindIvSelectResId", "bindIvUnSelectResId"}, requireAll = false)
    public static void setBindingImageSelect(ImageView imageView, boolean ivSelect, int selectResId, int unSelectResId) {
        if (imageView == null) return;
        imageView.setImageResource(ivSelect ? selectResId : unSelectResId);
    }


    @BindingAdapter(value = {"bindIvUrl", "bindIvRadius", "bindIvCornerType", "bindIvWidth",
            "bindIvHeight", "bindIvFallbackDrawable", "bindIvPlaceholderResourceId",
            "bindIvErrorResourceId", "bindIvOnlyRetrieveFromCache", "bindIvSkipMemoryCache",
            "bindIvDiskCacheStrategy", "bindIvImageCallBack",}, requireAll = false)
    public static void setBindingImageViewAdapter(ImageView imageView,
                                                  String url, int radius, RoundedCornersTransformation.CornerType cornerType,
                                                  int width, int height, Drawable fallbackDrawable, int placeholderResourceId,
                                                  int errorResourceId, boolean onlyRetrieveFromCache, boolean skipMemoryCache,
                                                  DiskCacheStrategy strategy, ImageCallBack imageCallBack) {
        if (imageView == null) return;

        ImageLoadUtils.loadImage(ImageLoadConfig.with(imageView)
                .setUrl(url)
                .setRadius(radius)
                .setCornerType(cornerType)
                .setWidth(width)
                .setHeight(height)
                .setFallbackDrawable(fallbackDrawable)
                .setPlaceholderResourceId(placeholderResourceId)
                .setErrorResourceId(errorResourceId)
                .setOnlyRetrieveFromCache(onlyRetrieveFromCache)
                .setSkipMemoryCache(skipMemoryCache)
                .setStrategy(strategy)
                .setCallBack(imageCallBack)
        );
    }


    @BindingAdapter(value = {"bindIvUrl", "bindIvRadius", "bindIvCornerType", "bindIvWidth",
            "bindIvHeight", "bindIvFallbackDrawable", "bindIvPlaceholderResourceId",
            "bindIvErrorResourceId", "bindIvOnlyRetrieveFromCache", "bindIvSkipMemoryCache",
            "bindIvDiskCacheStrategy", "bindIvImageCallBack",}, requireAll = false)
    public static void setBindingLoadImageViewAdapter(LoadImageView loadImageView,
                                                      String url, int radius, RoundedCornersTransformation.CornerType cornerType,
                                                      int width, int height, Drawable fallbackDrawable, int placeholderResourceId,
                                                      int errorResourceId, boolean onlyRetrieveFromCache, boolean skipMemoryCache,
                                                      DiskCacheStrategy strategy, ImageCallBack imageCallBack) {
        if (loadImageView == null) return;
        loadImageView.setImageLoadConfig(ImageLoadConfig.with(loadImageView)
                .setUrl(url)
                .setRadius(radius)
                .setCornerType(cornerType)
                .setWidth(width)
                .setHeight(height)
                .setFallbackDrawable(fallbackDrawable)
                .setPlaceholderResourceId(placeholderResourceId)
                .setErrorResourceId(errorResourceId)
                .setOnlyRetrieveFromCache(onlyRetrieveFromCache)
                .setSkipMemoryCache(skipMemoryCache)
                .setStrategy(strategy)
                .setCallBack(imageCallBack));
        loadImageView.onLoad();
    }
}
