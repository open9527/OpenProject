package com.open9527.wanandroid.pkg.net.article;

import androidx.annotation.NonNull;

import com.android.open9527.common.net.data.HttpData;
import com.android.open9527.common.net.data.response.DataResult;
import com.android.open9527.common.net.data.response.ResponseStatus;
import com.android.open9527.okhttp.OkHttpClientUtils;
import com.android.open9527.okhttp.listener.HttpCallback;
import com.android.open9527.okhttp.listener.OnHttpListener;
import com.android.open9527.okhttp.request.GetRequest;
import com.open9527.wanandroid.pkg.net.BannerVo;
import com.open9527.wanandroid.pkg.net.DataVo;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class ArticleDataRepository implements OnHttpListener {

    private ArticleDataRepository() {

    }

    private static class RepositoryInstance {
        private static final ArticleDataRepository INSTANCE = new ArticleDataRepository();
        ;
    }

    public static ArticleDataRepository getInstance() {
        return RepositoryInstance.INSTANCE;
    }


    public void banner(@NonNull GetRequest request, @NonNull DataResult.Result<List<BannerVo>> dataVoResult) {
        request.api(new BannerApi())
                .request(new HttpCallback<HttpData<List<BannerVo>>>(this) {
                    @Override
                    public void onSucceed(HttpData<List<BannerVo>> result) {
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

    public void article(@NonNull int page, @NonNull GetRequest request, @NonNull DataResult.Result<DataVo> dataVoResult) {
        request.api(new ArticleApi().setPage(page))
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

}
