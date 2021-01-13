package com.android.open9527.common.page;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @author open_9527
 * Create at 2021/1/7
 **/
public interface ICommonPage {

    default void initStatusBar() {
    }

    default void initView(@Nullable Bundle bundle) {
    }

    default void initRequest() {
    }

    default void initEvent() {
    }
}
