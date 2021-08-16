package com.android.custom.pkg.webview.bridge.custom;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.webview.bridge.custom.vo.WebBaseVo;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;


/**
 * @author open_9527
 * Create at 2021/8/9
 **/
public class CustomWebViewActivity extends BaseCommonActivity {

    private WebViewViewModel mViewModel;
    private CustomWebView mWebView;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(WebViewViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.custom_webview_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        mWebView = findViewById(R.id.custom_web_view);
        mWebView.setWebViewClient(new CustomWebViewClient(mWebView));
        mWebView.loadUrl("http://jyh.beta.easttone.com:8010/api/app/doc/release/rm_js_sdk_testcase.html");

        mWebView.registerHandler(WebBaseVo.HANDLER_NAME_READY, (data, function) -> {
            LogUtils.i(TAG, "registerHandler ready: " + data);
        });

        mWebView.registerHandler(WebBaseVo.HANDLER_NAME_UPDATE_WEB_VIEW_HEIGHT, (data, function) -> {
            LogUtils.i(TAG, "registerHandler updateWebViewHeight: " +data);
        });

    }


    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            onBackPressed();
        };
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }
}
