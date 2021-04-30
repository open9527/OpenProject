package com.android.custom.pkg.webview.bridge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.common.action.HandlerAction;
import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.cell.CommonEmptyCell;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.WrapRecyclerView;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.blankj.utilcode.util.ScreenUtils;
import com.open9527.webview.NestedScrollBrowserView;
import com.open9527.webview.bridge.X5BridgeWebView;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

/**
 * @author open_9527
 * Create at 2021/4/30
 **/
public class RecycleViewWebActivity extends BaseCommonActivity implements HandlerAction {

    private BridgeViewModel mViewModel;
    private NestedScrollBrowserView mBrowserView;
    private View mHeaderView;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(BridgeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.recycel_view_web_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        initHeader();
    }

    private void initHeader() {
        WrapRecyclerView wrapRecyclerView = findViewById(R.id.recyclerView);
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.bridge_web_view_layout, wrapRecyclerView, false);
        wrapRecyclerView.addHeaderView(mHeaderView);

        mBrowserView = mHeaderView.findViewById(R.id.browser_view);
        mBrowserView.loadUrl(mViewModel.valueWebUrl.get());
        mBrowserView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                getHandler().post(runnable);
            }
        });


        X5BridgeWebView mBridgeWebView = mHeaderView.findViewById(R.id.web_view);
        mViewModel.registerJs(mBridgeWebView.getWebAgreementImpl(), mViewModel.valueBridgeBundleData.get());
        mBridgeWebView.loadUrl(mViewModel.valueWebUrl.get());

//        mViewModel.valueCellList.add(new CommonEmptyCell());
    }


    private void setHeaderHeight(int height) {
        if (height <= 0) return;
        RecyclerView.LayoutParams linearParams = (RecyclerView.LayoutParams) mHeaderView.getLayoutParams();
        linearParams.height = height;
        mHeaderView.setLayoutParams(linearParams);

        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
        mViewModel.valueCellList.add(new CommonEmptyCell());
    }

    private Runnable runnable = () -> {
        mBrowserView.measure(0, 0);
        //可能存在获取不到高度
        int measuredHeight = mBrowserView.getMeasuredHeight();
        if (measuredHeight == 0) {
            measuredHeight = ScreenUtils.getScreenHeight();
        }
        setHeaderHeight(measuredHeight);
    };

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };

        public IRefresh<Boolean> onRefreshListeners = new IRefresh<Boolean>() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout, Boolean isRefresh) {
                if (isRefresh) {
//                    mBridgeWebView.reload();
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeCallbacks(runnable);
    }
}
