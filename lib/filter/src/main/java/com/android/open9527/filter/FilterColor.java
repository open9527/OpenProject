package com.android.open9527.filter;

import android.app.Activity;

import androidx.annotation.NonNull;

public abstract class FilterColor {
    /**
     *  着色
     *
     * @param activity 被着色对象
     */
    public abstract void tint(@NonNull Activity activity);


    /**
     *  擦除
     *
     * @param activity 被擦除对象
     */
    public abstract void clear(@NonNull Activity activity);
}
