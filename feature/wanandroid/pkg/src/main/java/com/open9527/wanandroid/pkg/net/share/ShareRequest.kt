package com.open9527.wanandroid.pkg.net.share

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.open9527.common.net.data.BaseRequest
import com.android.open9527.common.net.data.response.DataResult
import com.android.open9527.okhttp.request.GetRequest
import com.open9527.wanandroid.pkg.net.DataVo

/**
 * @author open_9527
 * Create at 2021/1/15
 */
class ShareRequest : BaseRequest() {
    private val shareLiveData = MutableLiveData<DataResult<DataVo>>()

    fun getShareLiveData(): LiveData<DataResult<DataVo>> {
        return shareLiveData
    }

    fun requestShare(page: Int, request: GetRequest) {
        ShareDataRepository.instance
            .share(page, request) { value: DataResult<DataVo> -> shareLiveData.postValue(value) }
    }
}