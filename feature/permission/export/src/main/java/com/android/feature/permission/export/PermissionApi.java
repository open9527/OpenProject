package com.android.feature.permission.export;

import androidx.annotation.NonNull;

import com.android.open9527.common.callback.ICommonCallBack;
import com.blankj.utilcode.util.ApiUtils;

/**
 * @author open_9527
 * Create at 2021/2/7
 **/
public abstract class PermissionApi extends ApiUtils.BaseApi {

    public abstract void startPermission(@NonNull PermissionBundle param);

    public abstract PermissionResult startPermissionForResult(@NonNull PermissionBundle param);


    public abstract ICommonCallBack startPermissionCallBack(@NonNull PermissionBundle param);


}
