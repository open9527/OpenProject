package com.android.open9527.common.net.glide;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public final class ImageLoadConfig  {

    public static ImageLoadConfig with(@NonNull ImageView imageView) {
        return new ImageLoadConfig(imageView);
    }

    private ImageView imageView;

    /**
     * 图片url
     */
    private String url;

    /**
     * 图片圆角
     */
    private int radius;

    /**
     * 圆角类型
     */
    private RoundedCornersTransformation.CornerType cornerType;


    /**
     * 加载图片宽高尺寸
     */
    private int width;
    private int height;


    /**
     * 图片缓存策略
     */
    private DiskCacheStrategy strategy;

    /**
     * 加载前占位图
     */
    private int placeholderResourceId;

    /**
     * 加载失败后占位图
     */
    private int errorResourceId;

    /**
     * 无加载前占位图和 加载失败后占位图
     * 显示该图片
     */
    private Drawable fallbackDrawable;

    /**
     * 是否跳过内存缓存
     */
    private boolean skipMemoryCache;

    /**
     * 是否只加载缓存图片
     */
    private boolean onlyRetrieveFromCache;

    private boolean thumbnail;


    /**
     * 图片加载监听
     */
    private ImageCallBack callBack;


    private ImageLoadConfig(@NonNull ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageLoadConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public ImageLoadConfig setRadius(int radius) {
        this.radius = radius;

        return this;
    }

    public ImageLoadConfig setCornerType(RoundedCornersTransformation.CornerType cornerType) {
        this.cornerType = cornerType;

        return this;
    }

    public ImageLoadConfig setWidth(int width) {
        this.width = width;

        return this;
    }

    public ImageLoadConfig setHeight(int height) {
        this.height = height;
        return this;
    }

    public ImageLoadConfig setStrategy(DiskCacheStrategy strategy) {
        this.strategy = strategy;

        return this;
    }

    public ImageLoadConfig setPlaceholderResourceId(int placeholderResourceId) {
        this.placeholderResourceId = placeholderResourceId;

        return this;
    }

    public ImageLoadConfig setErrorResourceId(int errorResourceId) {
        this.errorResourceId = errorResourceId;

        return this;
    }

    public ImageLoadConfig setFallbackDrawable(Drawable fallbackDrawable) {
        this.fallbackDrawable = fallbackDrawable;

        return this;
    }

    public ImageLoadConfig setSkipMemoryCache(boolean skipMemoryCache) {
        this.skipMemoryCache = skipMemoryCache;

        return this;
    }

    public ImageLoadConfig setOnlyRetrieveFromCache(boolean onlyRetrieveFromCache) {
        this.onlyRetrieveFromCache = onlyRetrieveFromCache;

        return this;
    }

    public ImageLoadConfig setCallBack(ImageCallBack callBack) {
        this.callBack = callBack;

        return this;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public String getUrl() {
        return url;
    }

    public int getRadius() {
        return radius;
    }

    public RoundedCornersTransformation.CornerType getCornerType() {
        return cornerType;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DiskCacheStrategy getStrategy() {
        return strategy;
    }

    public int getPlaceholderResourceId() {
        return placeholderResourceId;
    }

    public int getErrorResourceId() {
        return errorResourceId;
    }

    public Drawable getFallbackDrawable() {
        return fallbackDrawable;
    }

    public boolean getSkipMemoryCache() {
        return skipMemoryCache;
    }

    public boolean getOnlyRetrieveFromCache() {
        return onlyRetrieveFromCache;
    }

    public ImageCallBack getCallBack() {
        return callBack;
    }


    @Override
    public String toString() {
        return "ImageLoadConfig{" +
                "imageView=" + imageView +
                ", url='" + url + '\'' +
                ", radius=" + radius +
                ", cornerType=" + cornerType +
                ", width=" + width +
                ", height=" + height +
                ", strategy=" + strategy +
                ", placeholderResourceId=" + placeholderResourceId +
                ", errorResourceId=" + errorResourceId +
                ", fallbackDrawable=" + fallbackDrawable +
                ", skipMemoryCache=" + skipMemoryCache +
                ", onlyRetrieveFromCache=" + onlyRetrieveFromCache +
                ", thumbnail=" + thumbnail +
                ", callBack=" + callBack +
                '}';
    }
}
