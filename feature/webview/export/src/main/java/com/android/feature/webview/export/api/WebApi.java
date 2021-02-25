package com.android.feature.webview.export.api;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ApiUtils;

/**
 * @author open_9527
 * Create at 2021/2/25
 **/
public abstract class WebApi extends ApiUtils.BaseApi {

    protected String TAG = getClass().getName();

    public abstract void startWeb(@NonNull WebBundle webBundle);

}
