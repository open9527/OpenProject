package com.android.open9527.common.widget;

/**
 * @author open_9527
 * Create at 2021/6/24
 **/
public interface OnDragChangeListener {
    void onRelease();
    void onDragChange(int dy, float scale, float fraction);
}
