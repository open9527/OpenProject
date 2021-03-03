package com.android.feature.webview.pkg.browser.bridge;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.annotation.NonNull;

/**
 * @author open_9527
 * Create at 2021/3/2
 **/
public class BridgeWebChromeClient extends WebChromeClient {

    private static final String TAG = "BridgeWebChromeClient";

    private BridgeBrowserView mBrowserView;

    public BridgeWebChromeClient(@NonNull BridgeBrowserView browserView) {
        mBrowserView = browserView;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);

    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);

    }
}
