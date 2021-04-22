package com.android.custom.pkg;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.custom.pkg.dialog.DialogActivity;
import com.android.custom.pkg.shadow.ShadowActivity;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.filter.AppFilter;
import com.android.open9527.filter.FilterColor;
import com.android.open9527.filter.color.NightColor;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.ActivityUtils;

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
        return new DataBindingConfig(R.layout.custom_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                ;
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
    }

    public class ClickProxy {

        public View.OnClickListener backClick = v -> {
            finish();
        };

        public View.OnClickListener nightClick = v -> {
            FilterColor filterColor = AppFilter.getColor();
            if (filterColor instanceof NightColor) {
                AppFilter.tint(null);
            } else {
                AppFilter.tint(new NightColor());
            }

        };

        public View.OnClickListener dialogClick = v -> {
            ActivityUtils.startActivity(DialogActivity.class);
        };

        public View.OnClickListener shadowClick = v -> {
            ActivityUtils.startActivity(ShadowActivity.class);
        };

    }
}
