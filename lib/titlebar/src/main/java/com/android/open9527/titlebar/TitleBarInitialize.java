package com.android.open9527.titlebar;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * @author open_9527
 * Create at 2021/1/20
 **/
public interface TitleBarInitialize {

    /**
     * 获取左标题 View
     */
    TextView getLeftView(Context context);

    /**
     * 获取中间标题 View
     */
    TextView getTitleView(Context context);

    /**
     * 获取右标题 View
     */
    TextView getRightView(Context context);

    /**
     * 获取分割线 View
     */
    View getLineView(Context context);


    /**
     * 获取子控件的水平内间距
     */
    int getHorizontalPadding(Context context);

    /**
     * 获取子控件的垂直内间距
     */
    int getVerticalPadding(Context context);

}
