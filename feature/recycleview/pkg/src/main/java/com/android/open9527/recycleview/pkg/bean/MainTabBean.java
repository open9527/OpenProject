package com.android.open9527.recycleview.pkg.bean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/1/11
 **/
public class MainTabBean implements Serializable {

    //标题
    private String defaultText;
    private String selectedText;

    //字体颜色
    private int defaultTextColor;
    private int selectedTextColor;

    //图标
    private int defaultIcon;
    private int selectedIcon;

    private int defaultIconColor;
    private int selectedIconColor;

    private Drawable defaultDrawableIcon;
    private Drawable selectedDrawableIcon;


    //背景
    private int defaultBg;
    private int selectedBg;

    private Drawable defaultDrawableBg;
    private Drawable selectedDrawableBg;

    public MainTabBean(String defaultText, int defaultTextColor, int defaultIconColor, Drawable defaultDrawableIcon) {
        this.defaultText = defaultText;
        this.defaultTextColor = defaultTextColor;
        this.defaultIconColor = defaultIconColor;
        this.defaultDrawableIcon = defaultDrawableIcon;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    public String getSelectedText() {
        return selectedText;
    }

    public void setSelectedText(String selectedText) {
        this.selectedText = selectedText;
    }

    public int getDefaultTextColor() {
        return defaultTextColor;
    }

    public void setDefaultTextColor(int defaultTextColor) {
        this.defaultTextColor = defaultTextColor;
    }

    public int getSelectedTextColor() {
        return selectedTextColor;
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
    }

    public int getDefaultIcon() {
        return defaultIcon;
    }

    public void setDefaultIcon(int defaultIcon) {
        this.defaultIcon = defaultIcon;
    }

    public int getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(int selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public int getDefaultIconColor() {
        return defaultIconColor;
    }

    public void setDefaultIconColor(int defaultIconColor) {
        this.defaultIconColor = defaultIconColor;
    }

    public int getSelectedIconColor() {
        return selectedIconColor;
    }

    public void setSelectedIconColor(int selectedIconColor) {
        this.selectedIconColor = selectedIconColor;
    }

    public Drawable getDefaultDrawableIcon() {
        return defaultDrawableIcon;
    }

    public void setDefaultDrawableIcon(Drawable defaultDrawableIcon) {
        this.defaultDrawableIcon = defaultDrawableIcon;
    }

    public Drawable getSelectedDrawableIcon() {
        return selectedDrawableIcon;
    }

    public void setSelectedDrawableIcon(Drawable selectedDrawableIcon) {
        this.selectedDrawableIcon = selectedDrawableIcon;
    }

    public int getDefaultBg() {
        return defaultBg;
    }

    public void setDefaultBg(int defaultBg) {
        this.defaultBg = defaultBg;
    }

    public int getSelectedBg() {
        return selectedBg;
    }

    public void setSelectedBg(int selectedBg) {
        this.selectedBg = selectedBg;
    }

    public Drawable getDefaultDrawableBg() {
        return defaultDrawableBg;
    }

    public void setDefaultDrawableBg(Drawable defaultDrawableBg) {
        this.defaultDrawableBg = defaultDrawableBg;
    }

    public Drawable getSelectedDrawableBg() {
        return selectedDrawableBg;
    }

    public void setSelectedDrawableBg(Drawable selectedDrawableBg) {
        this.selectedDrawableBg = selectedDrawableBg;
    }

    @Override
    public String toString() {
        return "TabBean{" +
                "defaultText='" + defaultText + '\'' +
                ", selectedText='" + selectedText + '\'' +
                ", defaultTextColor=" + defaultTextColor +
                ", selectedTextColor=" + selectedTextColor +
                ", defaultIcon=" + defaultIcon +
                ", selectedIcon=" + selectedIcon +
                ", defaultIconColor=" + defaultIconColor +
                ", selectedIconColor=" + selectedIconColor +
                ", defaultDrawableIcon=" + defaultDrawableIcon +
                ", selectedDrawableIcon=" + selectedDrawableIcon +
                ", defaultBg=" + defaultBg +
                ", selectedBg=" + selectedBg +
                ", defaultDrawableBg=" + defaultDrawableBg +
                ", selectedDrawableBg=" + selectedDrawableBg +
                '}';
    }
}
