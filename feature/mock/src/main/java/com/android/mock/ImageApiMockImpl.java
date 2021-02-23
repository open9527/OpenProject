package com.android.mock;

import androidx.annotation.NonNull;

import com.android.open9527.common.callback.ICommonCallBack;
import com.android.open9527.image.export.api.ImageApi;
import com.android.open9527.image.export.api.ImageBundle;
import com.android.open9527.image.export.api.ImageResult;
import com.blankj.utilcode.util.ApiUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/

@ApiUtils.Api(isMock = true)
public class ImageApiMockImpl extends ImageApi {

    @Override
    public void startImage(@NonNull ImageBundle param) {
        ToastUtils.showShort("startImage");
    }

    @Override
    public ImageResult startImageForResult(@NonNull ImageBundle imageBundle) {
        LogUtils.i(TAG, imageBundle.toString());
        return null;
    }

    @Override
    public ICommonCallBack startImageCallBack(@NonNull ImageBundle imageBundle) {
        LogUtils.i(TAG, imageBundle.toString());
        return null;
    }
}
