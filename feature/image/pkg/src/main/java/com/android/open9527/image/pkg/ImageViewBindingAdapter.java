package com.android.open9527.image.pkg;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.android.open9527.common.net.glide.ImageCallBack;
import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.widget.LoadImageView;
import com.android.open9527.common.widget.StateSwitchImageConfig;
import com.android.open9527.common.widget.StateSwitchImageView;
import com.android.open9527.image.pkg.preview.LoadPhotoView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ImageViewBindingAdapter {
    @BindingAdapter(value = {"bindIvUrl", "bindIvRadius", "bindIvCornerType", "bindIvWidth",
            "bindIvHeight", "bindIvFallbackDrawable", "bindIvPlaceholderResourceId",
            "bindIvErrorResourceId", "bindIvOnlyRetrieveFromCache", "bindIvSkipMemoryCache",
            "bindIvDiskCacheStrategy", "bindIvImageCallBack", "bindPhotoViewClick", "bindPhotoViewLongClick"}, requireAll = false)
    public static void setBindingLoadImageViewAdapter(LoadPhotoView loadImageView,
                                                      String url, int radius, RoundedCornersTransformation.CornerType cornerType,
                                                      int width, int height, Drawable fallbackDrawable, int placeholderResourceId,
                                                      int errorResourceId, boolean onlyRetrieveFromCache, boolean skipMemoryCache,
                                                      DiskCacheStrategy strategy, ImageCallBack imageCallBack, View.OnClickListener clickListener
            , View.OnLongClickListener longClickListener
    ) {
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
        loadImageView.init(clickListener, longClickListener);
    }
}
