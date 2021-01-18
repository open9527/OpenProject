package com.android.open9527.common.page;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.open9527.base.page.BaseActivity;
import com.android.open9527.common.net.data.response.manager.NetworkStateManager;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;

/**
 * @author open_9527
 * Create at 2021/1/7
 **/
public abstract class BaseCommonActivity extends BaseActivity implements ICommonPage {


    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mActivity = this;
        initStatusBar();
        super.onCreate(savedInstanceState);
        initView(getIntent().getExtras());
    }

    @Override
    public void initStatusBar() {
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        BarUtils.setStatusBarLightMode(this, true);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        getLifecycle().addObserver(NetworkStateManager.getInstance());
        initEvent();
        initRequest();
    }





    protected void showLongToast(String text) {
        ToastUtils.showLong(text);
    }

    protected void showShortToast(String text) {
        ToastUtils.showShort(text);
    }


    //    @Override
//    public Resources getResources() {
//        if (ScreenUtils.isPortrait()) {
//            return AdaptScreenUtils.adaptWidth(super.getResources(), 360);
//        } else {
//            return AdaptScreenUtils.adaptHeight(super.getResources(), 640);
//        }
//    }


    @Override
    protected void onDestroy() {
        mActivity = null;
        super.onDestroy();
    }
}
