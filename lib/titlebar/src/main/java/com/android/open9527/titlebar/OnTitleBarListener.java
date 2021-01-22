package com.android.open9527.titlebar;

import android.view.View;

/**
 * @author open_9527
 * Create at 2021/1/20
 **/
public interface OnTitleBarListener {

    /**
     * 左项被点击
     *
     * @param v 被点击的左项View
     */
    default void onLeftClick(View v) {
    }

    /**
     * 标题被点击
     *
     * @param v 被点击的标题View
     */
    default void onTitleClick(View v) {
    }

    /**
     * 右项被点击
     *
     * @param v 被点击的右项View
     */
    default void onRightClick(View v) {
    }
}
