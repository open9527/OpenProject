package com.android.open9527.image.pkg.impl;

import androidx.annotation.NonNull;

import com.android.open9527.common.callback.ICommonCallBack;
import com.android.open9527.image.export.api.ImageApi;
import com.android.open9527.image.export.api.ImageBundle;
import com.android.open9527.image.export.api.ImageResult;
import com.android.open9527.image.pkg.preview.PreviewActivity;
import com.blankj.utilcode.util.ApiUtils;
import com.blankj.utilcode.util.LogUtils;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/

@ApiUtils.Api
public class ImageApiImpl extends ImageApi {

    @Override
    public void startImage(@NonNull ImageBundle imageBundle) {
        LogUtils.i(TAG, imageBundle.toString());
        PreviewActivity.start(0, Collections.singletonList(imageBundle.getName()));
    }

    @Override
    public ImageResult startImageForResult(@NonNull ImageBundle param) {
        return null;
    }

    @Override
    public ICommonCallBack startImageCallBack(@NonNull ImageBundle param) {
        return null;
    }
}
