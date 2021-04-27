package com.android.custom.pkg.recycleview;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.custom.pkg.recycleview.user.ContentVo;
import com.android.custom.pkg.recycleview.user.UserRequest;
import com.android.open9527.common.net.data.response.DataResult;
import com.android.open9527.okhttp.request.GetRequest;
import com.android.open9527.okhttp.request.PostRequest;
import com.android.open9527.recycleview.adapter.BaseBindingCell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/4/25
 **/
public class RecycleViewViewModel extends ViewModel implements UserRequest.IUserRequest {

    public final ObservableField<String> valueTitle = new ObservableField<>("RecycleView");

    public final ObservableBoolean valueNoMoreData = new ObservableBoolean(false);

    public final ObservableBoolean valueNotify = new ObservableBoolean(true);

    public final MutableLiveData<List<BaseBindingCell>> valueCellList = new MutableLiveData<>(new ArrayList<>());

    public final MutableLiveData<List<ContentVo>> valueList = new MutableLiveData<>(new ArrayList<>());
    public final MutableLiveData<Boolean> valueRefresh = new MutableLiveData<>(true);




    private UserRequest userRequest = new UserRequest();

    @Override
    public void requestLogin(@NonNull String userName, @NonNull String passWord, @NonNull PostRequest request) {
        userRequest.requestLoginApi(userName, passWord, request);
    }

    @Override
    public LiveData<DataResult<Object>> getLoginLiveData() {
        return userRequest.getLoginLiveData();
    }

    @Override
    public void requestCollectList(int page, @NonNull GetRequest request) {
        userRequest.requestCollectListApi(page, request);
    }

    @Override
    public LiveData<DataResult<List<ContentVo>>> getCollectListLiveData() {
        return userRequest.getCollectListLiveData();
    }
}
