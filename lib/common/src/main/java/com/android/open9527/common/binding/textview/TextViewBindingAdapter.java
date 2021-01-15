package com.android.open9527.common.binding.textview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.android.open9527.common.widget.StateSwitchTextConfig;
import com.android.open9527.common.widget.StateSwitchTextView;

public class TextViewBindingAdapter {

//    /**
//     * 配置text 文本,颜色
//     *
//     * @param textView     textView
//     * @param text         text
//     * @param textColorRes textColorRes
//     */
//    @BindingAdapter(value = {"bindText", "bindTextColor"}, requireAll = false)
//    public static void setBindText(TextView textView, String text, int textColorRes) {
//        if (textView == null) return;
//        if (!TextUtils.isEmpty(text)) {
//            textView.setText(text);
//        }
//        if (textColorRes > 0) {
//            textView.setTextColor(getColor(textView.getContext(), textColorRes));
//        }
//    }
//
//    @SuppressLint("ResourceType")
//    @BindingAdapter(value = {
//            "bindTextDrawableStart",
//            "bindTextDrawableTop",
//            "bindTextDrawableEnd",
//            "bindTextDrawableBottom",
//            "bindTextDrawablePadding"
//    }, requireAll = false)
//    public static void setBindingTextDrawable(TextView textView,
//                                              @DrawableRes int drawableStartRes,
//                                              @DrawableRes int drawableTopRes,
//                                              @DrawableRes int drawableEndRes,
//                                              @DrawableRes int drawableBottomRes,
//                                              int drawablePadding
//    ) {
//        if (textView != null) {
//            Drawable drawableLeft = null;
//            Drawable drawableTop = null;
//            Drawable drawableEnd = null;
//            Drawable drawableBottom = null;
//            if (drawableStartRes > 0) {
//                drawableLeft = getDrawable(textView.getContext(), drawableStartRes);
//            }
//            if (drawableTopRes > 0) {
//                drawableTop = getDrawable(textView.getContext(), drawableTopRes);
//            }
//            if (drawableEndRes > 0) {
//                drawableEnd = getDrawable(textView.getContext(), drawableEndRes);
//            }
//            if (drawableBottomRes > 0) {
//                drawableBottom = getDrawable(textView.getContext(), drawableBottomRes);
//            }
//            textView.setCompoundDrawablePadding(drawablePadding);
//            textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableEnd, drawableBottom);
//        }
//    }

    @SuppressLint("ResourceType")
    @BindingAdapter(value = {
            "bindTextDefaultText",
            "bindTextSelectedText",
            "bindTextDefaultTextColor",
            "bindTextSelectedTextColor",
            "bindTextDefaultTextSize",
            "bindTextSelectedTextSize",
            "bindTextDefaultDrawableLeft",
            "bindTextSelectedDrawableLeft",
            "bindTextDefaultDrawableTop",
            "bindTextSelectedDrawableTop",
            "bindTextDefaultDrawableRight",
            "bindTextSelectedDrawableRight",
            "bindTextDefaultDrawableBottom",
            "bindTextSelectedDrawableBottom",
            "bindTextDrawablePadding",
            "bindTextDrawableWidth",
            "bindTextDrawableHigh",
            "bindTextDefaultTextFont",
            "bindTextSelectedTextFont",
            "bindTextDefaultFakeBoldText",
            "bindTextSelectedFakeBoldText",
            "bindTextSelect",
    }, requireAll = false)
    public static void setBindingStateSwitchTextView
            (
                    StateSwitchTextView textView,
                    String defaultText,
                    String selectedText,
                    @ColorRes int defaultTextColor,
                    @ColorRes int selectedTextColor,
                    int defaultTextSize,
                    int selectedTextSize,
                    @DrawableRes int defaultDrawableLeftRes,
                    @DrawableRes int selectedDrawableLeftRes,
                    @DrawableRes int defaultDrawableTopRes,
                    @DrawableRes int selectedDrawableTopRes,
                    @DrawableRes int defaultDrawableRightRes,
                    @DrawableRes int selectedDrawableRightRes,
                    @DrawableRes int defaultDrawableBottomRes,
                    @DrawableRes int selectedDrawableBottomRes,
                    int drawablePadding,
                    int drawableWidth,
                    int drawableHigh,
                    String defaultTextFont,
                    String selectedTextFont,
                    boolean defaultFakeBoldText,
                    boolean selectedFakeBoldText,
                    boolean select
            ) {
        if (textView != null) {
            textView.setStateSwitchConfig(
                    StateSwitchTextConfig.Builder()
                            .setDefaultText(defaultText)
                            .setSelectedText(selectedText)
                            .setDefaultTextColor(defaultTextColor)
                            .setSelectedTextColor(selectedTextColor)
                            .setDefaultTextSize(defaultTextSize)
                            .setSelectedTextSize(selectedTextSize)
                            .setDefaultLeftImage(getDrawable(textView.getContext(), defaultDrawableLeftRes))
                            .setSelectedLeftImage(getDrawable(textView.getContext(), selectedDrawableLeftRes))
                            .setDefaultTopImage(getDrawable(textView.getContext(), defaultDrawableTopRes))
                            .setSelectedTopImage(getDrawable(textView.getContext(), selectedDrawableTopRes))
                            .setDefaultRightImage(getDrawable(textView.getContext(), defaultDrawableRightRes))
                            .setSelectedRightImage(getDrawable(textView.getContext(), selectedDrawableRightRes))
                            .setDefaultBottomImage(getDrawable(textView.getContext(), defaultDrawableBottomRes))
                            .setSelectedBottomImage(getDrawable(textView.getContext(), selectedDrawableBottomRes))
                            .setDrawablePadding(drawablePadding)
                            .setDrawableWidth(drawableWidth)
                            .setDrawableHigh(drawableHigh)
                            .setDefaultTextFont(defaultTextFont)
                            .setSelectedTextFont(selectedTextFont)
                            .setDefaultFakeBoldText(defaultFakeBoldText)
                            .setSelectedFakeBoldText(selectedFakeBoldText)
                            .setSelect(select)
            );
            textView.init();
        }
    }


    @SuppressLint("ResourceType")
    private static Drawable getDrawable(Context context, @DrawableRes int drawableRes) {
        if (drawableRes > 0) {
            return ContextCompat.getDrawable(context, drawableRes);
        }
        return null;
    }

    private static int getColor(Context context, @ColorRes int colorRes) {
        return ContextCompat.getColor(context, colorRes);
    }


}
