package com.android.open9527.common.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;


public class FontTextView extends AppCompatTextView {

    private boolean fakeBold;
    private String font;

    public FontTextView(Context context) {
        this(context, null);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setTypeface(font);
    }


    public void setTypeface(String font) {
        if (!TextUtils.isEmpty(font)) {
            Typeface typeface = FontUtils.getTypeface(font, getContext());
            setIncludeFontPadding(false);
            getPaint().setFakeBoldText(fakeBold);
            setTypeface(typeface);
        }
    }

    /**
     * 设置粗体
     *
     * @param fakeBold
     */
    public void setFakeBold(boolean fakeBold) {
        this.fakeBold = fakeBold;
    }

    /**
     * 设置指定字体
     *
     * @param font
     */
    public void setFont(@NonNull String font) {
        this.font = font;
    }

    @Override
    public String toString() {
        return "FontTextView{" +
                "fakeBold=" + fakeBold +
                ", font='" + font + '\'' +
                '}';
    }
}
