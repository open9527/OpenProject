package com.android.open9527.filter;

import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;

public class FilterUtil {

    private FilterUtil() throws IllegalAccessException {
        throw new IllegalAccessException("DressUtil is a util class.");
    }

    /**
     * @param view 需要被判断的View
     * @return true - 参数 view 是虚拟按键的背景View
     */
    public static boolean isNavigationBarBackground(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return view.getId() == android.R.id.navigationBarBackground;
        } else {
            return false;
        }
    }

    /**
     * @param view 需要被判断的View
     * @return true - 参数 view 是状态栏的背景View
     */
    public static boolean isStatusBrBackground(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return view.getId() == android.R.id.statusBarBackground;
        } else {
            return false;
        }
    }


}
