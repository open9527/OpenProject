package com.android.custom.pkg;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class CustomActivity extends BaseCommonActivity {

    private CustomViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(CustomViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.custom_activity, BR.vm, mViewModel);
    }
}
