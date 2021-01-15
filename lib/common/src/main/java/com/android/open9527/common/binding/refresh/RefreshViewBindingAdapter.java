package com.android.open9527.common.binding.refresh;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;


public class RefreshViewBindingAdapter {

    @BindingAdapter(value = {"bindRefreshListener", "bindRefreshNoMoreData", "bindRefreshCloseHeaderOrFooter"}, requireAll = false)
    public static void setBindingRefresh(SmartRefreshLayout smartRefreshLayout, IRefresh<Boolean> iRefresh, boolean noMoreData, boolean closeHeaderOrFooter) {
        if (smartRefreshLayout == null) return;
        smartRefreshLayout.setNoMoreData(noMoreData);
        smartRefreshLayout.setEnableFooterFollowWhenNoMoreData(noMoreData);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (noMoreData) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if (iRefresh != null) {
                    iRefresh.onRefresh(refreshLayout, false);
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.resetNoMoreData();
                if (iRefresh != null) {
                    iRefresh.onRefresh(refreshLayout, true);
                    refreshLayout.finishRefresh();
                }
            }
        });
    }

}
