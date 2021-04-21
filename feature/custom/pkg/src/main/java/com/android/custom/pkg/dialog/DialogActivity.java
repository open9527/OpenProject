package com.android.custom.pkg.dialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.umeng.UmengShareDialog;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.dialog.DialogGravity;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.umeng.Platform;
import com.open9527.umeng.UmengClient;
import com.open9527.umeng.UmengLogin;
import com.open9527.umeng.UmengShare;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author open_9527
 * Create at 2021/4/21
 **/
public class DialogActivity extends BaseCommonActivity {

    private DialogViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(DialogViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.dialog_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
    }

    public class ClickProxy {

        public View.OnClickListener backClick = v -> {
            finish();
        };

        public View.OnClickListener shareClick = v -> {
            shareDialog();
        };

        public View.OnClickListener withViewClick = DialogActivity.this::shareWithViewDialog;

        public View.OnLongClickListener loginClick = v -> {
            login();
            return true;
        };

        public View.OnClickListener dateClick = v -> {
            dateDialog();
        };


    }

    private void shareDialog() {
        UmengShare.ShareData shareData = new UmengShare.ShareData(this);
        shareData.setShareTitle("分享标题");
        shareData.setShareDescription("分享描述");
        shareData.setShareLogo("https://www.wanandroid.com/resources/image/pc/logo.png");
        shareData.setShareUrl("https://www.wanandroid.com/index");
        UmengShareDialog.with(this)
                .setShareData(shareData)
                .setListener(new UmengShare.OnShareListener() {
                    @Override
                    public void onSucceed(Platform platform) {
                        LogUtils.i(TAG, "onSucceed: " + GsonUtils.toJson(platform));
                    }

                    @Override
                    public void onError(Platform platform, Throwable t) {
                        LogUtils.i(TAG, "onError: " + GsonUtils.toJson(platform) + "  Throwable: " + t.getMessage());
                    }

                    @Override
                    public void onCancel(Platform platform) {
                        LogUtils.i(TAG, "onCancel: " + GsonUtils.toJson(platform));
                    }
                }).show();
    }

    @SuppressLint("SimpleDateFormat")
    private void dateDialog() {
        DateDialog.with(this)
//                .setStartYear(1900)
//                .setEndYear(2021)
//                .setYear(2021)
//                .setMonth(4)
//                .setDay(21)
                .setListener((year, month, day) -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year);
                    // 月份从零开始，所以需要减 1
                    calendar.set(Calendar.MONTH, month - 1);
                    calendar.set(Calendar.DAY_OF_MONTH, day);
//                        showLongToast("时间戳：" + calendar.getTimeInMillis());
                    showLongToast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
//                        showLongToast(String.valueOf(year + "-" + month + "-" + day));
                })
                .showDateDialog();

    }

    private void shareWithViewDialog(View view) {
        WithViewDialog.with(this)
                .setDialogGravity(DialogGravity.LEFT_CENTER)
                .showCommentReportDialog(view);
    }


    private void login() {
        UmengClient.login(this, Platform.QQ, new UmengLogin.OnLoginListener() {
            @Override
            public void onSucceed(Platform platform, UmengLogin.LoginData data) {
                LogUtils.i(TAG, "onSucceed: " + GsonUtils.toJson(data));
            }

            @Override
            public void onError(Platform platform, Throwable t) {
                LogUtils.i(TAG, "onError: " + GsonUtils.toJson(platform) + "  Throwable: " + t.getMessage());
            }

            @Override
            public void onCancel(Platform platform) {
                LogUtils.i(TAG, "onCancel: " + GsonUtils.toJson(platform));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 友盟登录回调
        UmengClient.onActivityResult(this, requestCode, resultCode, data);
    }

    private void dialog(View view) {
//        new GenjiDialog().showOnView(getSupportFragmentManager(), view, DialogGravity.LEFT_BOTTOM);
    }
}
