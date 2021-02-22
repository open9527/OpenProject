package com.open9527.wanandroid.pkg.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.SizeUtils;
import com.open9527.wanandroid.pkg.R;

import java.lang.reflect.Field;

public class TabIndicatorDrawable extends Drawable {
    private int INDICATOR_MARGIN = SizeUtils.dp2px(24);
    private static final int INDICATOR_HEIGHT = SizeUtils.dp2px(3);
    private static final int INDICATOR_RADIUS = SizeUtils.dp2px(1.5f);

    private View view;
    private Paint paint;

    public TabIndicatorDrawable(View view) {
        this.view = view;
        this.paint = new Paint();
        paint.setColor(view.getContext().getResources().getColor(R.color.common_accent_color));
    }

    public TabIndicatorDrawable(View view, int color) {
        this.view = view;
        this.paint = new Paint();
        paint.setColor(view.getContext().getResources().getColor(color));
    }

    public TabIndicatorDrawable(View view, int color, int indicator_margin) {
        this.view = view;
        this.paint = new Paint();
        this.INDICATOR_MARGIN = indicator_margin;
        paint.setColor(view.getContext().getResources().getColor(color));
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        //TODO:需要注意 变量indicatorLeft ,indicatorRight 会随着android版本变化
        int mIndicatorLeft = getIntValue("indicatorLeft");
        int mIndicatorRight = getIntValue("indicatorRight");
        int radius = INDICATOR_RADIUS;
        if (mIndicatorLeft >= 0 && mIndicatorRight > mIndicatorLeft) {
            canvas.drawRoundRect(new RectF(mIndicatorLeft + INDICATOR_MARGIN, view.getHeight() - INDICATOR_HEIGHT, mIndicatorRight - INDICATOR_MARGIN, view.getHeight()), radius, radius, paint);
        }
    }

    private int getIntValue(String name) {
        try {
            Field f = view.getClass().getDeclaredField(name);
            f.setAccessible(true);
            Object obj = f.get(view);
            return (Integer) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
