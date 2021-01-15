package com.android.open9527;

import android.view.View;

import com.android.feature.permission.pkg.PermissionActivity;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.pkg.OkHttpActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.open9527.wanandroid.pkg.main.WanAndroidActivity;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class LauncherActivity extends BaseCommonActivity {

    private LauncherViewMode mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(LauncherViewMode.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.launcher_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                ;
    }

    public static class ClickProxy {
        public View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_okhttp) {
                    ActivityUtils.startActivity(OkHttpActivity.class);

                } else if (view.getId() == R.id.btn_permission) {
                    ActivityUtils.startActivity(PermissionActivity.class);

                } else if (view.getId() == R.id.btn_wanandroid) {
                    ActivityUtils.startActivity(WanAndroidActivity.class);

                }
            }
        };
    }

}
