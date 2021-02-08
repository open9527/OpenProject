package com.android.open9527.dialog;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.util.Objects;


public class QuickDialogFragment extends BaseDialogFragment {


    private DialogFragmentConfig dialogFragmentConfig;

    public QuickDialogFragment(Context context, DialogFragmentConfig dialogFragmentConfig) {
        super(context);
        this.dialogFragmentConfig = dialogFragmentConfig;
    }

    @Override
    public int bindLayout() {
        return dialogFragmentConfig.getViewId();
    }

    @Override
    public int bindTheme() {
        return dialogFragmentConfig.getTheme();
    }

    @Override
    public void setWindowStyle(@NonNull Window window) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = dialogFragmentConfig.getGravity();
        attributes.width = dialogFragmentConfig.getWidth();
        attributes.height = dialogFragmentConfig.getHeight();
        attributes.windowAnimations = dialogFragmentConfig.getAnimation();
        attributes.dimAmount = dialogFragmentConfig.getDimAmount();
        window.setAttributes(attributes);
    }

    @Override
    public void initView(@NonNull BaseDialogFragment dialog,@NonNull View contentView) {
        IQuickView quickViewProvider = dialogFragmentConfig.getQuickViewProvider();
        if (quickViewProvider != null) {
            quickViewProvider.viewHolder(dialog, contentView);
        }
        setCancelable(dialogFragmentConfig.getCancelable());
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(dialogFragmentConfig.getCanceledOnTouchOutside());
    }
}
