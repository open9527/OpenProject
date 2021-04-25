package com.android.open9527;

import android.os.Bundle;
import android.view.View;

import com.android.feature.webview.pkg.WebViewActivity;
import com.android.open9527.common.bundle.BundleUtils;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.router.Path;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.router.Router;
import com.open9527.router.callback.NavigationCallback;
import com.open9527.router.info.Postcard;

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
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initEvent() {

    }

    public class ClickProxy {
        public View.OnClickListener onClickListener = view -> {
            if (view.getId() == R.id.btn_okhttp) {
                Router.getsInstance()
                        .build(Path.PATH_OKHTTP_OKHTTPACTIVITY)
                        .navigation(LauncherActivity.this, new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                LogUtils.i(TAG, "NavigationCallback" + "找到跳转页面");

                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                LogUtils.i(TAG, "NavigationCallback" + "未找到");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                LogUtils.i(TAG, "NavigationCallback" + "成功跳转");
                            }
                        });

            } else if (view.getId() == R.id.btn_permission) {
                Router.getsInstance()
                        .build(Path.PATH_PERMISSION_PERMISSIONACTIVITY)
                        .navigation(LauncherActivity.this);

            } else if (view.getId() == R.id.btn_wanandroid) {
                Router.getsInstance()
                        .build(Path.PATH_WANANDROID_WANANDROIDACTIVITY)
                        .navigation(LauncherActivity.this);

            } else if (view.getId() == R.id.btn_image) {
                Router.getsInstance()
                        .build(Path.PATH_IMAGE_IMAGEACTIVITY)
                        .navigation(LauncherActivity.this);

            } else if (view.getId() == R.id.btn_annotation) {
                Router.getsInstance().build(Path.PATH_LAUNCHER_CONTENTVIEWACTIVITY)
                        .navigation(LauncherActivity.this);


            } else if (view.getId() == R.id.btn_appmanager) {
                Router.getsInstance().build(Path.PATH_APPMANAGER_APPMANAGERACTIVITY)
                        .navigation(LauncherActivity.this);

            } else if (view.getId() == R.id.btn_webview) {
                Bundle bundle = BundleUtils.create(new WebViewActivity.BundleData("https://www.wanandroid.com/index", "wanandroid"));
                Router.getsInstance().build(Path.PATH_WEBVIEW_WEBVIEWACTIVITY)
                        .withBundle(bundle)
                        .navigation(LauncherActivity.this);

            } else if (view.getId() == R.id.btn_custom) {
                Router.getsInstance()
                        .build(Path.PATH_CUSTOM_CUSTOMACTIVITY)
                        .navigation(LauncherActivity.this, new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                LogUtils.i(TAG, "NavigationCallback" + "找到跳转页面");

                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                LogUtils.i(TAG, "NavigationCallback" + "未找到");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                LogUtils.i(TAG, "NavigationCallback" + "成功跳转");
                            }
                        });


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
