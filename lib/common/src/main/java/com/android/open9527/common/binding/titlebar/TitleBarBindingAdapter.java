package com.android.open9527.common.binding.titlebar;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.BindingAdapter;

import com.android.open9527.titlebar.OnTitleBarListener;
import com.android.open9527.titlebar.TitleBar;
import com.android.open9527.titlebar.TitleBarConfig;
import com.android.open9527.titlebar.TitleBarInitialize;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ViewUtils;

/**
 * @author open_9527
 * Create at 2021/1/21
 **/
public class TitleBarBindingAdapter {


    @BindingAdapter(value =
            {

                    "bindTitleBarLeftText",
                    "bindTitleBarLeftTextSize",
                    "bindTitleBarLeftTextColor",
                    "bindTitleBarLeftDrawable",
                    "bindTitleBarLeftDrawableWidth",
                    "bindTitleBarLeftDrawableHigh",
                    "bindTitleBarLeftDrawablePadding",

                    "bindTitleBarTitleText",
                    "bindTitleBarTitleTextSize",
                    "bindTitleBarTitleTextColor",
                    "bindTitleBarTitleDrawable",
                    "bindTitleBarTitleDrawableWidth",
                    "bindTitleBarTitleDrawableHigh",
                    "bindTitleBarTitleDrawablePadding",

                    "bindTitleBarRightText",
                    "bindTitleBarRightTextSize",
                    "bindTitleBarRightTextColor",
                    "bindTitleBarRightDrawable",
                    "bindTitleBarRightDrawableWidth",
                    "bindTitleBarRightDrawableHigh",
                    "bindTitleBarRightDrawablePadding",

                    "bindTitleBarLineVisibility",
                    "bindTitleBarLineBg",
                    "bindTitleBarLineHigh",

                    "bindTitleBarDrawableBg",
                    "bindTitleBarListener",
                    "bindTitleBarInitialize",

                    "bindTitleBarStatusBarViewId",

            }
            , requireAll = false)
    public static void setBindingTitleBar
            (TitleBar titleBar,

             String leftText,
             int leftTextSize,
             int leftTextColor,
             Drawable leftDrawable,
             int leftDrawableWidth,
             int leftDrawableHigh,
             int leftDrawablePadding,

             String titleText,
             int titleTextSize,
             int titleTextColor,
             Drawable titleDrawable,
             int titleDrawableWidth,
             int titleDrawableHigh,
             int titleDrawablePadding,

             String rightText,
             int rightTextSize,
             int rightTextColor,
             Drawable rightDrawable,
             int rightDrawableWidth,
             int rightDrawableHigh,
             int rightDrawablePadding,

             int lineVisibility,
             int lineBg,
             int lineBgHigh,

             Drawable titleBarBgDrawable,
             OnTitleBarListener OnTitleBarListener,
             TitleBarInitialize titleBarInitialize,

             int statusBarViewId

            ) {
        if (titleBar == null) return;
        titleBar.setTitleBarConfig(
                TitleBarConfig.Builder()
                        .setLeftText(leftText)
                        .setLeftTextSize(leftTextSize)
                        .setLeftTextColor(leftTextColor)
                        .setLeftDrawable(leftDrawable)
                        .setLeftDrawableWidth(leftDrawableWidth)
                        .setLeftDrawableHigh(leftDrawableHigh)
                        .setLeftDrawablePadding(leftDrawablePadding)

                        .setTitleText(titleText)
                        .setTitleTextSize(titleTextSize)
                        .setTitleTextColor(titleTextColor)
                        .setTitleDrawable(titleDrawable)
                        .setTitleDrawableWidth(titleDrawableWidth)
                        .setTitleDrawableHigh(titleDrawableHigh)
                        .setTitleDrawablePadding(titleDrawablePadding)

                        .setRightText(rightText)
                        .setRightTextSize(rightTextSize)
                        .setRightTextColor(rightTextColor)
                        .setRightDrawable(rightDrawable)
                        .setRightDrawableWidth(rightDrawableWidth)
                        .setRightDrawableHigh(rightDrawableHigh)
                        .setRightDrawablePadding(rightDrawablePadding)
                        .setRightDrawablePadding(rightDrawablePadding)

                        .setLineVisibility(lineVisibility)
                        .setLineBg(lineBg)
                        .setLineBgHigh(lineBgHigh)
                        .setTitleBarBgDrawable(titleBarBgDrawable)
                        .setTitleBarListener(OnTitleBarListener)
                        .setTitleBarInitialize(titleBarInitialize));
        titleBar.init();

        if (statusBarViewId > 0) {
            View rootView = (ViewGroup) titleBar.getParent();
            if (rootView!=null){
                BarUtils.setStatusBarCustom(rootView.findViewById(statusBarViewId));
            }
        }


    }
}
