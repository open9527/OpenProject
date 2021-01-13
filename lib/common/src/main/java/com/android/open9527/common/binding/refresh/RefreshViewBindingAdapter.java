package com.android.open9527.common.binding.refresh;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;


public class RefreshViewBindingAdapter {

    @BindingAdapter(value = {"bindRefreshListener", "bindRefreshNoMoreData"}, requireAll = false)
    public static void setBindingRefresh(SmartRefreshLayout smartRefreshLayout, IRefresh<Boolean> iRefresh, boolean noMoreData) {
        if (smartRefreshLayout == null) return;
        smartRefreshLayout.setNoMoreData(noMoreData);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (iRefresh != null) {
                    iRefresh.loadComplete(refreshLayout,false);
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.resetNoMoreData();
                if (iRefresh != null) {
                    iRefresh.loadComplete(refreshLayout,true);
                }
            }
        });
    }

}
