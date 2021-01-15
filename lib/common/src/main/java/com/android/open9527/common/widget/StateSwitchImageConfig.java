package com.android.open9527.common.widget;

import android.graphics.drawable.Drawable;

/**
 * @author open_9527
 * Create at 2021/1/14
 **/
public final class StateSwitchImageConfig {

    private boolean select;

    private int defaultImageResId;
    private int selectedImageResId;

    private Drawable defaultImageDrawable;
    private Drawable selectedImageDrawable;

    /*网络图片*/
//    private String defaultImageUrl;
//    private String selectedImageUrl;

    private StateSwitchImageConfig() {

    }

    public static StateSwitchImageConfig Builder() {
        return new StateSwitchImageConfig();
    }


    public StateSwitchImageConfig setSelect(boolean select) {
        this.select = select;
        return this;
    }

    public StateSwitchImageConfig setDefaultImageResId(int defaultImageResId) {
        this.defaultImageResId = defaultImageResId;
        return this;
    }

    public StateSwitchImageConfig setSelectedImageResId(int selectedImageResId) {
        this.selectedImageResId = selectedImageResId;
        return this;
    }

    public StateSwitchImageConfig setDefaultImageDrawable(Drawable defaultImageDrawable) {
        this.defaultImageDrawable = defaultImageDrawable;
        return this;
    }

    public StateSwitchImageConfig setSelectedImageDrawable(Drawable selectedImageDrawable) {
        this.selectedImageDrawable = selectedImageDrawable;
        return this;
    }

    /*get*/

    public boolean getSelect() {
        return select;
    }

    public int getDefaultImageResId() {
        return defaultImageResId;
    }

    public int getSelectedImageResId() {
        return selectedImageResId;
    }

    public Drawable getDefaultImageDrawable() {
        return defaultImageDrawable;
    }

    public Drawable getSelectedImageDrawable() {
        return selectedImageDrawable;
    }


    @Override
    public String toString() {
        return "StateSwitchImageConfig{" +
                "select=" + select +
                ", defaultImageResId=" + defaultImageResId +
                ", selectedImageResId=" + selectedImageResId +
                ", defaultImageDrawable=" + defaultImageDrawable +
                ", selectedImageDrawable=" + selectedImageDrawable +
                '}';
    }
}
