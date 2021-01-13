package com.android.open9527.recycleview.scroll;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class BottomSmoothScroller extends LinearSmoothScroller {

    public BottomSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_END;
    }

    @Override
    protected int getVerticalSnapPreference() {

        return SNAP_TO_END;
    }

//eg:
//    private void onScroller(@NonNull LinearLayoutManager linearLayoutManager, @NonNull Context context, int position) {
//        BottomSmoothScroller bottomSmoothScroller = new BottomSmoothScroller(context);
//        bottomSmoothScroller.setTargetPosition(position);
//        linearLayoutManager.startSmoothScroll(topSmoothScroller);
//    }
}
