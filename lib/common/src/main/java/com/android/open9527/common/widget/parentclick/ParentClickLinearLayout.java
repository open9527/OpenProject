package com.android.open9527.common.widget.parentclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;


public class ParentClickLinearLayout extends LinearLayout {
    public ParentClickLinearLayout(Context context) {
        this(context, null, 0);
    }

    public ParentClickLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParentClickLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
