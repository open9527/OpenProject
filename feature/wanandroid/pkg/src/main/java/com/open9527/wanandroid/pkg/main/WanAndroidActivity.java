package com.open9527.wanandroid.pkg.main;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.BarUtils;
import com.open9527.wanandroid.pkg.BR;
import com.open9527.wanandroid.pkg.R;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class WanAndroidActivity extends BaseCommonActivity {

    private WanAndroidViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(WanAndroidViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.wanandroid_activity, BR.vm, mViewModel);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        mViewModel.initTab(getSupportFragmentManager());
    }

    private long exitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showShortToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            AppUtils.exitApp();
        }
    }
}
