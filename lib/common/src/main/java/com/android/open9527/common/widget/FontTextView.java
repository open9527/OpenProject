package com.android.open9527.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.style.LineHeightSpan;
import android.util.AttributeSet;
import android.widget.TextView;

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

    /**
     * 排除每行文字间的padding
     *
     * @param text :
     */
    public void setCustomText(CharSequence text) {
        if (text == null) {
            return;
        }
        // 获得视觉定义的每行文字的行高
        int lineHeight = (int) getTextSize();

        SpannableStringBuilder spannableStringBuilder;
        if (text instanceof SpannableStringBuilder) {
            spannableStringBuilder = (SpannableStringBuilder) text;
            // 设置LineHeightSpan
            spannableStringBuilder.setSpan(new ExcludeInnerLineSpaceSpan(lineHeight),
                    0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            spannableStringBuilder = new SpannableStringBuilder(text);
            // 设置LineHeightSpan
            spannableStringBuilder.setSpan(new ExcludeInnerLineSpaceSpan(lineHeight),
                    0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // 调用系统setText()方法
        setText(spannableStringBuilder);
    }


    private static class ExcludeInnerLineSpaceSpan implements LineHeightSpan {

        // TextView行高
        private int mHeight;

        public ExcludeInnerLineSpaceSpan(int height) {
            mHeight = height;
        }

        @Override
        public void chooseHeight(CharSequence text, int start, int end,
                                 int spanstartv, int lineHeight,
                                 Paint.FontMetricsInt fm) {
            // 原始行高
            final int originHeight = fm.descent - fm.ascent;
            if (originHeight <= 0) {
                return;
            }
            // 计算比例值
            final float ratio = mHeight * 1.0f / originHeight;
            // 根据最新行高，修改descent
            fm.descent = Math.round(fm.descent * ratio);
            // 根据最新行高，修改ascent
            fm.ascent = fm.descent - mHeight;
        }
    }
}
