package com.open9527.webview.bridge;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;


import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.open9527.webview.bridge.impl.vo.MessageVo;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 如果要自定义WebViewClient必须要集成此类
 */
public class X5BridgeWebViewClient extends WebViewClient {
    private static final String TAG = "X5BridgeWebViewClient";


    protected X5BridgeWebView mX5BridgeWebView;

    public X5BridgeWebViewClient(@NonNull X5BridgeWebView webView) {
        this.mX5BridgeWebView = webView;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
//        LogUtils.i(TAG, "shouldOverrideUrlLoading url: " + url);
        try {
            url = URLDecoder.decode(url, "UTF-8");
            LogUtils.i(TAG, "shouldOverrideUrlLoading decodeUrl: " + url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (url.startsWith(BridgeUtil.RMT_RETURN_DATA)) { // 如果是返回数据
            mX5BridgeWebView.handlerReturnData(url);

        } else if (url.startsWith(BridgeUtil.RMT_SCHEMA_QUEUE_MESSAGE)) {
            mX5BridgeWebView.flushMessageQueue();

        } else if (RegexUtils.isMatch(BridgeUtil.REGEX_HTTP_S, url)) {
            return super.shouldOverrideUrlLoading(webView, url);

        } else if (url.startsWith(BridgeUtil.RMT)) {
            getScheme(url);
            return true;

        }
        return true;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest webResourceRequest) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return shouldOverrideUrlLoading(view, webResourceRequest.getUrl().toString());
        } else {
            return super.shouldOverrideUrlLoading(view, webResourceRequest);
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mX5BridgeWebView.getStartupMessage() != null) {
            for (MessageVo messageVo : mX5BridgeWebView.getStartupMessage()) {
                mX5BridgeWebView.dispatchMessage(messageVo);
            }
            mX5BridgeWebView.setStartupMessage(null);
        }
    }


    private void getScheme(String scheme) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme));
            ActivityUtils.startActivity(intent);
        } catch (Exception e) {

        }

    }
}