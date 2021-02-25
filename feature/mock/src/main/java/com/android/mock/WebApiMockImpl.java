package com.android.mock;

import androidx.annotation.NonNull;

import com.android.feature.webview.export.api.WebApi;
import com.android.feature.webview.export.api.WebBundle;
import com.blankj.utilcode.util.ApiUtils;
import com.blankj.utilcode.util.ToastUtils;

/**
 * @author open_9527
 * Create at 2021/2/25
 **/
@ApiUtils.Api(isMock = true)
public class WebApiMockImpl extends WebApi {
    @Override
    public void startWeb(@NonNull WebBundle webBundle) {
        ToastUtils.showShort("startWeb");
    }
}
