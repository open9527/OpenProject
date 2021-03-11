package com.android.open9527.common.widget.shadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @author open_9527
 * Create at 2021/3/10
 **/
public class ShadowConstraintLayout extends ConstraintLayout {

    private Paint mShadowPaint;
    private Paint mClipPaint;

    public ShadowConstraintLayout(@NonNull Context context) {
        this(context, null, 0, 0);
    }

    public ShadowConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public ShadowConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ShadowConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (isInEditMode()) {
            return;
        }
        init();
    }

    private void init() {
        mShadowPaint = new Paint();
        mShadowPaint.setAntiAlias(true);
        mShadowPaint.setDither(true);
        mShadowPaint.setFilterBitmap(true);
        mShadowPaint.setStyle(Paint.Style.FILL);

        mClipPaint = new Paint();
        mClipPaint.setAntiAlias(true);
        mClipPaint.setDither(true);
        mClipPaint.setFilterBitmap(true);
        mClipPaint.setStyle(Paint.Style.FILL);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }


    @Override
    public ConstraintLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        for (int i = 0, size = getChildCount(); i < size; i++) {
            View view = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof ShadowLayoutParams) {
                ShadowLayoutParams shadowLayoutParams = (ShadowLayoutParams) layoutParams;
                shadowLayoutParams.getData().initPath(view);
            }
        }
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        boolean ret = false;
        if (layoutParams instanceof ShadowLayoutParams) {
            ShadowLayoutParams shadowLayoutParams = (ShadowLayoutParams) layoutParams;
            ShadowLayoutParamsData data = shadowLayoutParams.getData();
            if (isInEditMode()) {
                //预览模式采用裁剪
                canvas.save();
                canvas.clipPath(data.widgetPath);
                ret = super.drawChild(canvas, child, drawingTime);
                canvas.restore();
                return ret;
            }
            if (!data.hasShadow && !data.needClip)
                return super.drawChild(canvas, child, drawingTime);
            //为解决锯齿问题，正式环境采用xfermode
            if (data.hasShadow) {
                int count = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
                mShadowPaint.setShadowLayer(data.shadowEvaluation, data.shadowDx, data.shadowDy, data.shadowColor);
                mShadowPaint.setColor(data.shadowColor);
                canvas.drawPath(data.widgetPath, mShadowPaint);
                mShadowPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
                mShadowPaint.setColor(Color.WHITE);
                canvas.drawPath(data.widgetPath, mShadowPaint);
                mShadowPaint.setXfermode(null);
                canvas.restoreToCount(count);

            }
            if (data.needClip) {
                int count = canvas.saveLayer(child.getLeft(), child.getTop(), child.getRight(), child.getBottom(), null, Canvas.ALL_SAVE_FLAG);
                ret = super.drawChild(canvas, child, drawingTime);
                mClipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                mClipPaint.setColor(Color.WHITE);
                canvas.drawPath(data.clipPath, mClipPaint);
                mClipPaint.setXfermode(null);
                canvas.restoreToCount(count);
            }
        }
        return ret;
    }

    static class LayoutParams extends ConstraintLayout.LayoutParams implements ShadowLayoutParams {

        private ShadowLayoutParamsData data;

        LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            data = new ShadowLayoutParamsData(c, attrs);
        }

        @Override
        public ShadowLayoutParamsData getData() {
            return data;
        }
    }
}
