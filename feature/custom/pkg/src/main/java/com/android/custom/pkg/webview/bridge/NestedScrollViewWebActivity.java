package com.android.custom.pkg.webview.bridge;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.common.action.HandlerAction;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.open9527.webview.BrowserView;
import com.open9527.webview.bridge.X5BridgeWebView;
import com.open9527.webview.bridge.X5BridgeWebViewClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/4/30
 **/
public class NestedScrollViewWebActivity extends BaseCommonActivity implements HandlerAction {

    private BridgeViewModel mViewModel;

    private X5BridgeWebView x5BridgeWebView;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(BridgeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.nested_scroll_view_web_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());

    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        initNestedScrollView();
    }

    @Override
    public void initRequest() {
        fragmentList.add(BridgeContainerFragment.newInstance());
    }

    private void initNestedScrollView() {
        BrowserView mBrowserView = findViewById(R.id.browser_view);

        mBrowserView.setBrowserChromeClient(new BrowserView.BrowserChromeClient(mBrowserView));
        mBrowserView.setBrowserViewClient(new BrowserView.BrowserViewClient());

        mBrowserView.loadUrl("http://jyh.beta.easttone.com:8010/api/app/doc/release/rm_js_sdk_testcase.html");

        x5BridgeWebView = findViewById(R.id.bridge_web_view);


        x5BridgeWebView.setWebViewClient(new X5BridgeWebViewClient(x5BridgeWebView) {
            @Override
            public void onPageFinished(com.tencent.smtt.sdk.WebView webView, String s) {
                super.onPageFinished(webView, s);
            }
        });
        x5BridgeWebView.loadUrl(mViewModel.valueWebUrl.get());
        mViewModel.registerJs(x5BridgeWebView.getWebAgreementImpl(), mViewModel.valueBridgeBundleData.get());

        //最好是在WebView 加载完毕后 再显示
        getHandler().postDelayed(runnable, 5000);


    }

    private Runnable runnable = () -> FragmentUtils.add(getSupportFragmentManager(), fragmentList, R.id.fl_container, 0);



    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       removeCallbacks(runnable);
    }
}
