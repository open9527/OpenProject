package com.open9527.wanandroid.pkg.main.share;


import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.page.BaseCommonFragment;
import com.android.open9527.okhttp.OkHttpUtils;
import com.android.open9527.okhttp.listener.OnHttpListener;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCellAdapter;
import com.android.open9527.recycleview.decoration.SpacesItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentLinearLayoutManager;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.wanandroid.pkg.BR;
import com.open9527.wanandroid.pkg.R;
import com.open9527.wanandroid.pkg.main.WanAndroidViewModel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import okhttp3.Call;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class ShareFragment extends BaseCommonFragment implements OnHttpListener {

    private int mPage = 0;
    private ShareViewModel mViewModel;

    public static ShareFragment newInstance() {
        return new ShareFragment();
    }


    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(ShareViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.share_fragment, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.layoutManager, new WrapContentLinearLayoutManager(mActivity))
                .addBindingParam(BR.itemDecoration, new SpacesItemDecoration(mActivity).setFooterNoShowDivider(1).setParam(R.color.common_line_color, 10))
                .addBindingParam(BR.adapter, new BaseBindingCellAdapter<>());
    }



    @Override
    public void initRequest() {
        super.initRequest();
        requestShare();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mViewModel.shareRequest.getShareLiveData().observe(getViewLifecycleOwner(), dataResult -> {
            mViewModel.onCreateCells(mPage, dataResult.getResult().getDataList());

        });
    }

    public class ClickProxy {
        public IRefresh<Boolean> onRefreshListeners = new IRefresh<Boolean>() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout, Boolean isRefresh) {
                mPage = (isRefresh ? 0 : ++mPage);
                requestShare();
            }
        };
    }

    private void requestShare() {
        mViewModel.shareRequest.requestShare(mPage, OkHttpUtils.get(getViewLifecycleOwner()));
    }

    @Override
    public void onStart(Call call) {
        LogUtils.i(TAG, "onStart");
//        mViewModel.valueCloseHeaderOrFooter.set(false);
    }

    @Override
    public void onEnd(Call call) {
        LogUtils.i(TAG, "onEnd");
//        mViewModel.valueCloseHeaderOrFooter.set(true);

    }
}
