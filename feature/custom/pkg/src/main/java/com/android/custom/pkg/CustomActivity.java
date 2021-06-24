package com.android.custom.pkg;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.android.custom.pkg.bundle.CustomBundle;
import com.android.custom.pkg.databinding.CustomActivityBinding;
import com.android.custom.pkg.dialog.DialogActivity;
import com.android.custom.pkg.layout.grid.GridLayoutActivity;
import com.android.custom.pkg.lottie.LottieActivity;
import com.android.custom.pkg.recycleview.RecycleViewActivity;
import com.android.custom.pkg.shadow.ShadowActivity;
import com.android.custom.pkg.video.VideoDetailsActivity;
import com.android.custom.pkg.webview.bridge.BridgeActivity;
import com.android.open9527.common.bundle.BundleUtils;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.filter.AppFilter;
import com.android.open9527.filter.FilterColor;
import com.android.open9527.filter.color.NightColor;
import com.android.open9527.okhttp.OkHttpUtils;
import com.android.open9527.okhttp.listener.OnDownloadListener;
import com.android.open9527.okhttp.model.HttpMethod;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.permission.OnPermissionCallback;
import com.android.open9527.permission.Permission;
import com.android.open9527.permission.PermissionsManage;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.annotation.router.Router;

import java.io.File;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
@Router(path = "/custom/CustomActivity")
public class CustomActivity extends BaseCommonActivity {

    private CustomViewModel mViewModel;
    private CustomAppViewModel mAppViewModel;

    private final int notifyId = 0x100001;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(CustomViewModel.class);
        mAppViewModel = getApplicationScopeViewModel(CustomAppViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.custom_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        mViewModel.valueIsLogin.set(false);

        CustomActivityBinding mBinding = (CustomActivityBinding) getBinding();
        mBinding.DrawTextPathView.setText("这是一段文字,用动画来播放");
        mBinding.DrawTextPathView.setAutoStart(true);
        mBinding.DrawTextPathView.setCycle(true);
        mBinding.DrawTextPathView.setDuration(10000);
        mBinding.DrawTextPathView.setTextColor(ColorUtils.getColor(R.color.common_text_color));
        mBinding.DrawTextPathView.setTextSize(30);

    }

    @Override
    public void initEvent() {
        mAppViewModel.getValueLogin().observe(this, aBoolean -> {
            mViewModel.valueIsLogin.set(true);
        });
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
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(mActivity, DialogActivity.class));
            startActivityByCheckLogin(intent, true);

        };

        public View.OnClickListener shadowClick = v -> {
            ActivityUtils.startActivity(ShadowActivity.class);
        };
        public View.OnClickListener recycleViewClick = v -> {
            ActivityUtils.startActivity(RecycleViewActivity.class);
        };

        public View.OnClickListener BridgeWebViewClick = v -> {
            ActivityUtils.startActivity(BridgeActivity.class);
        };

        public View.OnClickListener VideoPlayerViewClick = v -> {
            ActivityUtils.startActivity(VideoDetailsActivity.class);
        };

        public View.OnClickListener lottieViewClick = v -> {
            ActivityUtils.startActivity(LottieActivity.class);
        };

        public View.OnClickListener gridLayoutViewClick = v -> {
            ActivityUtils.startActivity(GridLayoutActivity.class);
        };

        public View.OnClickListener downLoadViewClick = v -> {
            PermissionsManage.with(mActivity)
                    .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                    .request(new OnPermissionCallback() {

                        @Override
                        public void onGranted(List<String> permissions, boolean all) {
                            if (all) {
                                ToastUtils.showShort("获取存储权限成功");
                                onDownloadRequest("");
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
        };


    }

    private NotificationManager notificationManager;
    //
    private void onDownloadRequest(String fileName) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String url = "https://dldir1.qq.com/weixin/android/weixin806android1900.apk";
//        String fileName = url.substring(url.lastIndexOf("-") + 1);
        if (TextUtils.isEmpty(fileName)) {
            fileName = TimeUtils.getNowString() + ".apk";
        }
        //系统相册目录
        String Pictures = Environment.getExternalStorageDirectory()
                //系统图片
//                + File.separator + Environment.DIRECTORY_PICTURES;
                //系统下载
                + File.separator + Environment.DIRECTORY_DOWNLOADS;
        //可创建文件夹
//                + File.separator + "Open9527" + File.separator;

        OkHttpUtils.download(this)
                .method(HttpMethod.GET)
                .file(new File(new File(Pictures), fileName))
                .url(url)
//                .md5("")//md5校验
                .listener(new OnDownloadListener() {

                    @Override
                    public void onStart(File file) {
//                        mProgressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onProgress(File file, int progress) {
//                        LogUtils.i(TAG, "progress-->" + progress);
                        mViewModel.valueDownLoad.set("下载进度:" + progress);
                        showNotification(notificationManager, progress);
                    }

                    @Override
                    public void onComplete(File file) {
                        ToastUtils.showShort("下载完成：" + file.getPath());
                        mViewModel.valueDownLoad.set("下载完成!");
                        notificationManager.cancel(notifyId);
                        AppUtils.installApp(file);
                    }

                    @Override
                    public void onError(File file, Exception e) {
                        ToastUtils.showShort("下载出错：" + e.getMessage());
                        mViewModel.valueDownLoad.set("下载出错!");
                    }

                    @Override
                    public void onEnd(File file) {
                        notificationManager.cancel(notifyId);
                    }

                }).start();
    }


    private void showNotification(NotificationManager notificationManager, int progress) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mActivity, String.valueOf(notifyId));
        builder.setContentTitle("xxxApp");
        builder.setContentText("正在下载中...");

        builder.setSmallIcon(R.drawable.icon_video_play);
        builder.setProgress(100, progress, false);

        builder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
        builder.setOnlyAlertOnce(true);

        Notification notification = builder.build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 创建
            NotificationChannel channel = new NotificationChannel(String.valueOf(notifyId), "通知", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.setShowBadge(true);
            channel.setDescription("这是一段来自app下载的描述");
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(notifyId, notification);
    }


    private void startActivityByCheckLogin(Intent intent, boolean checkLogin) {
        if (checkLogin && (!mViewModel.valueIsLogin.get())) {
            intent.putExtra("class", DialogActivity.class);
            intent.putExtras(BundleUtils.createBundleJson(new CustomBundle("这是一段描述")));
            intent.setComponent(new ComponentName(mActivity, LoginActivity.class));
        }
        super.startActivity(intent);
    }
}
