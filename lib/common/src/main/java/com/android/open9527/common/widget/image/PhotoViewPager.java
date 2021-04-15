package com.android.open9527.common.widget.image;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author open_9527
 * Create at 2021/1/25
 **/


public final class PhotoViewPager extends ViewPager {

    public PhotoViewPager(Context context) {
        super(context);
    }

    public PhotoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 当 PhotoView 和 ViewPager 组合时 ，用双指进行放大时 是没有问题的，但是用双指进行缩小的时候，程序就会崩掉
        // 并且抛出java.lang.IllegalArgumentException: pointerIndex out of range
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ignored) {
            LogUtils.i("PhotoViewPager", "Exception");
            return false;
        }
    }
}
