package com.android.copy.pkg;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class CopyActivity extends BaseCommonActivity {

    private CopyViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(CopyViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.copy_activity, BR.vm, mViewModel);
    }
}
