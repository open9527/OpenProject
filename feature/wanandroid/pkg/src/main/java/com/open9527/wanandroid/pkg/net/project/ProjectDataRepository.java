package com.open9527.wanandroid.pkg.net.project;

import androidx.annotation.NonNull;

import com.android.open9527.common.net.data.HttpData;
import com.android.open9527.common.net.data.response.DataResult;
import com.android.open9527.common.net.data.response.ResponseStatus;
import com.android.open9527.okhttp.listener.HttpCallback;
import com.android.open9527.okhttp.listener.OnHttpListener;
import com.android.open9527.okhttp.request.GetRequest;
import com.open9527.wanandroid.pkg.net.DataVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class ProjectDataRepository implements OnHttpListener {

    private static final ProjectDataRepository S_REQUEST_MANAGER = new ProjectDataRepository();

    private ProjectDataRepository() {

    }

    public static ProjectDataRepository getInstance() {
        return S_REQUEST_MANAGER;
    }

    public void project(@NonNull int page,@NonNull String cId, @NonNull GetRequest request, @NonNull DataResult.Result<DataVo> dataVoResult) {
        request.api(new ProjectApi().setPage(page).setCid(cId))
                .request(new HttpCallback<HttpData<DataVo>>(this) {
                    @Override
                    public void onSucceed(HttpData<DataVo> result) {
                        super.onSucceed(result);
                        dataVoResult.onResult(new DataResult<>(result.getData(), new ResponseStatus()));
                    }

                    @Override
                    public void onFail(Exception e) {
                        super.onFail(e);
                        dataVoResult.onResult(new DataResult<>(new DataVo(), new ResponseStatus("-1", false)));
                    }
                });
    }


    public void projectTree( @NonNull GetRequest request, @NonNull DataResult.Result<List<ProjectTreeVo>> dataVoResult) {
        request.api(new ProjectTreeApi())
                .request(new HttpCallback<HttpData<List<ProjectTreeVo>>>(this) {
                    @Override
                    public void onSucceed(HttpData<List<ProjectTreeVo>> result) {
                        super.onSucceed(result);
                        dataVoResult.onResult(new DataResult<>(result.getData(), new ResponseStatus()));
                    }

                    @Override
                    public void onFail(Exception e) {
                        super.onFail(e);
                        dataVoResult.onResult(new DataResult<>(new ArrayList<>(), new ResponseStatus("-1", false)));
                    }
                });
    }

}
