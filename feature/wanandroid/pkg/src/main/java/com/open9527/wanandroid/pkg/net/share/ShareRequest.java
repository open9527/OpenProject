package com.open9527.wanandroid.pkg.net.share;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.open9527.common.net.data.BaseRequest;
import com.android.open9527.common.net.data.response.DataResult;
import com.android.open9527.okhttp.request.GetRequest;
import com.open9527.wanandroid.pkg.net.DataVo;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class ShareRequest extends BaseRequest {

    private final MutableLiveData<DataResult<DataVo>> shareLiveData = new MutableLiveData<>();

    public LiveData<DataResult<DataVo>> getShareLiveData() {

        return shareLiveData;
    }

    public void requestShare(@NonNull int page, @NonNull GetRequest request) {
        ShareDataRepository.getInstance().share(page, request, shareLiveData::postValue);
    }
}
