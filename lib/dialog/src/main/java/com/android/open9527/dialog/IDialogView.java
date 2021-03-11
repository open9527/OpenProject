package com.android.open9527.dialog;

import android.view.View;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;


public interface IDialogView {

    default DialogDataBindingConfig getDataBindingConfig() {
        return new DialogDataBindingConfig();
    }

    default void initView(@NonNull BaseDialogFragment dialog, @NonNull View contentView) {

    }


    default int bindTheme() {
        return 0;
    }

    @LayoutRes
    default int bindLayout() {
        return 0;
    }

    default void setWindowStyle(@NonNull Window window) {

    }

    default void onCancel(@NonNull BaseDialogFragment dialog) {

    }

    default void onDismiss(@NonNull BaseDialogFragment dialog) {

    }


}
