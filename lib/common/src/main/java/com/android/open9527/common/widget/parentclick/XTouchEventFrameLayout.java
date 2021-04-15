package com.android.open9527.common.widget.parentclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class XTouchEventFrameLayout extends FrameLayout {

    private InterceptListener mInPutListener;

    public XTouchEventFrameLayout(@NonNull Context context) {
        super(context);
    }

    public XTouchEventFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public XTouchEventFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (mInPutListener != null) {
                mInPutListener.onActionDown(this);
            }
        }
        return super.onInterceptTouchEvent(ev);
    }


    public InterceptListener getInPutListener() {
        return mInPutListener;
    }

    public void setInPutListener(InterceptListener inPutListener) {
        mInPutListener = inPutListener;
    }

    public interface InterceptListener {
        default void onInterceptTouchEvent(View view, MotionEvent event) {
        }
        void onActionDown(View view);
    }
}

