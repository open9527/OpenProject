package com.android.custom.pkg;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.custom.pkg.bundle.CustomBundle;
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
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.ActivityUtils;
import com.open9527.annotation.router.Router;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
@Router(path = "/custom/CustomActivity")
public class CustomActivity extends BaseCommonActivity {

    private CustomViewModel mViewModel;
    private CustomAppViewModel mAppViewModel;

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
