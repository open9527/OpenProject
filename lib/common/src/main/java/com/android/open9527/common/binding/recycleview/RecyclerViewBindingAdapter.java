package com.android.open9527.common.binding.recycleview;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.open9527.common.cell.CommonEmptyCell;
import com.android.open9527.recycleview.RecycleViewUtils;
import com.android.open9527.recycleview.layout_manager.WrapContentGridLayoutManager;
import com.android.open9527.recycleview.layout_manager.WrapContentLinearLayoutManager;
import com.android.open9527.recycleview.layout_manager.WrapContentPickerLayoutManager;
import com.android.open9527.recycleview.layout_manager.WrapContentStaggeredGridLayoutManager;
import com.android.open9527.recycleview.scroll.RecycleViewScrollListener;
import com.blankj.utilcode.util.CollectionUtils;

import java.util.List;


public class RecyclerViewBindingAdapter {
    private static final String TAG = "RecyclerViewBindingAdapter";


    /**
     * 配置RecycleViewAdapter
     *
     * @param recyclerView   recyclerView
     * @param mAdapter       mAdapter
     * @param layoutManager  layoutManager
     * @param itemDecoration itemDecoration
     */
    @BindingAdapter(value = {
            "bindRvAdapter",
            "bindRvLayoutManager",
            "bindRvItemDecoration",
            "bindRvHasFixedSize",
            "bindRvAnim"},
            requireAll = false)
    public static void setBindingRecyclerViewAdapter(RecyclerView recyclerView,
                                                     RecyclerView.Adapter mAdapter,
                                                     RecyclerView.LayoutManager layoutManager,
                                                     RecyclerView.ItemDecoration itemDecoration,
                                                     boolean hasFixedSize,
                                                     boolean bindRvAnim
    ) {
        if (recyclerView == null || mAdapter == null) return;
        if (!bindRvAnim) {
            //移除动画
            RecycleViewUtils.closeDefaultAnimator(recyclerView);
            recyclerView.setItemAnimator(null);
        }
        recyclerView.setHasFixedSize(hasFixedSize);
        if (layoutManager != null) {
            if (layoutManager instanceof WrapContentPickerLayoutManager) {
                recyclerView.setLayoutManager(layoutManager);

            } else if (layoutManager instanceof GridLayoutManager) {
                int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                recyclerView.setLayoutManager(new WrapContentGridLayoutManager(recyclerView.getContext(), spanCount));

            } else if (layoutManager instanceof LinearLayoutManager) {
                int orientation = ((LinearLayoutManager) layoutManager).getOrientation();
                boolean reverseLayout = ((LinearLayoutManager) layoutManager).getReverseLayout();
                recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(recyclerView.getContext(), orientation, reverseLayout));

            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
                int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
                recyclerView.setLayoutManager(new WrapContentStaggeredGridLayoutManager(spanCount, orientation));

            } else {
                recyclerView.setLayoutManager(layoutManager);
            }
        }
        if (itemDecoration != null) {
            if (recyclerView.getItemDecorationCount() == 0) {
                recyclerView.addItemDecoration(itemDecoration);
            }
        }

        recyclerView.setAdapter(mAdapter);

    }


    @BindingAdapter(value = {"bindRvList", "bindRvSpanSizeLookup"}, requireAll = false)
    public static void setBindingRecycleViewData(RecyclerView recyclerView, List list, GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        if (recyclerView == null) return;
        if (recyclerView.getAdapter() != null) {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter instanceof ListAdapter) {
                ListAdapter listAdapter = (ListAdapter) adapter;
                listAdapter.submitList(list);
                setEmptyLayoutManager(recyclerView, list);
                if (spanSizeLookup != null) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof GridLayoutManager) {
                        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                        gridLayoutManager.setSpanSizeLookup(spanSizeLookup);
                    }
                }
            }

        }
    }

    @BindingAdapter(value = {"bindRvScrollListener"}, requireAll = false)
    public static void setBindingRecycleViewScrollListener(RecyclerView recyclerView, RecycleViewScrollListener scrollListener) {
        if (recyclerView == null || scrollListener == null) return;
        recyclerView.addOnScrollListener(scrollListener);
    }


    private static void setEmptyLayoutManager(RecyclerView recyclerView, List list) {
        if (CollectionUtils.isNotEmpty(list) && list.size() == 1) {
            if (list.get(0) instanceof CommonEmptyCell) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof GridLayoutManager) {
                    recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(recyclerView.getContext()));
                }
            }
        }
    }


    @BindingAdapter(value = {
            "bindPickerLayoutManager",
            "bindPickerListener",
            "bindPickerPosition",
    },
            requireAll = false)
    public static void setBindingPickerLayoutManager(RecyclerView recyclerView, WrapContentPickerLayoutManager layoutManager, WrapContentPickerLayoutManager.OnPickerListener listener, int position) {
        if (recyclerView == null || layoutManager == null) return;
        recyclerView.setLayoutManager(layoutManager);
        if (listener != null) {
            layoutManager.setOnPickerListener(listener);
        }
        if (position > -1) {
            recyclerView.scrollToPosition(position);
        }
    }

}
