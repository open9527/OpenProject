package com.android.custom.pkg.webview.bridge;

import android.os.Bundle;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.common.cell.CommonEmptyCell;
import com.android.open9527.common.cell.CommonLineCell;
import com.android.open9527.common.cell.CommonNoMoreDataCell;
import com.android.open9527.common.page.BaseCommonFragment;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;

/**
 * @author open_9527
 * Create at 2021/4/29
 **/
public class BridgeContainerFragment extends BaseCommonFragment {

    private BridgeViewModel mViewModel;

    public static BridgeContainerFragment newInstance() {
        return new BridgeContainerFragment();
    }

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(BridgeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.bridge_container_fragment, BR.vm, mViewModel)
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>());
    }

    @Override
    public void initRequest() {
        super.initRequest();
        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }
}
