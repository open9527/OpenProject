package com.android.open9527.common.widget.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.airbnb.lottie.LottieAnimationView;


/**
 * @author open_9527
 * Create at 2021/4/22
 **/
//public class RoundImageView extends AppCompatImageView {
public class RoundImageView extends LottieAnimationView {

    private static final String TAG = "RoundImageView";


    //save bundle state
    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_TYPE = "state_type";
    private static final String STATE_BORDER_RADIUS = "state_border_radius";
    private static final String STATE_ROUND_RADIUS = "state_round_radius";
    private static final String STATE_BORDER_WIDTH = "state_border_width";
    private static final String STATE_BORDER_COLOR = "state_border_color";


    private int mBorderRadius = 10;
    private int mRoundRadius = 0;
    private int mBorderWidth = 0;
    private int mBorderColor = Color.WHITE;

    @RoundImageType.RoundType
    private String mRoundType = RoundImageType.ROUND_TYPE_NORMAL;

    @RoundImageType.CornerType
    private String mCornerType = RoundImageType.CORNER_TYPE_ALL;


    private Paint mPaint;
    private Paint mBorderPaint;
    private Matrix mMatrix;
    private int mWidth;
    private RectF mRectF;
    private RectF mRectF1;
    private RectF mRectF2;


    public RoundImageView(@NonNull Context context) {
        this(context, null, 0);
    }

