package com.android.open9527.common.binding.recycleview;

import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.recycleview.RecycleViewUtils;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellAdapter;
import com.android.open9527.recycleview.layout_manager.WrapContentGridLayoutManager;
import com.android.open9527.recycleview.layout_manager.WrapContentLinearLayoutManager;
import com.android.open9527.recycleview.layout_manager.WrapContentStaggeredGridLayoutManager;
import com.blankj.utilcode.util.LogUtils;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

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
        }
        recyclerView.setHasFixedSize(hasFixedSize);
        if (layoutManager != null) {

            if (layoutManager instanceof GridLayoutManager) {
                int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                recyclerView.setLayoutManager(new WrapContentGridLayoutManager(recyclerView.getContext(), spanCount));

            } else if (layoutManager instanceof LinearLayoutManager) {
                recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(recyclerView.getContext()));

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

    @BindingAdapter(value = { "bindRvList"}, requireAll = false)
    public static void setBindingRecycleViewData(RecyclerView recyclerView, List list) {
        if (recyclerView == null) return;
        if (recyclerView.getAdapter() != null) {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();

            if (adapter instanceof BaseBindingCellAdapter) {
                BaseBindingCellAdapter<BaseBindingCell> bindingCellAdapter = (BaseBindingCellAdapter) adapter;
                bindingCellAdapter.submitItems(list);
            }

        }
    }


}
