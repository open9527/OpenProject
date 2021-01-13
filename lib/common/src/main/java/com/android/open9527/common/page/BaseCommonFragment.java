package com.android.open9527.common.page;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.open9527.base.page.BaseFragment;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

/**
 * @author open_9527
 * Create at 2021/1/11
 **/
public abstract class BaseCommonFragment extends BaseFragment implements ICommonPage {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    // 是否第一次加载
    private boolean isFirstLoad = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(TAG, "onCreate");
        FragmentManager fm = getFragmentManager();
        if (fm == null) return;
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = fm.beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(getArguments());
        initEvent();
        LogUtils.i(TAG, "onViewCreated");
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        LogUtils.i(TAG, "initView");
    }

//    @Override
//    protected void loadInitData() {
//        super.loadInitData();
//        LogUtils.i(TAG, "loadInitData");
//    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            initRequest();
            isFirstLoad = false;
        }
    }

    @Override
    public void initRequest() {
        LogUtils.i(TAG, "initRequest");
    }

    @Override
    public void initEvent() {
        LogUtils.i(TAG, "initEvent");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }


    protected void showLongToast(String text) {
        ToastUtils.showLong(text);
    }

    protected void showShortToast(String text) {
        ToastUtils.showShort(text);
    }
}
