package com.android.open9527.common.widget;


import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.customview.widget.ViewDragHelper;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

import java.lang.reflect.Field;


/**
 * @author open_9527
 * Create at 2021/6/4
 *
 * 实现PhotoView 拖拽效果(有bug)
 **/

public class PhotoViewContainer extends FrameLayout {
    private static final String TAG = "PhotoViewContainer";
    private ViewDragHelper dragHelper;
    public ViewPager viewPager;
    private int HideTopThreshold = 80;
    private int maxOffset;
    private OnDragChangeListener dragChangeListener;
    public boolean isReleasing = false;

    public PhotoViewContainer(@NonNull Context context) {
        this(context, null);
    }

    public PhotoViewContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoViewContainer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        HideTopThreshold = dip2px(HideTopThreshold);
        dragHelper = ViewDragHelper.create(this, cb);
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        viewPager = (ViewPager) getChildAt(0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        maxOffset = getHeight() / 3;
    }

    boolean isVertical = false;
    private float touchX, touchY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getPointerCount() > 1) return super.dispatchTouchEvent(ev);
        try {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchX = ev.getX();
                    touchY = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float dx = ev.getX() - touchX;
                    float dy = ev.getY() - touchY;
                    viewPager.dispatchTouchEvent(ev);
                    isVertical = (Math.abs(dy) > Math.abs(dx));
                    touchX = ev.getX();
                    touchY = ev.getY();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    touchX = 0;
                    touchY = 0;
                    isVertical = false;
                    break;
            }
        } catch (Exception e) {
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isTopOrBottomEnd() {
        PhotoView photoView = getCurrentPhotoView();
        if (photoView == null) return false;
        PhotoViewAttacher photoViewAttacher = photoView.getAttacher();
        //  isTopEnd = (mVerticalScrollEdge == VERTICAL_EDGE_TOP) && getScale() != 1f;
        //            isBottomEnd = (mVerticalScrollEdge == VERTICAL_EDGE_BOTTOM) && getScale() != 1f;
        //TODO:
        int mVerticalScrollEdge = getIntValue(photoViewAttacher, "mVerticalScrollEdge");
        int VERTICAL_EDGE_TOP = getIntValue(photoViewAttacher, "VERTICAL_EDGE_TOP");
        int VERTICAL_EDGE_BOTTOM = getIntValue(photoViewAttacher, "VERTICAL_EDGE_BOTTOM");

        return (photoViewAttacher.getScale() != 1f) && (mVerticalScrollEdge == VERTICAL_EDGE_TOP || mVerticalScrollEdge == VERTICAL_EDGE_BOTTOM);

//        return photoView != null && (photoView.attacher.isTopEnd || photoView.attacher.isBottomEnd);
    }

    private PhotoView getCurrentPhotoView() {
        return (PhotoView) viewPager.getChildAt(viewPager.getCurrentItem());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = dragHelper.shouldInterceptTouchEvent(ev);
        if (ev.getPointerCount() > 1 && ev.getAction() == MotionEvent.ACTION_MOVE) return false;
        if (isTopOrBottomEnd() && isVertical) return true;
        return result && isVertical;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getPointerCount() > 1) return false;
        try {
            dragHelper.processTouchEvent(ev);
            return true;
        } catch (Exception e) {
        }
        return true;
    }

    ViewDragHelper.Callback cb = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            return !isReleasing;
        }

        @Override
        public int getViewVerticalDragRange(@NonNull View child) {
            return 1;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            int t = viewPager.getTop() + dy / 2;
            if (t >= 0) {
                return Math.min(t, maxOffset);
            } else {
                return -Math.min(-t, maxOffset);
            }
        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            if (changedView != viewPager) {
                viewPager.offsetTopAndBottom(dy);
            }
            float fraction = Math.abs(top) * 1f / maxOffset;
            float pageScale = 1 - fraction * .2f;
            viewPager.setScaleX(pageScale);
            viewPager.setScaleY(pageScale);
            changedView.setScaleX(pageScale);
            changedView.setScaleY(pageScale);
            if (dragChangeListener != null) {
                dragChangeListener.onDragChange(dy, pageScale, fraction);
            }

        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (Math.abs(releasedChild.getTop()) > HideTopThreshold) {
                if (dragChangeListener != null) dragChangeListener.onRelease();
            } else {
                dragHelper.smoothSlideViewTo(viewPager, 0, 0);
                dragHelper.smoothSlideViewTo(releasedChild, 0, 0);
                ViewCompat.postInvalidateOnAnimation(PhotoViewContainer.this);
            }
        }
    };

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(false)) {
            ViewCompat.postInvalidateOnAnimation(PhotoViewContainer.this);
        }
    }

    public int dip2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setOnDragChangeListener(OnDragChangeListener listener) {
        this.dragChangeListener = listener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isReleasing = false;
    }

    private int getIntValue(PhotoViewAttacher photoViewAttacher, String name) {
        try {
            Field f = photoViewAttacher.getClass().getDeclaredField(name);
            f.setAccessible(true);
            Object obj = f.get(photoViewAttacher);
            return (Integer) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
