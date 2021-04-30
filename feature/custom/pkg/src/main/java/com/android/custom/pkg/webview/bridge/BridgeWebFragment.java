package com.android.custom.pkg.webview.bridge;

import android.os.Bundle;
import android.webkit.WebSettings;

import androidx.annotation.Nullable;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.common.page.BaseCommonFragment;
import com.android.open9527.page.DataBindingConfig;
import com.open9527.webview.ViewPager2BrowserView;
import com.open9527.webview.bridge.X5BridgeWebView;

/**
 * @author open_9527
 * Create at 2021/4/29
 **/
public class BridgeWebFragment extends BaseCommonFragment {

    private BridgeViewModel mViewModel;

    public static BridgeWebFragment newInstance() {
        return new BridgeWebFragment();
    }


    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(BridgeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.bridge_web_fragment, BR.vm, mViewModel);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        initWebView();
        initBrowserView();
    }

    private void initBrowserView() {
        ViewPager2BrowserView mBrowserView = getView().findViewById(R.id.browser_view);
        mBrowserView.loadUrl(mViewModel.valueWebUrl.get());
    }

    private void initWebView() {
        X5BridgeWebView x5BridgeWebView = getView().findViewById(R.id.web_view);

        mViewModel.registerJs(x5BridgeWebView.getWebAgreementImpl(), mViewModel.valueBridgeBundleData.get());
        x5BridgeWebView.loadUrl(mViewModel.valueWebUrl.get());
    }
}
