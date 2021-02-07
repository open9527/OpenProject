package com.open9527.wanandroid.pkg.net.project;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.open9527.common.net.data.BaseRequest;
import com.android.open9527.common.net.data.response.DataResult;
import com.android.open9527.okhttp.request.GetRequest;
import com.open9527.wanandroid.pkg.net.DataVo;
import com.open9527.wanandroid.pkg.net.share.ShareDataRepository;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class ProjectRequest extends BaseRequest {

    private final MutableLiveData<DataResult<DataVo>> projectLiveData = new MutableLiveData<>();

    private final MutableLiveData<DataResult<List<ProjectTreeVo>>> projectTreeLiveData = new MutableLiveData<>();

    public LiveData<DataResult<DataVo>> getProjectLiveData() {

        return projectLiveData;
    }

    public LiveData<DataResult<List<ProjectTreeVo>>> getProjectTreeLiveData() {
        return projectTreeLiveData;
    }

    public void requestProject(@NonNull int page, @NonNull String cId, @NonNull GetRequest request) {
        ProjectDataRepository.getInstance().project(page,cId, request, projectLiveData::postValue);
    }

    public void requestProjectTreeApi(@NonNull GetRequest request) {
        ProjectDataRepository.getInstance().projectTree( request, projectTreeLiveData::postValue);
    }
}
