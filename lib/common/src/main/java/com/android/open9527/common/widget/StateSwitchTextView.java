package com.android.open9527.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public class StateSwitchTextView extends FontTextView {

    private static final String TAG = "StateSwitchTextView";


    private boolean select;

    private int defaultColor;
    private int selectedColor;

    private String defaultText;
    private String selectedText;

    private int drawablePadding;

    private Drawable defaultLeftImage;
    private Drawable selectedLeftImage;

    private Drawable defaultTopImage;
    private Drawable selectedTopImage;

    private Drawable defaultRightImage;
    private Drawable selectedRightImage;

    private Drawable defaultBottomImage;
    private Drawable selectedBottomImage;


    public StateSwitchTextView(Context context) {
        this(context, null, 0);
    }

    public StateSwitchTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateSwitchTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setText();
        setTextImage();
        Log.i(TAG, toString());
    }

    public void setSelect(boolean select) {
        this.select = select;
        init();
        Log.i(TAG, toString());
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    public void setSelectedText(String selectedText) {
        this.selectedText = selectedText;
    }

    public void setDrawablePadding(int drawablePadding) {
        this.drawablePadding = drawablePadding;
    }

    public void setDefaultLeftImage(Drawable defaultLeftImage) {
        this.defaultLeftImage = defaultLeftImage;
    }

    public void setSelectedLeftImage(Drawable selectedLeftImage) {
        this.selectedLeftImage = selectedLeftImage;
    }

    public void setDefaultTopImage(Drawable defaultTopImage) {
        this.defaultTopImage = defaultTopImage;
    }

    public void setSelectedTopImage(Drawable selectedTopImage) {
        this.selectedTopImage = selectedTopImage;
    }

    public void setDefaultRightImage(Drawable defaultRightImage) {
        this.defaultRightImage = defaultRightImage;
    }

    public void setSelectedRightImage(Drawable selectedRightImage) {
        this.selectedRightImage = selectedRightImage;
    }

    public void setDefaultBottomImage(Drawable defaultBottomImage) {
        this.defaultBottomImage = defaultBottomImage;
    }

    public void setSelectedBottomImage(Drawable selectedBottomImage) {
        this.selectedBottomImage = selectedBottomImage;
    }

    @Override
    public String toString() {
        return "StateSwitchTextView{" +
                "select=" + select +
                ", defaultColor=" + defaultColor +
                ", selectedColor=" + selectedColor +
                ", defaultText='" + defaultText + '\'' +
                ", selectedText='" + selectedText + '\'' +
                ", drawablePadding=" + drawablePadding +
                ", defaultLeftImage=" + defaultLeftImage +
                ", selectedLeftImage=" + selectedLeftImage +
                ", defaultTopImage=" + defaultTopImage +
                ", selectedTopImage=" + selectedTopImage +
                ", defaultRightImage=" + defaultRightImage +
                ", selectedRightImage=" + selectedRightImage +
                ", defaultBottomImage=" + defaultBottomImage +
                ", selectedBottomImage=" + selectedBottomImage +
                '}';
    }

    private void setTextImage() {
        setCompoundDrawablePadding(drawablePadding);
        setCompoundDrawablesWithIntrinsicBounds
                (
                        select ? selectedLeftImage : defaultLeftImage,
                        select ? selectedTopImage : defaultTopImage,
                        select ? selectedRightImage : defaultRightImage,
                        select ? selectedBottomImage : defaultBottomImage
                );
    }

    private void setText() {
        setText(select ? selectedText : defaultText);
        setTextColor(ContextCompat.getColor(getContext(), select ? selectedColor : defaultColor));
    }


}
