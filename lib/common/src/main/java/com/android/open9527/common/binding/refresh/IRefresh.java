package com.android.open9527.common.binding.refresh;


import com.scwang.smart.refresh.layout.api.RefreshLayout;

public interface IRefresh<T> {

    default void onRefresh(RefreshLayout refreshLayout, T isRefresh) {

    }

}
