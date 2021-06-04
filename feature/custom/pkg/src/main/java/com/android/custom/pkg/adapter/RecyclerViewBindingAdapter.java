package com.android.custom.pkg.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.open9527.recycleview.scroll.BottomSmoothScroller;
import com.blankj.utilcode.util.LogUtils;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/4/25
 **/
public class RecyclerViewBindingAdapter {
    @BindingAdapter(value = {"adapter"}, requireAll = false)
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter(value = {"submitList"}, requireAll = false)
    public static void submitList(RecyclerView recyclerView, List list) {
        if (recyclerView.getAdapter() != null) {
            ListAdapter adapter = (ListAdapter) recyclerView.getAdapter();
            adapter.submitList(list);

        }
    }

    @BindingAdapter(value = {"notifyCurrentListChanged"}, requireAll = false)
    public static void notifyCurrentListChanged(RecyclerView recyclerView, boolean notifyCurrentListChanged) {
        if (notifyCurrentListChanged) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @BindingAdapter(value = {"autoScrollToTopWhenInsert", "autoScrollToBottomWhenInsert"}, requireAll = false)
    public static void autoScroll(RecyclerView recyclerView,
                                  boolean autoScrollToTopWhenInsert,
                                  boolean autoScrollToBottomWhenInsert) {

        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
//                    LogUtils.i("autoScroll", "positionStart: " + positionStart
//                            + " itemCount: " + itemCount
//                            + " adapter.getItemCount() " + recyclerView.getAdapter().getItemCount());
                    if (autoScrollToTopWhenInsert) {
                        recyclerView.scrollToPosition(0);
                    } else if (autoScrollToBottomWhenInsert && (positionStart > 0)) {
                        LogUtils.i("autoScroll", "positionStart: " + positionStart);
//                        onScroller(recyclerView, positionStart);
//                        recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount());
                        recyclerView.scrollToPosition(positionStart - 2);
                    }
                }
            });
        }
    }

    private static void onScroller(@NonNull RecyclerView recyclerView, int position) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (linearLayoutManager != null) {
            BottomSmoothScroller smoothScroller = new BottomSmoothScroller(recyclerView.getContext());
            smoothScroller.setTargetPosition(position - 2);
            linearLayoutManager.startSmoothScroll(smoothScroller);
        }
    }
}
