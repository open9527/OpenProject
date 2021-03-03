package com.android.feature.webview.pkg.browser.bridge;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.EncodeUtils;

/**
 * @author open_9527
 * Create at 2021/3/2
 **/
public class BridgeWebViewClient extends WebViewClient {

    private static final String TAG = "BridgeWebViewClient";

    private BridgeBrowserView mBrowserView;

    public BridgeWebViewClient(@NonNull BridgeBrowserView browserView) {
        mBrowserView = browserView;
    }


    /**
     * 同名 API 兼容
     */
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (request.isForMainFrame()) {
            onReceivedError(view,
                    error.getErrorCode(), error.getDescription().toString(),
                    request.getUrl().toString());
        }
    }

    /**
     * 加载错误
     */
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
    }


    /**
     * 同名 API 兼容
     */
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return shouldOverrideUrlLoading(view, request.getUrl().toString());
    }

    /**
     * 跳转到其他链接
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        String scheme = Uri.parse(url).getScheme();
        if (scheme == null) {
            return true;
        }
        // 如果是返回数据
        if (url.startsWith(BridgeUtil.RMT_RETURN_DATA)) {
            mBrowserView.handlerReturnData(EncodeUtils.urlDecode(url));

        } else if (url.startsWith(BridgeUtil.RMT_SCHEMA_QUEUE_MESSAGE)) {
            mBrowserView.flushMessageQueue(view);

        } else if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
            view.loadUrl(url);
        }
        // 已经处理该链接请求
        return true;
    }

}
