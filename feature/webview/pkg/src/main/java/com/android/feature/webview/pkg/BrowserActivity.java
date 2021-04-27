package com.android.feature.webview.pkg;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.feature.webview.pkg.browser.bridge.BridgeBrowserView;
import com.android.feature.webview.pkg.browser.bridge.BridgeWebChromeClient;
import com.android.feature.webview.pkg.browser.bridge.BridgeWebViewClient;
import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.okhttp.request.GetRequest;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.android.open9527.recycleview.decoration.SpacesItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentLinearLayoutManager;
import com.android.open9527.titlebar.OnTitleBarListener;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

/**
 * @author open_9527
 * Create at 2021/3/1
 **/
public class BrowserActivity extends BaseCommonActivity {
    private int mPage = 0;

    private BrowserViewModel mViewModel;
    private BridgeBrowserView browserView;
    private ProgressBar progressBar;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(BrowserViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.browser_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.layoutManager, new WrapContentLinearLayoutManager(this))
                .addBindingParam
                        (
                                BR.itemDecoration,
                                new SpacesItemDecoration(this).setParam(R.color.common_line_color, 10)
                        )
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<BaseBindingCell>())
                .addBindingParam(BR.titleBarListener, onTitleBarListener);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        initBrowserView();
        progressBar = findViewById(R.id.pb_web_progress);
    }

    private void initBrowserView() {
        browserView = findViewById(R.id.browser_view);
        browserView.setLifecycleOwner(this);
        browserView.setBrowserViewClient(new MyBrowserViewClient(browserView));
        browserView.setBrowserChromeClient(new MyBrowserChromeClient(browserView));

        //        browserView.loadUrl(mViewModel.valueUrl.get());
        browserView.loadUrl("https://www.wanandroid.com/index");
    }

    @Override
    public void initRequest() {
//        requestArticle();
    }

    @Override
    public void initEvent() {
        mViewModel.articleRequest.getArticleLiveData().observe(this, dataVoDataResult -> {
            mViewModel.onCreateCells(mPage, dataVoDataResult.getResult().getDataList());
        });

    }

    private void requestArticle() {
        mViewModel.articleRequest.requestArticle(mPage, new GetRequest(this));
    }


    public class ClickProxy {
        public IRefresh<Boolean> onRefreshListeners = new IRefresh<Boolean>() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout, Boolean isRefresh) {
                mPage = isRefresh ? 0 : ++mPage;
                requestArticle();
//                if (isRefresh) browserView.reload();
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
            ToastUtils.showShort("onRightClick");
        }
    };


    private class MyBrowserViewClient extends BridgeWebViewClient {

        public MyBrowserViewClient(@NonNull BridgeBrowserView browserView) {
            super(browserView);
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
            requestArticle();

        }
    }


    private class MyBrowserChromeClient extends BridgeWebChromeClient {

        private MyBrowserChromeClient(BridgeBrowserView view) {
            super(view);
        }

        /**
         * 收到网页标题
         */
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (title != null) {
                LogUtils.i(TAG, "title:" + title);
                mViewModel.valueTitle.set(title);
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && browserView.canGoBack()) {
            // 后退网页并且拦截该事件
            browserView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void start() {
        ActivityUtils.startActivity(BrowserActivity.class);
    }
}
