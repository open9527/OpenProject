package com.android.custom.pkg;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.android.custom.pkg.bundle.CustomBundle;
import com.android.open9527.common.bundle.BundleUtils;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.LogUtils;

/**
 * @author open_9527
 * Create at 2021/5/26
 **/
public class LoginActivity extends BaseCommonActivity {

    private LoginViewModel mViewModel;
    private CustomAppViewModel mAppViewModel;
    private CustomBundle mBundle;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(LoginViewModel.class);
        mAppViewModel = getApplicationScopeViewModel(CustomAppViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.login_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        mViewModel.valueTitle.set(getTitle().toString());
        mBundle = BundleUtils.getBundleData(bundle, CustomBundle.class);

    }

    @Override
    public void initEvent() {
        mViewModel.valueLogin.observe(this, aBoolean -> {
            showLongToast("登陆成功!");
            mAppViewModel.setValueLogin(true);
            startLoginSuccessActivity();
        });
    }

    /**
     * 操作成功后 替换当前class
     */
    private void startLoginSuccessActivity() {
        if (mBundle!=null){
            Intent intent = getIntent();
            intent.setComponent(new ComponentName(mActivity, (Class<?>) intent.getExtras().get("class")));
            LogUtils.i(TAG, "title :" + mBundle.getTitle());
            startActivity(intent);
            finish();
        }

    }

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };

        public View.OnClickListener loginClick = v -> {
            mViewModel.valueLogin.setValue(true);
        };
    }
}
