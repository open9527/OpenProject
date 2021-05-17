package com.android.open9527.common.binding.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.databinding.BindingAdapter;

import com.android.open9527.common.net.glide.ImageCallBack;
import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.widget.image.LoadImageView;
import com.android.open9527.common.widget.image.RoundImageType;
import com.android.open9527.common.widget.image.RoundImageView;
import com.android.open9527.common.widget.image.StateSwitchImageConfig;
import com.android.open9527.common.widget.image.StateSwitchImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ImageViewBindingAdapter {
    @BindingAdapter(value = {"bindIvUrl", "bindIvUri", "bindIvRadius", "bindIvCornerType", "bindIvWidth",
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


    @BindingAdapter(value = {
            "bindIvDefaultImageResId",
            "bindIvSelectedImageResId",
            "bindIvDefaultImageDrawable",
            "bindIvSelectedImageDrawable",
            "bindIvSelect"},
            requireAll = false)
    public static void setBindingStateSwitchImageView(
            StateSwitchImageView imageView,
            int defaultImageResId,
            int selectedImageResId,
            Drawable defaultImageDrawable,
            Drawable selectedImageDrawable,
            boolean select
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

    @BindingAdapter(value = {"bindIvRoundType", "bindCornerType", "bindIvBorderRadius", "bindIvRoundRadius",
            "bindIvBorderColor", "bindIvBorderWidth"},
            requireAll = false)
    public static void setBindingStateSwitchImageView(RoundImageView imageView,
                                                      @RoundImageType.RoundType String roundType,
                                                      @RoundImageType.CornerType String cornerType,
                                                      int borderRadius, int roundRadius, @ColorInt Integer color, int borderWidth) {
        if (imageView == null) return;
        if (roundType != null) {
            imageView.setRoundType(roundType);
        }
        if (cornerType != null) {
            imageView.setCornerType(cornerType);
        }
        if (color != null) {
            imageView.setBorderColor(color);
        }
        imageView.setBorderRadius(borderRadius);
        imageView.setRoundRadius(roundRadius);
        imageView.setBorderWidth(borderWidth);
    }


    @BindingAdapter(value = {"bindIvBitmap"}, requireAll = false)
    public static void setBindingImageView(ImageView imageView, Bitmap bitmap) {
        if (imageView == null || bitmap == null) return;
        imageView.setImageBitmap(bitmap);
    }

}
