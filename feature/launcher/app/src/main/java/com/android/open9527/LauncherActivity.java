package com.android.open9527;

import android.view.View;

import com.android.appmanager.pkg.AppManagerActivity;
import com.android.feature.permission.pkg.PermissionActivity;
import com.android.open9527.common.callback.SharedViewModel;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.image.pkg.load.ImageActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.pkg.OkHttpActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.open9527.wanandroid.pkg.main.WanAndroidActivity;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class LauncherActivity extends BaseCommonActivity {

    private LauncherViewMode mViewModel;
    private SharedViewModel mSharedViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(LauncherViewMode.class);
        mSharedViewModel = getApplicationScopeViewModel(SharedViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.launcher_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initEvent() {
//        mSharedViewModel.getStartImageModel().observeInActivity(this, s ->
//                GalleryActivity.start());
    }

    public static class ClickProxy {
        public View.OnClickListener onClickListener = view -> {
            if (view.getId() == R.id.btn_okhttp) {
                ActivityUtils.startActivity(OkHttpActivity.class);

            } else if (view.getId() == R.id.btn_permission) {

                ActivityUtils.startActivity(PermissionActivity.class);

            } else if (view.getId() == R.id.btn_wanandroid) {
                ActivityUtils.startActivity(WanAndroidActivity.class);

            } else if (view.getId() == R.id.btn_image) {
                ActivityUtils.startActivity(ImageActivity.class);

            } else if (view.getId() == R.id.btn_annotation) {
                ActivityUtils.startActivity(ContentViewActivity.class);


            } else if (view.getId() == R.id.btn_appmanager) {
                ActivityUtils.startActivity(AppManagerActivity.class);


            }
        };
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
