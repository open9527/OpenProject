package com.open9527.wanandroid.pkg.bean;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class TabBean implements Serializable {
    //标题
    private String defaultText;

    //字体颜色
    private int defaultTextColor;
    private int selectedTextColor;

    //图标
    private int defaultIcon;
    private int selectedIcon;

    public TabBean() {

    }

    public TabBean(String defaultText) {
        this.defaultText = defaultText;
    }

    public TabBean(String defaultText, int defaultIcon) {
        this.defaultText = defaultText;
        this.defaultIcon = defaultIcon;
    }





    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
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
}
