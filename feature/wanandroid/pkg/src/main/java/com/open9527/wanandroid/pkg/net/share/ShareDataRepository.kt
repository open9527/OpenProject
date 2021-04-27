package com.open9527.wanandroid.pkg.net.share

import com.android.open9527.common.net.data.HttpData
import com.android.open9527.common.net.data.response.DataResult
import com.android.open9527.common.net.data.response.ResponseStatus
import com.android.open9527.okhttp.listener.HttpCallback
import com.android.open9527.okhttp.listener.OnHttpListener
import com.android.open9527.okhttp.request.GetRequest
import com.open9527.wanandroid.pkg.net.DataVo
import com.open9527.wanandroid.pkg.net.user.CollectApi

/**
 * @author open_9527
 * Create at 2021/1/15
 */
class ShareDataRepository private constructor() : OnHttpListener<Any?> {
    fun share(page: Int, request: GetRequest, dataVoResult: (DataResult<DataVo>) -> Unit) {
        request.api(ShareApi().setPage(page))
//        request.api(CollectApi().setPage(page))
            .request(object : HttpCallback<HttpData<DataVo?>?>(this) {
                override fun onSucceed(result: HttpData<DataVo?>?) {
                    super.onSucceed(result)
                    result?.let {
                        dataVoResult.invoke(DataResult(result.data, ResponseStatus()))
                    }
                }

                override fun onFail(e: Exception) {
                    super.onFail(e)
                    dataVoResult.invoke(DataResult(DataVo(), ResponseStatus("-1", false)))
                }
            })
    }

    companion object {
        val instance = ShareDataRepository()
    }
}