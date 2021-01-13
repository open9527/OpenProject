package com.android.open9527.dialog;

import android.view.View;
import android.view.Window;

import androidx.annotation.LayoutRes;


public interface DialogViewProvider {

    default void initView(BaseDialogFragment dialog, View contentView) {

    }

    default int bindTheme() {
        return 0;
    }

    @LayoutRes
    default int bindLayout() {
        return 0;
    }

    default void setWindowStyle(Window window) {

    }

    default void onCancel(BaseDialogFragment dialog) {

    }

    default void onDismiss(BaseDialogFragment dialog) {

    }


}
