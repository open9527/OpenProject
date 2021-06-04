package com.android.custom.pkg.layout.grid;

import android.view.View;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;

/**
 * @author open_9527
 * Create at 2021/6/3
 **/
public class GridLayoutActivity extends BaseCommonActivity {

    private GridLayoutViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(GridLayoutViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.grid_layout_activity, BR.vm, mViewModel)
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>())
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initRequest() {
        mViewModel.requestData();
    }

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };
    }
}
