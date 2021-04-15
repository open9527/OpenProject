package com.android.open9527.common.widget.textview;

import android.graphics.drawable.Drawable;


/**
 * @author open_9527
 * Create at 2021/1/13
 **/
public final class StateSwitchTextConfig {


    private boolean select;

    private String defaultText;
    private String selectedText;

    private int defaultTextColor;
    private int selectedTextColor;

    private int defaultTextSize;
    private int selectedTextSize;

    private Drawable defaultLeftImage;
    private Drawable selectedLeftImage;

    private Drawable defaultTopImage;
    private Drawable selectedTopImage;

    private Drawable defaultRightImage;
    private Drawable selectedRightImage;

    private Drawable defaultBottomImage;
    private Drawable selectedBottomImage;

    private int drawablePadding = -1;

    private int drawableWidth;
    private int drawableHigh;

    private String defaultTextFont;
    private String selectedTextFont;

    private boolean defaultFakeBoldText;
    private boolean selectedFakeBoldText;


    private StateSwitchTextConfig() {

    }

    public static StateSwitchTextConfig Builder() {
        return new StateSwitchTextConfig();
    }


    public StateSwitchTextConfig setSelect(boolean select) {
        this.select = select;
        return this;
    }

    public StateSwitchTextConfig setDefaultText(String defaultText) {
        this.defaultText = defaultText;
        return this;
    }

    public StateSwitchTextConfig setSelectedText(String selectedText) {
        this.selectedText = selectedText;
        return this;
    }

    public StateSwitchTextConfig setDefaultTextColor(int defaultTextColor) {
        this.defaultTextColor = defaultTextColor;
        return this;
    }

    public StateSwitchTextConfig setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
        return this;
    }

    public StateSwitchTextConfig setDefaultTextSize(int defaultTextSize) {
        this.defaultTextSize = defaultTextSize;
        return this;
    }

    public StateSwitchTextConfig setSelectedTextSize(int selectedTextSize) {
        this.selectedTextSize = selectedTextSize;
        return this;
    }

    public StateSwitchTextConfig setDrawablePadding(int drawablePadding) {
        this.drawablePadding = drawablePadding;
        return this;
    }

    public StateSwitchTextConfig setDefaultLeftImage(Drawable defaultLeftImage) {
        this.defaultLeftImage = defaultLeftImage;
        return this;
    }

    public StateSwitchTextConfig setSelectedLeftImage(Drawable selectedLeftImage) {
        this.selectedLeftImage = selectedLeftImage;
        return this;
    }

    public StateSwitchTextConfig setDefaultTopImage(Drawable defaultTopImage) {
        this.defaultTopImage = defaultTopImage;
        return this;
    }

    public StateSwitchTextConfig setSelectedTopImage(Drawable selectedTopImage) {
        this.selectedTopImage = selectedTopImage;
        return this;
    }

    public StateSwitchTextConfig setDefaultRightImage(Drawable defaultRightImage) {
        this.defaultRightImage = defaultRightImage;
        return this;
    }

    public StateSwitchTextConfig setSelectedRightImage(Drawable selectedRightImage) {
        this.selectedRightImage = selectedRightImage;
        return this;
    }

    public StateSwitchTextConfig setDefaultBottomImage(Drawable defaultBottomImage) {
        this.defaultBottomImage = defaultBottomImage;
        return this;
    }

    public StateSwitchTextConfig setSelectedBottomImage(Drawable selectedBottomImage) {
        this.selectedBottomImage = selectedBottomImage;
        return this;
    }

    public StateSwitchTextConfig setDrawableWidth(int drawableWidth) {
        this.drawableWidth = drawableWidth;
        return this;
    }

    public StateSwitchTextConfig setDrawableHigh(int drawableHigh) {
        this.drawableHigh = drawableHigh;

        return this;
    }

    public StateSwitchTextConfig setDefaultTextFont(String defaultTextFont) {
        this.defaultTextFont = defaultTextFont;
        return this;
    }

    public StateSwitchTextConfig setSelectedTextFont(String selectedTextFont) {
        this.selectedTextFont = selectedTextFont;
        return this;
    }

    public StateSwitchTextConfig setDefaultFakeBoldText(boolean defaultFakeBoldText) {
        this.defaultFakeBoldText = defaultFakeBoldText;
        return this;
    }

    public StateSwitchTextConfig setSelectedFakeBoldText(boolean selectedFakeBoldText) {
        this.selectedFakeBoldText = selectedFakeBoldText;
        return this;
    }
    /*get*/

    public boolean getSelect() {
        return select;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public String getSelectedText() {
        return selectedText;
    }

    public int getDefaultTextColor() {
        return defaultTextColor;
    }

    public int getSelectedTextColor() {
        return selectedTextColor;
    }

    public int getDefaultTextSize() {
        return defaultTextSize;
    }

    public int getSelectedTextSize() {
        return selectedTextSize;
    }

    public Drawable getDefaultLeftImage() {
        return defaultLeftImage;
    }

    public Drawable getSelectedLeftImage() {
        return selectedLeftImage;
    }

    public Drawable getDefaultTopImage() {
        return defaultTopImage;
    }

    public Drawable getSelectedTopImage() {
        return selectedTopImage;
    }

    public Drawable getDefaultRightImage() {
        return defaultRightImage;
    }

    public Drawable getSelectedRightImage() {
        return selectedRightImage;
    }

    public Drawable getDefaultBottomImage() {
        return defaultBottomImage;
    }

    public Drawable getSelectedBottomImage() {
        return selectedBottomImage;
    }

    public int getDrawablePadding() {
        return drawablePadding;
    }

    public int getDrawableWidth() {
        return drawableWidth;
    }

    public int getDrawableHigh() {
        return drawableHigh;
    }

    public String getDefaultTextFont() {
        return defaultTextFont;
    }

    public String getSelectedTextFont() {
        return selectedTextFont;
    }

    public boolean getDefaultFakeBoldText() {
        return defaultFakeBoldText;
    }

    public boolean getSelectedFakeBoldText() {
        return selectedFakeBoldText;
    }

    @Override
    public String toString() {
        return "StateSwitchTextConfig{" +
                "select=" + select +
                ", defaultText='" + defaultText + '\'' +
                ", selectedText='" + selectedText + '\'' +
                ", defaultTextColor=" + defaultTextColor +
                ", selectedTextColor=" + selectedTextColor +
                ", defaultTextSize=" + defaultTextSize +
                ", selectedTextSize=" + selectedTextSize +
                ", defaultLeftImage=" + defaultLeftImage +
                ", selectedLeftImage=" + selectedLeftImage +
                ", defaultTopImage=" + defaultTopImage +
                ", selectedTopImage=" + selectedTopImage +
                ", defaultRightImage=" + defaultRightImage +
                ", selectedRightImage=" + selectedRightImage +
                ", defaultBottomImage=" + defaultBottomImage +
                ", selectedBottomImage=" + selectedBottomImage +
                ", drawablePadding=" + drawablePadding +
                ", drawableWidth=" + drawableWidth +
                ", drawableHigh=" + drawableHigh +
                ", defaultTextFont='" + defaultTextFont + '\'' +
                ", selectedTextFont='" + selectedTextFont + '\'' +
                ", defaultFakeBoldText=" + defaultFakeBoldText +
                ", selectedFakeBoldText=" + selectedFakeBoldText +
                '}';
    }
}
