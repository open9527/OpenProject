package com.android.feature.permission.pkg;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.permission.OnPermissionCallback;
import com.android.open9527.permission.Permission;
import com.android.open9527.permission.PermissionsManage;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/6
 **/
public class PermissionActivity extends BaseCommonActivity {

    private PermissionViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(PermissionViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.permission_activity, BR.vm, mViewModel);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        LogUtils.i(TAG, "initView");
        super.initView(bundle);
        findViewById(R.id.btn_main_request_1).setOnClickListener(clickListener);
        findViewById(R.id.btn_main_request_2).setOnClickListener(clickListener);
        findViewById(R.id.btn_main_request_3).setOnClickListener(clickListener);
        findViewById(R.id.btn_main_request_4).setOnClickListener(clickListener);
        findViewById(R.id.btn_main_request_5).setOnClickListener(clickListener);
        findViewById(R.id.btn_main_request_6).setOnClickListener(clickListener);
        findViewById(R.id.btn_main_request_7).setOnClickListener(clickListener);
        findViewById(R.id.btn_main_request_8).setOnClickListener(clickListener);
        findViewById(R.id.btn_main_app_details).setOnClickListener(clickListener);
    }


    @Override
    public void initRequest() {
        LogUtils.i(TAG, "initRequest");
    }

    @Override
    public void initEvent() {
        LogUtils.i(TAG, "initEvent");
    }


    public View.OnClickListener clickListener = view -> {
        if (view.getId() == R.id.btn_main_request_1) {
            requestCamera();

        } else if (view.getId() == R.id.btn_main_request_2) {
            requestAudio();

        } else if (view.getId() == R.id.btn_main_request_3) {
            requestLocation();

        } else if (view.getId() == R.id.btn_main_request_4) {
            requestStorage(view);

        } else if (view.getId() == R.id.btn_main_request_5) {
            requestInstallPackages();

        } else if (view.getId() == R.id.btn_main_request_6) {
            requestSystemAlertWindow();

        } else if (view.getId() == R.id.btn_main_request_7) {
            requestNotificationService();

        } else if (view.getId() == R.id.btn_main_request_8) {
            requestWriteSettings();

        } else if (view.getId() == R.id.btn_main_app_details) {
            PermissionsManage.startApplicationDetails(this);

        }
    };


    private void requestCamera() {
        PermissionsManage.with(this)
                .permission(Permission.CAMERA)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(@NonNull List<String> permissions, boolean all) {
                        if (all) {
                            ToastUtils.showShort("获取拍照权限成功");
                        }
                    }

                    @Override
                    public void onDenied(@NonNull List<String> permissions, boolean never) {
                        if (never) {
                            ToastUtils.showShort("被永久拒绝授权，请手动授予拍照权限");
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            PermissionsManage.startPermissionActivity(mActivity, permissions);
                        } else {
                            ToastUtils.showShort("获取拍照权限失败");
                        }
                    }
                });
    }

    private void requestAudio() {
        PermissionsManage.with(this)
                .permission(Permission.RECORD_AUDIO)
                .permission(Permission.Group.CALENDAR)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            ToastUtils.showShort("获取录音和日历权限成功");
                        } else {
                            ToastUtils.showShort("获取部分权限成功，但是部分权限未正常授予");
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            ToastUtils.showShort("被永久拒绝授权，请手动授予录音和日历权限");
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            PermissionsManage.startPermissionActivity(mActivity, permissions);
                        } else {
                            ToastUtils.showShort("获取权限失败");
                        }
                    }
                });

    }

    private void requestLocation() {
        PermissionsManage.with(this)
                .permission(Permission.Group.LOCATION)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            ToastUtils.showShort("获取定位权限成功");
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            ToastUtils.showShort("被永久拒绝授权，请手动授予定位权限");
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            PermissionsManage.startPermissionActivity(mActivity, permissions);
                            return;
                        }

                        if (permissions.size() == 1 && Permission.ACCESS_BACKGROUND_LOCATION.equals(permissions.get(0))) {
                            ToastUtils.showShort("没有授予后台定位权限，请您选择\"始终允许\"");
                        } else {
                            ToastUtils.showShort("获取定位权限失败");
                        }
                    }
                });
    }

    private void requestStorage(View view) {

        long delayMillis = 0;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            delayMillis = 2000;
            ToastUtils.showShort("当前版本不是 Android 11 以上，会自动变更为旧版的请求方式");
        }

        view.postDelayed(new Runnable() {

            @Override
            public void run() {
                PermissionsManage.with(mActivity)
                        // 不适配 Android 11 可以这样写
                        //.permission(Permission.Group.STORAGE)
                        // 适配 Android 11 需要这样写，这里无需再写 Permission.Group.STORAGE
                        .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                        .request(new OnPermissionCallback() {

                            @Override
                            public void onGranted(List<String> permissions, boolean all) {
                                if (all) {
                                    ToastUtils.showShort("获取存储权限成功");
                                }
                            }

                            @Override
                            public void onDenied(List<String> permissions, boolean never) {
                                if (never) {
                                    ToastUtils.showShort("被永久拒绝授权，请手动授予存储权限");
                                    // 如果是被永久拒绝就跳转到应用权限系统设置页面
                                    PermissionsManage.startPermissionActivity(mActivity, permissions);
                                } else {
                                    ToastUtils.showShort("获取存储权限失败");
                                }
                            }
                        });
            }
        }, delayMillis);

    }

    private void requestInstallPackages() {
        PermissionsManage.with(this)
                .permission(Permission.REQUEST_INSTALL_PACKAGES)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        ToastUtils.showShort("获取安装包权限成功");
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        ToastUtils.showShort("获取安装包权限失败，请手动授予权限");
                    }
                });

    }

    private void requestSystemAlertWindow() {
        PermissionsManage.with(this)
                .permission(Permission.SYSTEM_ALERT_WINDOW)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        ToastUtils.showShort("获取悬浮窗权限成功");
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        ToastUtils.showShort("获取悬浮窗权限失败，请手动授予权限");
                    }
                });

    }

    private void requestNotificationService() {

        PermissionsManage.with(this)
                .permission(Permission.NOTIFICATION_SERVICE)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        ToastUtils.showShort("获取通知栏权限成功");
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        ToastUtils.showShort("获取通知栏权限失败，请手动授予权限");
                    }
                });
    }

    private void requestWriteSettings() {
        PermissionsManage.with(this)
                .permission(Permission.WRITE_SETTINGS)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        ToastUtils.showShort("获取系统设置权限成功");
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        ToastUtils.showShort("获取系统设置权限失败，请手动授予权限");
                    }
                });

    }


}