    public RoundImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mMatrix = new Matrix();
        mPaint = new Paint();
        mBorderPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mRoundType.equals(RoundImageType.ROUND_TYPE_CIRCLE)) {
            mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
            mRoundRadius = mWidth / 2 - mBorderWidth / 2;
            setMeasuredDimension(mWidth, mWidth);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        initPaint();
        initBitmapShader();
        if (mRoundType.equals(RoundImageType.ROUND_TYPE_NORMAL)){
            super.onDraw(canvas);
        }else {
            initCanvas(canvas);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initRectF();
    }


    private void initPaint() {
        mPaint.setAntiAlias(true);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);
    }

    private void initCanvas(Canvas canvas) {
        if (mRoundType.equals(RoundImageType.ROUND_TYPE_ROUND)) {
            if (mCornerType.equals(RoundImageType.CORNER_TYPE_ALL)) {
                canvas.drawRoundRect(mRectF, mRoundRadius, mRoundRadius, mPaint);
                if (mBorderWidth > 0) {
                    canvas.drawRoundRect(mRectF, mBorderRadius, mBorderRadius, mBorderPaint);
                }
            } else {
                canvas.drawRoundRect(mRectF, mRoundRadius, mRoundRadius, mPaint);
                canvas.drawRect(mRectF1, mPaint);
                if (mRectF2 != null) {
                    canvas.drawRect(mRectF2, mPaint);
                }

            }

        } else if (mRoundType.equals(RoundImageType.ROUND_TYPE_CIRCLE)) {
            canvas.drawCircle(mRoundRadius, mRoundRadius, mRoundRadius, mPaint);

            if (mBorderWidth > 0) {
                canvas.drawCircle(mRoundRadius, mRoundRadius, mRoundRadius - mBorderWidth / 2f, mBorderPaint);
            }
        }
//        else {
//            getDrawable().draw(canvas);
//        }

    }


    private void initRectF() {
        if (mRoundType.equals(RoundImageType.ROUND_TYPE_ROUND)) {
            if (mCornerType.equals(RoundImageType.CORNER_TYPE_ALL)) {
                if (mBorderWidth > 0) {
                    mRectF = new RectF(mBorderWidth / 2f, mBorderWidth / 2f, getWidth() - mBorderWidth / 2f, getHeight() - mBorderWidth / 2f);

                } else {
                    mRectF = new RectF(0, 0, getWidth(), getHeight());
                }

            } else if (mCornerType.equals(RoundImageType.CORNER_TYPE_LEFT_TOP)) {
                mRectF = new RectF(0, 0, mRoundRadius * 2, mRoundRadius * 2);
                mRectF1 = new RectF(0, mRoundRadius, mRoundRadius, getHeight());
                mRectF2 = new RectF(mRoundRadius, 0, getWidth(), getHeight());

            } else if (mCornerType.equals(RoundImageType.CORNER_TYPE_LEFT)) {
                mRectF = new RectF(0, 0, mRoundRadius * 2, getHeight());
                mRectF1 = new RectF(mRoundRadius, 0, getWidth(), getHeight());

            } else if (mCornerType.equals(RoundImageType.CORNER_TYPE_TOP)) {
                mRectF = new RectF(0, 0, getWidth(), mRoundRadius * 2);
                mRectF1 = new RectF(0, mRoundRadius, getWidth(), getHeight());

            } else if (mCornerType.equals(RoundImageType.CORNER_TYPE_RIGHT_TOP)) {
                mRectF = new RectF(getWidth() - mRoundRadius * 2, 0, getWidth(), mRoundRadius * 2);
                mRectF1 = new RectF(0, 0, getWidth() - mRoundRadius, getHeight());
                mRectF2 = new RectF(getWidth() - mRoundRadius, mRoundRadius, getWidth(), getHeight());


            } else if (mCornerType.equals(RoundImageType.CORNER_TYPE_RIGHT)) {
                mRectF = new RectF(getWidth() - mRoundRadius * 2, 0, getWidth(), getHeight());
                mRectF1 = new RectF(0, 0, getWidth() - mRoundRadius, getHeight());

            } else if (mCornerType.equals(RoundImageType.CORNER_TYPE_BOTTOM_LEFT)) {
                mRectF = new RectF(0, getHeight() - mRoundRadius * 2, mRoundRadius * 2, getHeight());
                mRectF1 = new RectF(0, 0, mRoundRadius * 2, getHeight() - mRoundRadius);
                mRectF2 = new RectF(mRoundRadius, 0, getWidth(), getHeight());


            } else if (mCornerType.equals(RoundImageType.CORNER_TYPE_BOTTOM_RIGHT)) {
                mRectF = new RectF(getWidth() - mRoundRadius * 2, getHeight() - mRoundRadius * 2, getWidth(), getHeight());
                mRectF1 = new RectF(0, 0, getWidth() - mRoundRadius, getHeight());
                mRectF2 = new RectF(getWidth() - mRoundRadius, 0, getWidth(), getHeight() - mRoundRadius);


            } else if (mCornerType.equals(RoundImageType.CORNER_TYPE_BOTTOM)) {
                mRectF = new RectF(0, getHeight() - mRoundRadius * 2, getWidth(), getHeight());
                mRectF1 = new RectF(0, 0, getWidth(), getHeight() - mRoundRadius);
            }
        } else {
            mRectF = new RectF(0, 0, getWidth(), getHeight());
        }

    }


    private void initBitmapShader() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Bitmap bitmap = drawable2Bitmap(drawable);
        if (bitmap == null) {
            return;
        }
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int drawableWidth = bitmap.getWidth();
        int drawableHeight = bitmap.getHeight();
        float dx = 0, dy = 0;

        float scale1 = 1.0f;
        float scale2 = 1.0f;
        if (mMatrix!=null){
            mMatrix.setScale(scale1, scale2);
        }

        final boolean fits = (drawableWidth < 0 || viewWidth == drawableWidth)
                && (drawableHeight < 0 || viewHeight == drawableHeight);
        if (mRoundType.equals(RoundImageType.ROUND_TYPE_CIRCLE)) {
            int size = Math.min(drawableWidth, drawableHeight);
            scale = mWidth * 1.0f / size;
        } else if (mRoundType.equals(RoundImageType.ROUND_TYPE_ROUND)) {
            scale = Math.max(viewWidth * 1.0f / drawableWidth, viewHeight
                    * 1.0f / drawableHeight);
        } else {
            return;
        }

        if (drawableWidth <= 0 || drawableHeight <= 0) {
            drawable.setBounds(0, 0, viewWidth, viewHeight);
            mMatrix = null;
        } else {
            drawable.setBounds(0, 0, drawableWidth, drawableHeight);
            if (mMatrix != null) {
                if (ScaleType.MATRIX == getScaleType()) {
                    if (mMatrix.isIdentity()) {
                        mMatrix = null;
                    }
                } else if (fits) {
                    mMatrix = null;
                } else if (ScaleType.CENTER == getScaleType()) {
                    mMatrix.setTranslate(Math.round((viewWidth - drawableWidth) * 0.5f),
                            Math.round((viewHeight - drawableHeight) * 0.5f));
                } else if (ScaleType.CENTER_CROP == getScaleType()) {
                    if (drawableWidth * viewHeight > viewWidth * drawableHeight) {
                        dx = (viewWidth - drawableWidth * scale) * 0.5f;
                    } else {
                        dy = (viewHeight - drawableHeight * scale) * 0.5f;
                    }

                    mMatrix.setScale(scale, scale);
                    mMatrix.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));


                } else if (ScaleType.CENTER_INSIDE == getScaleType()) {

                    if (drawableWidth <= viewWidth && drawableHeight <= viewHeight) {
                        scale = 1.0f;
                    } else {
                        scale = Math.min((float) viewWidth / (float) drawableWidth,
                                (float) viewHeight / (float) drawableHeight);
                    }
                    dx = Math.round((viewWidth - drawableWidth * scale) * 0.5f);
                    dy = Math.round((viewHeight - drawableHeight * scale) * 0.5f);
                    mMatrix.setScale(scale, scale);
                    mMatrix.postTranslate(dx, dy);
                } else {
                    if (drawableWidth * viewHeight > viewWidth * drawableHeight) {
                        dx = (viewWidth - drawableWidth * scale) * 0.5f;
                    } else {
                        dy = (viewHeight - drawableHeight * scale) * 0.5f;
                    }
                    mMatrix.setScale(scale, scale);
                    mMatrix.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));
                }
            }
        }
        if (ScaleType.FIT_XY == getScaleType() && mMatrix != null) {
            scale1 = viewWidth * 1.0f / drawableWidth;
            scale2 = viewHeight * 1.0f / drawableHeight;
            mMatrix.setScale(scale1, scale2);
        }
        bitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(bitmapShader);
    }


    public void setBorderWidth(int borderWidth) {
        int width = dp2px(borderWidth);
        if (this.mBorderWidth != width) {
            this.mBorderWidth = width;
            invalidate();
        }
    }

    public void setBorderColor(int mBorderColor) {
        if (this.mBorderColor == mBorderColor) {
            return;
        }
        this.mBorderColor = mBorderColor;
        mBorderPaint.setColor(mBorderColor);
        invalidate();
    }

    public void setBorderRadius(int borderRadius) {
        int radius = dp2px(borderRadius);
        if (this.mBorderRadius != radius) {
            this.mBorderRadius = radius;
            invalidate();
        }
    }

    public void setRoundRadius(int roundRadius) {
        int radius = dp2px(roundRadius);
        if (this.mRoundRadius != radius) {
            this.mRoundRadius = radius;
            invalidate();
        }
    }


    public void setRoundType(@RoundImageType.RoundType String roundType) {
        if (!this.mRoundType.equals(roundType)) {
            this.mRoundType = roundType;
            if (!this.mRoundType.equals(RoundImageType.ROUND_TYPE_ROUND) && (!this.mRoundType.equals(RoundImageType.ROUND_TYPE_CIRCLE))) {
                this.mRoundType = RoundImageType.ROUND_TYPE_NORMAL;
            }
            requestLayout();
        }
    }

    public void setCornerType(@RoundImageType.CornerType String cornerType) {
        if (!this.mCornerType.equals(cornerType)) {
            if (mRoundType.equals(RoundImageType.ROUND_TYPE_ROUND) && mBorderRadius > 0) {
                this.mCornerType = cornerType;
                requestLayout();
            }
        }
    }


    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putString(STATE_TYPE, mRoundType);
        bundle.putInt(STATE_BORDER_RADIUS, mBorderRadius);
        bundle.putInt(STATE_ROUND_RADIUS, mRoundRadius);
        bundle.putInt(STATE_BORDER_WIDTH, mBorderWidth);
        bundle.putInt(STATE_BORDER_COLOR, mBorderColor);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state)
                    .getParcelable(STATE_INSTANCE));
            this.mRoundType = bundle.getString(STATE_TYPE);
            this.mBorderRadius = bundle.getInt(STATE_BORDER_RADIUS);
            this.mRoundRadius = bundle.getInt(STATE_ROUND_RADIUS);
            this.mBorderWidth = bundle.getInt(STATE_BORDER_WIDTH);
            this.mBorderColor = bundle.getInt(STATE_BORDER_COLOR);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, getResources().getDisplayMetrics());
    }

    private Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            return bitmapDrawable.getBitmap();
        }
        Bitmap bitmap = null;
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1,
                    drawable.getOpacity() != PixelFormat.OPAQUE
                            ? Bitmap.Config.ARGB_8888
                            : Bitmap.Config.RGB_565);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    drawable.getOpacity() != PixelFormat.OPAQUE
                            ? Bitmap.Config.ARGB_8888
                            : Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
