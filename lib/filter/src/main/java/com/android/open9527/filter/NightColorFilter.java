package com.android.open9527.filter;


import android.view.View;

import androidx.annotation.NonNull;

public interface  NightColorFilter {

    /**
     * 是否对该View逆转夜间模式
     *
     * @param view 需要判定的View
     * @return true-逆转该View
     */
    boolean excludeView(@NonNull View view);
}
