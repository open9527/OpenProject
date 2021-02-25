package com.android.feature.webview.pkg.impl;

import androidx.annotation.NonNull;

import com.android.feature.webview.export.api.WebApi;
import com.android.feature.webview.export.api.WebBundle;
import com.android.feature.webview.pkg.WebViewActivity;
import com.blankj.utilcode.util.ApiUtils;

/**
 * @author open_9527
 * Create at 2021/2/25
 **/
@ApiUtils.Api
public class WebApiImpl extends WebApi {
    @Override
    public void startWeb(@NonNull WebBundle webBundle) {
        WebViewActivity.start(webBundle.getUrl(), webBundle.getTitle());
    }
}
