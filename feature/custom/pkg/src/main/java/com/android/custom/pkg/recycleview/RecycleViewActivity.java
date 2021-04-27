package com.android.custom.pkg.recycleview;

import android.view.View;


import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.recycleview.cell.CollectCell;
import com.android.custom.pkg.recycleview.user.ContentVo;
import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.okhttp.OkHttpUtils;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.blankj.utilcode.util.CollectionUtils;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * @author open_9527
 * Create at 2021/4/25
 **/
public class RecycleViewActivity extends BaseCommonActivity {
    private int mPage = 0;

    private RecycleViewViewModel mViewViewModel;


    @Override
    protected void initViewModel() {
        mViewViewModel = getActivityScopeViewModel(RecycleViewViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.recycleview_activity, BR.vm, mViewViewModel)
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>())
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initRequest() {
//        requestLogin();
        requestCollectList();
    }

    private void requestLogin() {
        mViewViewModel.requestLogin("open_9527", " ", OkHttpUtils.post(this));
    }

    private void requestCollectList() {
        mViewViewModel.requestCollectList(mPage, OkHttpUtils.get(this));
    }

    @Override
    public void initEvent() {
        mViewViewModel.getCollectListLiveData().observe(this, objectDataResult -> {
            List<ContentVo> contentVoList = new ArrayList<>();
            if (objectDataResult.getResponseStatus().isSuccess()) {
                contentVoList = objectDataResult.getResult();

            }
            setData(contentVoList);
        });
    }

    private void setData(List<ContentVo> contentVoList) {
        List<BaseBindingCell> cellList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(contentVoList)) {
            for (ContentVo contentVo : contentVoList) {
                cellList.add(new CollectCell(contentVo));
            }
            mViewViewModel.valueNoMoreData.set(contentVoList.size() < 20);
        }
        mViewViewModel.valueCellList.getValue().addAll(cellList);
        mViewViewModel.valueCellList.setValue(mViewViewModel.valueCellList.getValue());


        mViewViewModel.valueList.getValue().addAll(contentVoList);
        mViewViewModel.valueList.setValue(mViewViewModel.valueList.getValue());

        mViewViewModel.valueRefresh.setValue(mPage == 0);
    }


    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };

        public IRefresh<Boolean> onRefreshListeners = new IRefresh<Boolean>() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout, Boolean isRefresh) {
                if (isRefresh) {
                    mPage = 0;
                    mViewViewModel.valueCellList.getValue().clear();
                    mViewViewModel.valueList.getValue().clear();
                } else {
                    mPage++;
                }
                requestCollectList();
            }
        };
    }
}
