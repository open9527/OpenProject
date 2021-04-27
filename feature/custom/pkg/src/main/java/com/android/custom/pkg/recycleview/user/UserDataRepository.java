package com.android.custom.pkg.recycleview.user;

import androidx.annotation.NonNull;

import com.android.open9527.common.net.data.HttpData;
import com.android.open9527.common.net.data.response.DataResult;
import com.android.open9527.common.net.data.response.ResponseStatus;
import com.android.open9527.okhttp.listener.HttpCallback;
import com.android.open9527.okhttp.listener.OnHttpListener;
import com.android.open9527.okhttp.request.GetRequest;
import com.android.open9527.okhttp.request.PostRequest;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class UserDataRepository implements OnHttpListener {
    private UserDataRepository() {

    }

    private static class RepositoryInstance {
        private static final UserDataRepository INSTANCE = new UserDataRepository();
    }

    public static UserDataRepository getInstance() {
        return RepositoryInstance.INSTANCE;
    }

    public void login(@NonNull String userName,@NonNull String passWord, @NonNull PostRequest request, @NonNull DataResult.Result<Object> dataVoResult) {
        request.api(new LoginApi().setUserName(userName)
                .setPassWord(passWord))
                .request(new HttpCallback<HttpData>(this) {
                    @Override
                    public void onSucceed(HttpData result) {
                        super.onSucceed(result);
                        dataVoResult.onResult(new DataResult<>(result.getData(), new ResponseStatus()));
                    }

                    @Override
                    public void onFail(Exception e) {
                        super.onFail(e);
                        dataVoResult.onResult(new DataResult<>(null, new ResponseStatus("-1", false)));
                    }
                });
    }


    public void collectList(int page,@NonNull GetRequest request, @NonNull DataResult.Result<List<ContentVo>> dataVoResult) {
        request.api(new CollectApi().setPage(page))
                .request(new HttpCallback<HttpData<DataVo>>(this) {
                    @Override
                    public void onSucceed(HttpData<DataVo> result) {
                        super.onSucceed(result);
                        dataVoResult.onResult(new DataResult<>(result.getData().getContentVos(), new ResponseStatus()));
                    }

                    @Override
                    public void onFail(Exception e) {
                        super.onFail(e);
                        dataVoResult.onResult(new DataResult<>(null, new ResponseStatus("-1", false)));
                    }
                });
    }

}
