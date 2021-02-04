package com.android.open9527.common.binding.image;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.databinding.BindingAdapter;

import com.android.open9527.common.net.glide.ImageCallBack;
import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.widget.LoadImageView;
import com.android.open9527.common.widget.StateSwitchImageConfig;
import com.android.open9527.common.widget.StateSwitchImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ImageViewBindingAdapter {
    @BindingAdapter(value = {"bindIvUrl", "bindIvUri","bindIvRadius", "bindIvCornerType", "bindIvWidth",
            "bindIvHeight", "bindIvFallbackDrawable", "bindIvPlaceholderResourceId",
            "bindIvErrorResourceId", "bindIvOnlyRetrieveFromCache", "bindIvSkipMemoryCache",
            "bindIvDiskCacheStrategy", "bindIvImageCallBack",}, requireAll = false)
    public static void setBindingLoadImageViewAdapter(LoadImageView loadImageView,
                                                      String url, Uri uri, int radius, RoundedCornersTransformation.CornerType cornerType,
                                                      int width, int height, Drawable fallbackDrawable, int placeholderResourceId,
                                                      int errorResourceId, boolean onlyRetrieveFromCache, boolean skipMemoryCache,
                                                      DiskCacheStrategy strategy, ImageCallBack imageCallBack) {
        if (loadImageView == null) return;
        loadImageView.setImageLoadConfig(ImageLoadConfig.with(loadImageView)
                .setUrl(url)
                .setUri(uri)
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
        loadImageView.init();
    }


    @BindingAdapter(value = {"bindIvDefaultImageResId", "bindIvSelectedImageResId",
            "bindIvDefaultImageDrawable", "bindIvSelectedImageDrawable", "bindIvSelect"}, requireAll = false)
    public static void setBindingStateSwitchImageView(StateSwitchImageView imageView,
                                                      int defaultImageResId, int selectedImageResId
            , Drawable defaultImageDrawable, Drawable selectedImageDrawable, boolean select
    ) {
        if (imageView != null) {
            imageView.setStateSwitchImageConfig(StateSwitchImageConfig.Builder()
                    .setDefaultImageResId(defaultImageResId)
                    .setSelectedImageResId(selectedImageResId)
                    .setDefaultImageDrawable(defaultImageDrawable)
                    .setSelectedImageDrawable(selectedImageDrawable)
                    .setSelect(select));
            imageView.init();
        }


    }
}
