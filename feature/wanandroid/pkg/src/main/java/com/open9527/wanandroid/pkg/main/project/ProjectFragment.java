package com.open9527.wanandroid.pkg.main.project;

import com.android.open9527.common.page.BaseCommonFragment;
import com.android.open9527.okhttp.OkHttpUtils;
import com.android.open9527.page.DataBindingConfig;
import com.open9527.wanandroid.pkg.BR;
import com.open9527.wanandroid.pkg.R;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class ProjectFragment extends BaseCommonFragment {


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
        return new DataBindingConfig(R.layout.project_fragment, BR.vm, mViewModel);

    }

    @Override
    public void initRequest() {
        super.initRequest();
        requestProjectTree();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mViewModel.projectRequest.getProjectTreeLiveData().observe(getViewLifecycleOwner(), listDataResult -> {
            mViewModel.initTab(getChildFragmentManager(),listDataResult.getResult());
        });

    }

    private void requestProjectTree() {
        mViewModel.projectRequest.requestProjectTreeApi(OkHttpUtils.get(this));
    }
}
