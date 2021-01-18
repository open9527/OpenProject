package com.open9527.wanandroid.pkg.main.h5;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.ActivityUtils;
import com.open9527.wanandroid.pkg.BR;
import com.open9527.wanandroid.pkg.R;
import com.open9527.wanandroid.pkg.bean.BaseBundleData;
import com.open9527.wanandroid.pkg.bean.BundleCreate;

/**
 * @author open_9527
 * Create at 2021/1/18
 **/
public class H5Activity extends BaseCommonActivity {

    private H5ViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(H5ViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.h5_activity, BR.vm, mViewModel);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        H5Bundle h5Bundle = getBundleData();
        mViewModel.valueH5Url.set(h5Bundle.getUrl());
        mViewModel.valueTitle.set(h5Bundle.getTitle());

    }

    public static void startH5(@NonNull String url, String title) {
        ActivityUtils.startActivity(BundleCreate.create(new H5Bundle(url, title)), H5Activity.class);
    }


    public <T extends BaseBundleData> T getBundleData() {
        Intent intent = getIntent();
        if (intent == null) {
            return null;
        }
        Object object = intent.getSerializableExtra(BaseBundleData.BUNDLE_NAME);
        if (object == null) {
            return null;
        }
        return (T) object;
    }

}
