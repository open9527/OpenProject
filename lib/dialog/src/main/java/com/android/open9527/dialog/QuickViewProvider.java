package com.android.open9527.dialog;

import android.view.View;


public interface QuickViewProvider {

    default void viewHolder(BaseDialogFragment dialog, View contentView) {

    }
}
