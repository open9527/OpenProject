package com.android.custom.pkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.custom.pkg.umeng.UmengShareDialog;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.umeng.Platform;
import com.open9527.umeng.UmengClient;
import com.open9527.umeng.UmengLogin;
import com.open9527.umeng.UmengShare;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class CustomActivity extends BaseCommonActivity {

    private CustomViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(CustomViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.custom_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, onClickListener);
    }


    public View.OnClickListener onClickListener = v -> {
        if (v.getId() == R.id.tv_shadow_3) {
            share();
        } else {
            login();
        }

    };

    private void share() {
        UmengShareDialog.with(this)
                .setShareTitle("分享标题")
                .setShareDescription("分享描述")
                .setShareLogo("https://www.wanandroid.com/resources/image/pc/logo.png")
                .setShareUrl("https://www.wanandroid.com/index")
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
}
