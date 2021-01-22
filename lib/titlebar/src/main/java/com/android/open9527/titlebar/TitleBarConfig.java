package com.android.open9527.titlebar;

import android.graphics.drawable.Drawable;

/**
 * @author open_9527
 * Create at 2021/1/20
 **/
public final class TitleBarConfig {

    private String leftText;
    private int leftTextSize;
    private int leftTextColor;
    private Drawable leftDrawable;
    private int leftDrawableWidth;
    private int leftDrawableHigh;
    private int leftDrawablePadding;

    private String titleText;
    private int titleTextSize;
    private int titleTextColor;
    private Drawable titleDrawable;
    private int titleDrawableWidth;
    private int titleDrawableHigh;
    private int titleDrawablePadding;


    private String rightText;
    private int rightTextSize;
    private int rightTextColor;
    private Drawable rightDrawable;
    private int rightDrawableWidth;
    private int rightDrawableHigh;
    private int rightDrawablePadding;


    private int lineVisibility;
    private int lineBg;
    private int lineBgHigh;

    private Drawable titleBarBgDrawable;

    private TitleBarInitialize titleBarInitialize;
    private OnTitleBarListener titleBarListener;


    private TitleBarConfig() {

    }

    public static TitleBarConfig Builder() {
        return new TitleBarConfig();
    }
    /*set*/

    public TitleBarConfig setLeftText(String leftText) {
        this.leftText = leftText;
        return this;
    }

    public TitleBarConfig setLeftTextSize(int leftTextSize) {
        this.leftTextSize = leftTextSize;
        return this;
    }

    public TitleBarConfig setLeftTextColor(int leftTextColor) {
        this.leftTextColor = leftTextColor;
        return this;
    }

    public TitleBarConfig setLeftDrawable(Drawable leftDrawable) {
        this.leftDrawable = leftDrawable;
        return this;
    }

    public TitleBarConfig setLeftDrawableWidth(int leftDrawableWidth) {
        this.leftDrawableWidth = leftDrawableWidth;
        return this;
    }

    public TitleBarConfig setLeftDrawableHigh(int leftDrawableHigh) {
        this.leftDrawableHigh = leftDrawableHigh;
        return this;
    }

    public TitleBarConfig setLeftDrawablePadding(int leftDrawablePadding) {
        this.leftDrawablePadding = leftDrawablePadding;
        return this;
    }

    public TitleBarConfig setTitleText(String titleText) {
        this.titleText = titleText;
        return this;
    }

    public TitleBarConfig setTitleTextSize(int titleTextSize) {
        this.titleTextSize = titleTextSize;
        return this;
    }

    public TitleBarConfig setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return this;
    }

    public TitleBarConfig setTitleDrawable(Drawable titleDrawable) {
        this.titleDrawable = titleDrawable;
        return this;
    }

    public TitleBarConfig setTitleDrawableWidth(int titleDrawableWidth) {
        this.titleDrawableWidth = titleDrawableWidth;
        return this;
    }

    public TitleBarConfig setTitleDrawableHigh(int titleDrawableHigh) {
        this.titleDrawableHigh = titleDrawableHigh;
        return this;
    }

    public TitleBarConfig setTitleDrawablePadding(int titleDrawablePadding) {
        this.titleDrawablePadding = titleDrawablePadding;
        return this;
    }

    public TitleBarConfig setRightText(String rightText) {
        this.rightText = rightText;
        return this;
    }

    public TitleBarConfig setRightTextSize(int rightTextSize) {
        this.rightTextSize = rightTextSize;
        return this;
    }

    public TitleBarConfig setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
        return this;
    }

    public TitleBarConfig setRightDrawable(Drawable rightDrawable) {
        this.rightDrawable = rightDrawable;
        return this;
    }

    public TitleBarConfig setRightDrawableWidth(int rightDrawableWidth) {
        this.rightDrawableWidth = rightDrawableWidth;
        return this;
    }

    public TitleBarConfig setRightDrawableHigh(int rightDrawableHigh) {
        this.rightDrawableHigh = rightDrawableHigh;
        return this;
    }

    public TitleBarConfig setRightDrawablePadding(int rightDrawablePadding) {
        this.rightDrawablePadding = rightDrawablePadding;
        return this;
    }

    public TitleBarConfig setLineVisibility(int lineVisibility) {
        this.lineVisibility = lineVisibility;
        return this;
    }

    public TitleBarConfig setLineBg(int lineBg) {
        this.lineBg = lineBg;
        return this;
    }

    public TitleBarConfig setLineBgHigh(int lineBgHigh) {
        this.lineBgHigh = lineBgHigh;
        return this;
    }



    public TitleBarConfig setTitleBarBgDrawable(Drawable titleBarBgDrawable) {
        this.titleBarBgDrawable = titleBarBgDrawable;
        return this;
    }

    public TitleBarConfig setTitleBarInitialize(TitleBarInitialize titleBarInitialize) {
        this.titleBarInitialize = titleBarInitialize;
        return this;
    }

    public TitleBarConfig setTitleBarListener(OnTitleBarListener titleBarListener) {
        this.titleBarListener = titleBarListener;
        return this;
    }

    /*get*/
    public String getLeftText() {
        return leftText;
    }

    public int getLeftTextSize() {
        return leftTextSize;
    }

    public int getLeftTextColor() {
        return leftTextColor;
    }

    public Drawable getLeftDrawable() {
        return leftDrawable;
    }

    public int getLeftDrawableWidth() {
        return leftDrawableWidth;
    }

    public int getLeftDrawableHigh() {
        return leftDrawableHigh;
    }

    public int getLeftDrawablePadding() {
        return leftDrawablePadding;
    }

    public String getTitleText() {
        return titleText;
    }

    public int getTitleTextSize() {
        return titleTextSize;
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    public Drawable getTitleDrawable() {
        return titleDrawable;
    }

    public int getTitleDrawableWidth() {
        return titleDrawableWidth;
    }

    public int getTitleDrawableHigh() {
        return titleDrawableHigh;
    }

    public int getTitleDrawablePadding() {
        return titleDrawablePadding;
    }

    public String getRightText() {
        return rightText;
    }

    public int getRightTextSize() {
        return rightTextSize;
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public Drawable getRightDrawable() {
        return rightDrawable;
    }

    public int getRightDrawableWidth() {
        return rightDrawableWidth;
    }

    public int getRightDrawableHigh() {
        return rightDrawableHigh;
    }

    public int getRightDrawablePadding() {
        return rightDrawablePadding;
    }

    public int getLineVisibility() {
        return lineVisibility;
    }

    public int getLineBg() {
        return lineBg;
    }

    public int getLineBgHigh() {
        return lineBgHigh;
    }

    public Drawable getTitleBarBgDrawable() {
        return titleBarBgDrawable;
    }

    public TitleBarInitialize getTitleBarInitialize() {
        return titleBarInitialize;
    }

    public OnTitleBarListener getTitleBarListener() {
        return titleBarListener;
    }
}
