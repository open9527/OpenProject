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
    
    public RecycleViewScrollListener(IScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//        super.onScrolled(recyclerView, dx, dy);
        mDistance += dy;
        scrollListener.onScrimsStateChange(recyclerView, mDistance >= mThreshold);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });

    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                //当屏幕停止滚动，加载图片
                scrollListener.onImageLoadChange(recyclerView, true);
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                //当屏幕滚动且用户使用的触碰或手指还在屏幕上，停止加载图片
            case RecyclerView.SCROLL_STATE_SETTLING:
                //由于用户的操作，屏幕产生惯性滑动，停止加载图片
                scrollListener.onImageLoadChange(recyclerView, false);
                break;

        }

    }

    public interface IScrollListener {
        default void onScrimsStateChange(@NonNull RecyclerView recyclerView, boolean shown) {
        }

        default void onImageLoadChange(@NonNull RecyclerView recyclerView, boolean loadImage) {

        }
    }

    public void setOnScrimsListener(int distance, IScrollListener listener) {
        mThreshold = distance;
        scrollListener = listener;
    }
}
