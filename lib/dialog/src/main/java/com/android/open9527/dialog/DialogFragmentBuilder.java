package com.android.open9527.dialog;

import android.content.Context;


public class DialogFragmentBuilder {

    private Context context;

    private DialogFragmentConfig dialogFragmentConfig;

    private DialogFragmentBuilder(Context context) {
        this.context = context;
    }

    public static DialogFragmentBuilder with(Context context) {
        return new DialogFragmentBuilder(context);
    }


    public DialogFragmentBuilder config(DialogFragmentConfig dialogFragmentConfig) {
        this.dialogFragmentConfig = dialogFragmentConfig;
        return this;
    }

    public final DialogFragmentConfig getDialogFragmentConfig() {
        return dialogFragmentConfig;
    }

    public QuickDialogFragment build() {
        return new QuickDialogFragment(context, dialogFragmentConfig);
    }


    public QuickDialogFragment show() {
        QuickDialogFragment quickDialogFragment = build();
        quickDialogFragment.show();
        return quickDialogFragment;
    }

    public QuickDialogFragment show(String tag) {
        QuickDialogFragment quickDialogFragment = build();
        quickDialogFragment.show(tag);
        return quickDialogFragment;
    }
}
