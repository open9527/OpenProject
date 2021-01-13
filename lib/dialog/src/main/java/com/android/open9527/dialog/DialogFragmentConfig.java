package com.android.open9527.dialog;


import android.view.Gravity;


public final class DialogFragmentConfig {


    private int viewId = 0;

    private int width = -1;

    private int height = -2;

    private int gravity = Gravity.CENTER;

    private int animation = 0;

    private int theme = R.style.BaseDialogStyle;


    private boolean cancelable = true;

    private boolean canceledOnTouchOutside = true;

    /*dimAmount #FLAG_DIM_BEHIND */
    private float dimAmount = 0.5f;

    private QuickViewProvider quickViewProvider;

    public DialogFragmentConfig() {

    }

    public static DialogFragmentConfig generateDefault() {
        return new DialogFragmentConfig();
    }

    public DialogFragmentConfig viewId(int viewId) {
        this.viewId = viewId;
        return this;
    }


    public DialogFragmentConfig width(int width) {
        this.width = width;
        return this;
    }

    public DialogFragmentConfig height(int height) {
        this.height = height;
        return this;
    }

    public DialogFragmentConfig gravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public DialogFragmentConfig animation(int animation) {
        this.animation = animation;
        return this;
    }

    public DialogFragmentConfig theme(int theme) {
        this.theme = theme;
        return this;
    }

    public DialogFragmentConfig cancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public DialogFragmentConfig canceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public DialogFragmentConfig dimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public DialogFragmentConfig quickViewProvider(QuickViewProvider quickViewProvider) {
        this.quickViewProvider = quickViewProvider;
        return this;
    }

    /*get*/

    public int getViewId() {
        return viewId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getGravity() {
        return gravity;
    }

    public int getAnimation() {
        return animation;
    }

    public int getTheme() {
        return theme;
    }

    public boolean getCancelable() {
        return cancelable;
    }


    public boolean getCanceledOnTouchOutside() {
        return canceledOnTouchOutside;
    }


    public float getDimAmount() {
        return dimAmount;
    }

    public QuickViewProvider getQuickViewProvider() {
        return quickViewProvider;
    }
}
