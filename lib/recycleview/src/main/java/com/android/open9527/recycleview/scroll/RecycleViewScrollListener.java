package com.android.open9527.recycleview.scroll;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class RecycleViewScrollListener extends RecyclerView.OnScrollListener {

    private IScrollListener scrollListener;
    private int mThreshold = 50;
    private int mDistance = 0;

    public RecycleViewScrollListener(int distance, IScrollListener scrollListener) {
        this.mThreshold = distance;
        this.scrollListener = scrollListener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//        super.onScrolled(recyclerView, dx, dy);
        mDistance += dy;
        scrollListener.onScrimsStateChange(recyclerView, mDistance >= mThreshold);

    }

    public interface IScrollListener {
        void onScrimsStateChange(@NonNull RecyclerView recyclerView, boolean shown);
    }

    public void setOnScrimsListener(int distance, IScrollListener listener) {
        mThreshold = distance;
        scrollListener = listener;
    }
}
