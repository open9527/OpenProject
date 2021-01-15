package com.open9527.wanandroid.pkg.net.user;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.open9527.common.net.data.BaseRequest;
import com.android.open9527.common.net.data.response.DataResult;
import com.android.open9527.okhttp.request.PostRequest;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class UserRequest extends BaseRequest {

    private final MutableLiveData<DataResult<Object>> loginLiveData = new MutableLiveData<>();

    public LiveData<DataResult<Object>> getLoginLiveData() {

        return loginLiveData;
    }

    public void requestLoginApi(@NonNull String userName, @NonNull String passWord, @NonNull PostRequest request) {
        UserDataRepository.getInstance().login(userName, passWord, request, loginLiveData::postValue);
    }
}
