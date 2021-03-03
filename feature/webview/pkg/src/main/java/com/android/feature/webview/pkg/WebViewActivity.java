package com.android.feature.webview.pkg;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.open9527.common.action.HandlerAction;
import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.bundle.BaseBundleData;
import com.android.open9527.common.bundle.BundleUtils;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.titlebar.OnTitleBarListener;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.webview.BrowserView;
import com.scwang.smart.refresh.layout.api.RefreshLayout;


/**
 * @author open_9527
 * Create at 2021/1/6
 **/
public class WebViewActivity extends BaseCommonActivity implements HandlerAction {

    private WebViewViewModel mViewModel;
    private BrowserView browserView;
    private ProgressBar progressBar;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(WebViewViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.webview_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.titleBarListener, onTitleBarListener);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        LogUtils.i(TAG, "initView");
        super.initView(bundle);
        browserView = findViewById(R.id.browser_view);
        progressBar = findViewById(R.id.pb_web_progress);

        browserView.setLifecycleOwner(this);
        browserView.setBrowserViewClient(new MyBrowserViewClient());
        browserView.setBrowserChromeClient(new MyBrowserChromeClient(browserView));


        BundleData bundleData = BundleUtils.getBundleData(bundle);
        if (bundleData != null) {
            mViewModel.valueTitle.set(bundleData.getTitle());
            browserView.loadUrl(bundleData.getUrl());
        } else {
            browserView.loadUrl(mViewModel.valueUrl.get());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && browserView.canGoBack()) {
            // 后退网页并且拦截该事件
            browserView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        removeCallbacks();
        super.onDestroy();
    }

    @Override
    public void initRequest() {
        LogUtils.i(TAG, "initRequest");
    }

    @Override
    public void initEvent() {
        LogUtils.i(TAG, "initEvent");
    }


    public class ClickProxy {
        public IRefresh<Boolean> onRefreshListeners = new IRefresh<Boolean>() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout, Boolean isRefresh) {
                if (isRefresh) browserView.reload();
            }
        };
    }


    public OnTitleBarListener onTitleBarListener = new OnTitleBarListener() {
        @Override
        public void onLeftClick(View v) {
            finish();
        }

        @Override
        public void onRightClick(View v) {
            BrowserActivity.start();
            ToastUtils.showShort("onRightClick");
        }
    };


    private class MyBrowserViewClient extends BrowserView.BrowserViewClient {

        /**
         * 网页加载错误时回调，这个方法会在 onPageFinished 之前调用
         */
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            // 这里为什么要用延迟呢？因为加载出错之后会先调用 onReceivedError 再调用 onPageFinished

            //可以加载一个错误重试布局 view.reload();
//            post(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            });
        }

        /**
         * 开始加载网页
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mViewModel.valueWebPbVisibility.set(View.VISIBLE);

        }

        /**
         * 完成加载网页
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            mViewModel.valueWebPbVisibility.set(View.GONE);
            mViewModel.valueCloseHeaderOrFooter.set(true);
        }
    }

    private class MyBrowserChromeClient extends BrowserView.BrowserChromeClient {

        private MyBrowserChromeClient(BrowserView view) {
            super(view);
        }

        /**
         * 收到网页标题
         */
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (title != null) {
//                mViewModel.valueTitle.set(title);
//                setTitle(title);
            }
        }

        /**
         * 收到加载进度变化
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar.setProgress(newProgress);
        }
    }


    public static class BundleData extends BaseBundleData {
        private String url;
        private String title;

        public BundleData(String url, String title) {
            this.url = url;
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static void start(@NonNull String url, @NonNull String title) {
        ActivityUtils.startActivity(BundleUtils.create(new BundleData(url, title)), WebViewActivity.class);
    }


}
