package com.android.open9527.dialog;

import android.view.Gravity;

/**
 * @author open_9527
 * Create at 2021/4/21
 **/
public interface DialogGravity {

    int LEFT_TOP = Gravity.START | Gravity.TOP;
    int CENTER_TOP = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
    int RIGHT_TOP = Gravity.END | Gravity.TOP;

    int LEFT_CENTER = Gravity.START | Gravity.CENTER_VERTICAL;
    int CENTER_CENTER = Gravity.CENTER;

    int RIGHT_CENTER = Gravity.END | Gravity.CENTER_VERTICAL;
    int LEFT_BOTTOM = Gravity.START | Gravity.BOTTOM;
    int CENTER_BOTTOM = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
    int RIGHT_BOTTOM = Gravity.END | Gravity.BOTTOM;


}
