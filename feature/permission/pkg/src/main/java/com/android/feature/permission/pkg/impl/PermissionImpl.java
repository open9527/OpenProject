package com.android.feature.permission.pkg.impl;

import androidx.annotation.NonNull;

import com.android.feature.permission.export.PermissionApi;
import com.android.feature.permission.export.PermissionBundle;
import com.android.feature.permission.export.PermissionResult;
import com.android.feature.permission.pkg.PermissionActivity;
import com.android.open9527.common.callback.ICommonCallBack;
import com.blankj.utilcode.util.ApiUtils;
import com.blankj.utilcode.util.LogUtils;

/**
 * @author open_9527
 * Create at 2021/2/7
 **/

@ApiUtils.Api
public class PermissionImpl extends PermissionApi {
    @Override
    public void startPermission(@NonNull PermissionBundle param) {
        PermissionActivity.start(param);
    }

    @Override
    public PermissionResult startPermissionForResult(@NonNull PermissionBundle param) {
        PermissionActivity.start(param);
        return new PermissionResult("9527", "2333");
    }

    @Override
    public ICommonCallBack startPermissionCallBack(@NonNull PermissionBundle param) {
        PermissionActivity.start(param);
        return new ICommonCallBack() {
            @Override
            public void onCallBack() {
                LogUtils.i("PermissionImpl", "onCallBack");
            }
        };
    }
}
