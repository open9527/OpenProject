package com.open9527.wanandroid.pkg.main.project;

import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.page.BaseCommonFragment;
import com.android.open9527.okhttp.OkHttpUtils;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCellAdapter;
import com.android.open9527.recycleview.decoration.SpacesItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentLinearLayoutManager;
import com.open9527.wanandroid.pkg.BR;
import com.open9527.wanandroid.pkg.R;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class ProjectFragment extends BaseCommonFragment {

    private int mPage = 0;
    private ProjectViewModel mViewModel;

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(ProjectViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.project_fragment, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.layoutManager, new WrapContentLinearLayoutManager(mActivity))
                .addBindingParam(BR.itemDecoration, new SpacesItemDecoration(mActivity).setParam(R.color.color_line_main, 10))
                .addBindingParam(BR.adapter, new BaseBindingCellAdapter<>());
    }

    @Override
    public void initRequest() {
        super.initRequest();
        requestProject();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mViewModel.projectRequest.getProjectLiveData().observe(getViewLifecycleOwner(), dataVoDataResult -> {
            mViewModel.onCreateCells(mPage, dataVoDataResult.getResult().getDataList());

        });
    }

    private void requestProject() {
        mViewModel.projectRequest.requestProject(mPage, OkHttpUtils.get(this));
    }

    public class ClickProxy {
        public IRefresh<Boolean> onRefreshListeners = new IRefresh<Boolean>() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout, Boolean isRefresh) {
                mPage = (isRefresh ? 0 : ++mPage);
                requestProject();
            }
        };
    }
}
