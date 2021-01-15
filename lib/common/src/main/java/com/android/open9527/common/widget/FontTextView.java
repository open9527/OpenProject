package com.android.open9527.common.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public class FontTextView extends AppCompatTextView {


    public FontTextView(Context context) {
        this(context, null);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }
    }

    public void initFont(StateSwitchTextConfig stateSwitchConfig) {
        setTypeface(stateSwitchConfig.getSelect() ? stateSwitchConfig.getSelectedTextFont() : stateSwitchConfig.getDefaultTextFont(),
                stateSwitchConfig.getSelect() ? stateSwitchConfig.getSelectedFakeBoldText() : stateSwitchConfig.getDefaultFakeBoldText()
        );
    }


    public void setTypeface(String font, boolean fakeBold) {
        if (!TextUtils.isEmpty(font)) {
            Typeface typeface = FontUtils.getTypeface(font, getContext());
            setIncludeFontPadding(false);
            getPaint().setFakeBoldText(fakeBold);
            setTypeface(typeface);
        }
    }

}
