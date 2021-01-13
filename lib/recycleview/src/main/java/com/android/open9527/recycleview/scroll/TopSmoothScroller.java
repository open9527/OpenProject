package com.android.open9527.recycleview.scroll;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class TopSmoothScroller extends LinearSmoothScroller {

    public TopSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;
    }

    @Override
    protected int getVerticalSnapPreference() {

        return SNAP_TO_START;
    }

//eg:
//    private void onScroller(@NonNull LinearLayoutManager linearLayoutManager, @NonNull Context context, int position) {
//        TopSmoothScroller topSmoothScroller = new TopSmoothScroller(context);
//        topSmoothScroller.setTargetPosition(position);
//        linearLayoutManager.startSmoothScroll(topSmoothScroller);
//    }

}
