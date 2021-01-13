
package com.android.open9527.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 * 全屏播放VideoView
 **/

public class FullScreenVideoView extends VideoView {

    public FullScreenVideoView(Context context) {
        super(context);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
    }
}
