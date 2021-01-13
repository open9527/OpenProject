package com.android.open9527.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public class StateSwitchImageView extends LoadImageView {
    private static final String TAG = "StateSwitchImageView";


    private boolean select;

    private int defaultImageResId;
    private int selectedImageResId;

    private Drawable defaultImageDrawable;
    private Drawable selectedImageDrawable;

    private String defaultImageUrl;
    private String selectedImageUrl;

    public StateSwitchImageView(@NonNull Context context) {
        this(context, null, 0);
    }

    public StateSwitchImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateSwitchImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setImageResId();
        setImageDrawable();
        setImageUrl();
        Log.i(TAG, toString());
    }

    public void setSelect(boolean select) {
        this.select = select;

        setImageResId();
        setImageDrawable();
        setImageUrl();
        Log.i(TAG, toString());
    }

    public void setDefaultImageResId(int defaultImageResId) {
        this.defaultImageResId = defaultImageResId;
    }

    public void setSelectedImageResId(int selectedImageResId) {
        this.selectedImageResId = selectedImageResId;
    }

    public void setDefaultImageDrawable(Drawable defaultImageDrawable) {
        this.defaultImageDrawable = defaultImageDrawable;
    }

    public void setSelectedImageDrawable(Drawable selectedImageDrawable) {
        this.selectedImageDrawable = selectedImageDrawable;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
    }

    public void setSelectedImageUrl(String selectedImageUrl) {
        this.selectedImageUrl = selectedImageUrl;
    }

    @Override
    public String toString() {
        return "StateSwitchImageView{" +
                "select=" + select +
                ", defaultImageResId=" + defaultImageResId +
                ", selectedImageResId=" + selectedImageResId +
                ", defaultImageDrawable=" + defaultImageDrawable +
                ", selectedImageDrawable=" + selectedImageDrawable +
                ", defaultImageUrl='" + defaultImageUrl + '\'' +
                ", selectedImageUrl='" + selectedImageUrl + '\'' +
                '}';
    }

    private void setImageResId() {
        if (selectedImageResId > 0 || defaultImageResId > 0) {
            setImageResource(select ? selectedImageResId : defaultImageResId);

        }
//        setImageDrawable(ContextCompat.getDrawable(getContext(), select ? selectedImageResId : defaultImageResId));
    }

    private void setImageDrawable() {
        if (selectedImageDrawable != null || defaultImageDrawable != null) {
            setImageDrawable(select ? selectedImageDrawable : defaultImageDrawable);

        }

    }

    private void setImageUrl() {
        if (TextUtils.isEmpty(selectedImageUrl) || TextUtils.isEmpty(defaultImageUrl)) {
            return;
        }
        //TODO:
    }


}
