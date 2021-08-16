package com.android.custom.pkg.webview.bridge;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.webview.bridge.custom.CustomWebViewActivity;
import com.android.open9527.common.bundle.BundleUtils;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.ActivityUtils;


/**
 * @author open_9527
 * Create at 2021/4/28
 **/
public class BridgeActivity extends BaseCommonActivity {

    private BridgeViewModel mViewModel;


    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(BridgeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.bridge_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        BridgeBundleData bundleData = BundleUtils.getBundleData(bundle);
        super.initView(bundle);
        mViewModel.valueBridgeBundleData.set(bundleData);
//       TextView textView= findViewById(R.id.tv_viewPager2);
//        textView.setTextSize(SizeUtils.dp2px(16));
    }

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };
        public View.OnClickListener ViewPager2Click = v -> {
            ActivityUtils.startActivity(ViewPage2WebActivity.class);
        };
        public View.OnClickListener nestedScrollViewClick = v -> {
            ActivityUtils.startActivity(NestedScrollViewWebActivity.class);
        };
        public View.OnClickListener recycleViewViewClick = v -> {
            ActivityUtils.startActivity(RecycleViewWebActivity.class);
        };

        public View.OnClickListener webViewClick = v -> {
            ActivityUtils.startActivity(CustomWebViewActivity.class);
        };
    }
}
