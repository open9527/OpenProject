package com.open9527.wanandroid.pkg.main.project.content;

import android.content.Intent;
import android.os.BaseBundle;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.bundle.BaseBundleData;
import com.android.open9527.common.bundle.BundleUtils;
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
 * Create at 2021/1/18
 **/
public class ProjectContentFragment extends BaseCommonFragment {

    private int mPage = 0;

    private ProjectContentViewModel mViewModel;
    private BundleData mBundleData;


    public static ProjectContentFragment newInstance(String cId, String title) {
        ProjectContentFragment fragment = new ProjectContentFragment();
        fragment.setArguments(BundleUtils.create(new BundleData(cId, title)));
        return fragment;
    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(ProjectContentViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.project_content_fragment, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.layoutManager, new WrapContentLinearLayoutManager(mActivity))
                .addBindingParam(BR.itemDecoration, new SpacesItemDecoration(mActivity).setParam(R.color.common_line_color, 10))
                .addBindingParam(BR.adapter, new BaseBindingCellAdapter<>());

    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
//        mBundleData =  getBundleData();
        mBundleData = BundleUtils.getBundleData(bundle);
        if (mBundleData == null) {
            throw new IllegalArgumentException("mBundleData is null");
        }
        mViewModel.valueTitle.set(mBundleData.getTitle());
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
        mViewModel.projectRequest.requestProject(mPage,mBundleData.getId(), OkHttpUtils.get(this));
    }

    private static class BundleData extends BaseBundleData {
        private String id;
        private String title;

        public BundleData(String id, String title) {
            this.id = id;
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
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


    public <T extends BaseBundleData> T getBundleData() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return null;
        }

        return (T) bundle.getSerializable(BaseBundleData.BUNDLE_NAME);
    }
}
