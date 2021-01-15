package com.android.open9527.common.binding.view;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

/**
 * @author open_9527
 * Create at 2021/1/11
 **/
public class ViewBindingAdapter {

    @SuppressLint("ResourceType")
    @BindingAdapter(value =
            {
                    "bindViewBgColor",
                    "bindViewBgDrawable",
                    "bindViewBgDrawableResId"
            },
            requireAll = false)
    public static void setBindingViewBg
            (
                    View view,
                    @ColorRes int colorBg,
                    Drawable drawableBg,
                    @DrawableRes int DrawableResIdBg
            ) {
        if (view == null) return;

        if (colorBg > 0) {
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(), colorBg));

        } else if (drawableBg != null) {
            view.setBackground(drawableBg);

        } else if (DrawableResIdBg > 0) {
            view.setBackgroundResource(DrawableResIdBg);
        }

    }

    @BindingAdapter(value = {"bindViewVisibility"}, requireAll = false)
    public static void setBindingViewVisibility(View view, int visibility) {
        if (view == null) return;
        view.setVisibility(visibility);

    }

    @BindingAdapter(value = {"bindViewIsVisibility"}, requireAll = false)
    public static void setBindingViewIsVisibility(View view, boolean isVisibility) {
        if (view == null) return;
        view.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
    }


    @BindingAdapter(value = {"bindViewHeight"}, requireAll = false)
    public static void setBindingViewHeight(View view, int viewHeight) {
        if (view == null) return;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = viewHeight;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter(value = {"bindViewWidth"}, requireAll = false)
    public static void setBindingViewWidth(View view, int viewWidth) {
        if (view == null) return;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = viewWidth;
        view.setLayoutParams(layoutParams);
    }


    @BindingAdapter(value = {"bindViewAdjustWidth"})
    public static void setBindingViewAdjustWidth(View view, int adjustWidth) {
        if (view == null) return;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = adjustWidth;
        view.setLayoutParams(params);
    }

    @BindingAdapter(value = {"bindViewAdjustHeight"})
    public static void setBindingViewAdjustHeight(View view, int adjustHeight) {
        if (view == null) return;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = adjustHeight;
        view.setLayoutParams(params);
    }


}
