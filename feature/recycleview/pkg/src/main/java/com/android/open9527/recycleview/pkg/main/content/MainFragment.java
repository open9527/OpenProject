package com.android.open9527.recycleview.pkg.main.content;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.page.BaseCommonFragment;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCellAdapter;
import com.android.open9527.recycleview.decoration.SpacesItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentLinearLayoutManager;
import com.android.open9527.recycleview.pkg.BR;
import com.android.open9527.recycleview.pkg.R;
import com.android.open9527.recycleview.pkg.main.RecycleViewViewModel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

/**
 * @author open_9527
 * Create at 2021/1/11
 **/
public class MainFragment extends BaseCommonFragment {

    private MainFragmentViewModel mViewModel;
    private RecycleViewViewModel mMainViewModel;

    private int mPage = 1;


    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(MainFragmentViewModel.class);
        mMainViewModel = getActivityScopeViewModel(RecycleViewViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.main_fragment, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.layoutManager, new WrapContentLinearLayoutManager(mActivity))
                .addBindingParam(BR.itemDecoration, new SpacesItemDecoration(mActivity).setParam(R.color.color_line_main, 10))
                .addBindingParam(BR.adapter, new BaseBindingCellAdapter<>());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);

    }

    @Override
    public void initRequest() {
        super.initRequest();
        mViewModel.createCells(mPage);
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }

    public class ClickProxy {
        public IRefresh<Boolean> onRefreshListeners = new IRefresh<Boolean>() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout, Boolean isRefresh) {
                if (isRefresh) {
                    mPage = 1;
                } else {
                    mPage++;
                }
                mViewModel.createCells(mPage);
                refreshLayout.closeHeaderOrFooter();
            }
        };
    }




}
