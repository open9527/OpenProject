package com.open9527.wanandroid.pkg.net.article;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.open9527.common.net.data.BaseRequest;
import com.android.open9527.common.net.data.response.DataResult;
import com.android.open9527.okhttp.request.GetRequest;
import com.open9527.wanandroid.pkg.net.BannerVo;
import com.open9527.wanandroid.pkg.net.DataVo;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class ArticleRequest extends BaseRequest {

    private final MutableLiveData<DataResult<List<BannerVo>>> bannerLiveData = new MutableLiveData<>();
    private final MutableLiveData<DataResult<DataVo>> articleLiveData = new MutableLiveData<>();

    public LiveData<DataResult<List<BannerVo>>> getBannerLiveData() {

        return bannerLiveData;
    }

    public LiveData<DataResult<DataVo>> getArticleLiveData() {
        return articleLiveData;
    }

    public void requestBanner(@NonNull GetRequest request) {
        ArticleDataRepository.getInstance().banner(request, bannerLiveData::postValue);
    }

    public void requestArticle(int page, @NonNull GetRequest request) {
        ArticleDataRepository.getInstance().article(page, request, articleLiveData::postValue);
    }

}
