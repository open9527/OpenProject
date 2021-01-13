package com.android.open9527.common.binding.textview;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

public class TextViewBindingAdapter {

    /**
     * 配置text 文本,颜色
     *
     * @param textView     textView
     * @param text         text
     * @param textColorRes textColorRes
     */
    @BindingAdapter(value = {"bindText", "bindTextColor"}, requireAll = false)
    public static void setBindText(TextView textView, String text, int textColorRes) {
        if (textView == null) return;
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
        if (textColorRes > 0) {
            textView.setTextColor(ContextCompat.getColor(textView.getContext(), textColorRes));
        }
    }

    @SuppressLint("ResourceType")
    @BindingAdapter(value = {
            "bindTextDrawableStart",
            "bindTextDrawableTop",
            "bindTextDrawableEnd",
            "bindTextDrawableBottom",
            "bindTextDrawablePadding"
    }, requireAll = false)
    public static void setBindingTextDrawable(TextView textView,
                                              @DrawableRes int drawableStartRes,
                                              @DrawableRes int drawableTopRes,
                                              @DrawableRes int drawableEndRes,
                                              @DrawableRes int drawableBottomRes,
                                              int drawablePadding
    ) {
        if (textView != null) {
            Drawable drawableLeft = null;
            Drawable drawableTop = null;
            Drawable drawableEnd = null;
            Drawable drawableBottom = null;
            if (drawableStartRes > 0) {
                drawableLeft = ContextCompat.getDrawable(textView.getContext(), drawableStartRes);
            }
            if (drawableTopRes > 0) {
                drawableTop = ContextCompat.getDrawable(textView.getContext(), drawableTopRes);
            }
            if (drawableEndRes > 0) {
                drawableEnd = ContextCompat.getDrawable(textView.getContext(), drawableEndRes);
            }
            if (drawableBottomRes > 0) {
                drawableBottom = ContextCompat.getDrawable(textView.getContext(), drawableBottomRes);
            }
            textView.setCompoundDrawablePadding(drawablePadding);
            textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableEnd, drawableBottom);
        }
    }


}
