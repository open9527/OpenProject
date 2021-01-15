package com.android.open9527.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;


/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public class StateSwitchTextView extends FontTextView {

    private static final String TAG = "StateSwitchTextView";

    private StateSwitchTextConfig mStateSwitchConfig;


    public StateSwitchTextView(Context context) {
        this(context, null, 0);
    }

    public StateSwitchTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateSwitchTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }
    }

    public void setStateSwitchConfig(StateSwitchTextConfig stateSwitchConfig) {
        this.mStateSwitchConfig = stateSwitchConfig;
    }

    public void init() {
//        LogUtils.i(TAG, "mStateSwitchConfig:" + mStateSwitchConfig.toString());
        setText();
        setTextImage();
        initFont(mStateSwitchConfig);
    }

    private void setTextImage() {
        if (mStateSwitchConfig.getDrawablePadding() >= 0) {
            setCompoundDrawablePadding(mStateSwitchConfig.getDrawablePadding());
        }
        setDrawableBounds();
    }

    private void setText() {
        setText(mStateSwitchConfig.getSelect() ? mStateSwitchConfig.getSelectedText() : mStateSwitchConfig.getDefaultText());

        if (mStateSwitchConfig.getSelectedTextColor() > 0 || mStateSwitchConfig.getDefaultTextColor() > 0) {
            setTextColor(ContextCompat.getColor(getContext(), mStateSwitchConfig.getSelect() ? mStateSwitchConfig.getSelectedTextColor() : mStateSwitchConfig.getDefaultTextColor()));
        }
        //TODO:SP
        if (mStateSwitchConfig.getSelectedTextSize() > 0 || mStateSwitchConfig.getDefaultTextSize() > 0) {
            setTextSize(mStateSwitchConfig.getSelect() ? mStateSwitchConfig.getSelectedTextSize() : mStateSwitchConfig.getDefaultTextSize());
        }
    }


    private void setDrawableBounds() {
        if (mStateSwitchConfig.getDrawableWidth() > 0 && mStateSwitchConfig.getDrawableHigh() > 0) {
            setCompoundDrawables(mStateSwitchConfig.getSelect() ? getDrawable(mStateSwitchConfig.getSelectedLeftImage()) : getDrawable(mStateSwitchConfig.getDefaultLeftImage()),
                    mStateSwitchConfig.getSelect() ? getDrawable(mStateSwitchConfig.getSelectedTopImage()) : getDrawable(mStateSwitchConfig.getDefaultTopImage()),
                    mStateSwitchConfig.getSelect() ? getDrawable(mStateSwitchConfig.getSelectedRightImage()) : getDrawable(mStateSwitchConfig.getDefaultRightImage()),
                    mStateSwitchConfig.getSelect() ? getDrawable(mStateSwitchConfig.getSelectedBottomImage()) : getDrawable(mStateSwitchConfig.getDefaultBottomImage()));

        } else {
            setCompoundDrawablesWithIntrinsicBounds(
                    mStateSwitchConfig.getSelect() ? mStateSwitchConfig.getSelectedLeftImage() : mStateSwitchConfig.getDefaultLeftImage(),
                    mStateSwitchConfig.getSelect() ? mStateSwitchConfig.getSelectedTopImage() : mStateSwitchConfig.getDefaultTopImage(),
                    mStateSwitchConfig.getSelect() ? mStateSwitchConfig.getSelectedRightImage() : mStateSwitchConfig.getDefaultRightImage(),
                    mStateSwitchConfig.getSelect() ? mStateSwitchConfig.getSelectedBottomImage() : mStateSwitchConfig.getDefaultBottomImage()
            );
        }

    }


    private Drawable getDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, mStateSwitchConfig.getDrawableWidth(), mStateSwitchConfig.getDrawableHigh());
        }
        return drawable;
    }

}
