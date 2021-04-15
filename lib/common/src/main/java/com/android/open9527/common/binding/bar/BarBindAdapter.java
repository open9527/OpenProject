package com.android.open9527.common.binding.bar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.BarUtils;

public class BarBindAdapter {


    @BindingAdapter(value = {
            "bindViewStatusBar",
    },
            requireAll = false)
    public static void setBindingStatusBar(@NonNull View view, int height) {
        BarUtils.setStatusBarCustom(view);
    }
}
