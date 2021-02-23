package com.android.open9527.image.export.api;

import androidx.annotation.NonNull;

import com.android.open9527.common.callback.ICommonCallBack;
import com.blankj.utilcode.util.ApiUtils;

/**
 * @author open_9527
 * Create at 2021/2/22
 **/
public abstract class ImageApi extends ApiUtils.BaseApi {
    protected String TAG = getClass().getName();


    public abstract void startImage(@NonNull ImageBundle param);

    public abstract ImageResult startImageForResult(@NonNull ImageBundle param);


    public abstract ICommonCallBack startImageCallBack(@NonNull ImageBundle param);


}
