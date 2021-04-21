package com.android.custom.pkg.shadow;

import android.view.View;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;

/**
 * @author open_9527
 * Create at 2021/4/21
 **/
public class ShadowActivity extends BaseCommonActivity {

    private ShadowViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(ShadowViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.shadow_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                ;
    }


    public class ClickProxy {

        public View.OnClickListener backClick = v -> {
            finish();
        };
    }
}
