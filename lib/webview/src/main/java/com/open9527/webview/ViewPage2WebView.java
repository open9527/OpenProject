package com.open9527.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;


/**
 * @author open_9527
 * Create at 2021/3/1
 * <p>
 * eg:支持ViewPager2嵌套滚动的 WebView
 **/
public class ViewPage2WebView extends WebView {

    public ViewPage2WebView(Context context) {
        super(context);
    }

    public ViewPage2WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPage2WebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewPage2WebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private boolean mClampedY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            super.requestDisallowInterceptTouchEvent(!mClampedY);
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            mClampedY = false;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        mClampedY = clampedY;
        if (!clampedY) {
            super.requestDisallowInterceptTouchEvent(true);
        }
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

}