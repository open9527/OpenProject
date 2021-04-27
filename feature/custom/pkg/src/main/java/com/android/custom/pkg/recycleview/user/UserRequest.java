package com.android.custom.pkg.recycleview.user;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.open9527.common.net.data.BaseRequest;
import com.android.open9527.common.net.data.response.DataResult;
import com.android.open9527.okhttp.request.GetRequest;
import com.android.open9527.okhttp.request.PostRequest;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class UserRequest extends BaseRequest {

    private final MutableLiveData<DataResult<Object>> loginLiveData = new MutableLiveData<>();
    private final MutableLiveData<DataResult<List<ContentVo>>> collectListLiveData = new MutableLiveData<>();

    public LiveData<DataResult<Object>> getLoginLiveData() {
        return loginLiveData;
    }

    public void requestLoginApi(@NonNull String userName, @NonNull String passWord, @NonNull PostRequest request) {
        UserDataRepository.getInstance().login(userName, passWord, request, loginLiveData::setValue);
    }

    public LiveData<DataResult<List<ContentVo>>> getCollectListLiveData() {
        return collectListLiveData;
    }


    public void requestCollectListApi(int page, @NonNull GetRequest request) {
        UserDataRepository.getInstance().collectList(page, request, collectListLiveData::setValue);
    }

    public interface IUserRequest {
        default void requestLogin(@NonNull String userName, @NonNull String passWord, @NonNull PostRequest request) {
        }

        default LiveData<DataResult<Object>> getLoginLiveData() {
            return null;
        }


        default void requestCollectList(int page, @NonNull GetRequest request) {

        }

        default LiveData<DataResult<List<ContentVo>>> getCollectListLiveData() {
            return null;
        }

    }
}
