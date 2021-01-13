package com.android.open9527.recycleview.pkg.main;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.pkg.BR;
import com.android.open9527.recycleview.pkg.R;
import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.tabs.TabLayout;

/**
 * @author open_9527
 * Create at 2021/1/11
 **/
public class RecycleViewActivity extends BaseCommonActivity {

    private RecycleViewViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(RecycleViewViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.recycleview_activity, BR.vm, mViewModel);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        mViewModel.initTab(getSupportFragmentManager(), onTabSelectedListener);

    }


    public TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            LogUtils.i(TAG, "onTabSelected");
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            LogUtils.i(TAG, "onTabUnselected");
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            LogUtils.i(TAG, "onTabReselected");
        }
    };

}
