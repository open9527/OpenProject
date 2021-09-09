package com.android.kotlin.pkg.net

import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 *@author   open_9527
 *Create at 2021/8/17
 **/
class RequestBuilder : Callback {

    var urls: String = ""

    var method: String = "GET"

    var body: RequestBody? = null

    var timeout: Long = 3000

    var success: ((Response) -> Unit)? = null

    var faild: ((Throwable) -> Unit)? = null

    fun onSuccess(onSuccess: (Response) -> Unit) {
        success = onSuccess
    }

    fun onFail(onError: (Throwable) -> Unit) {
        faild = onError
    }

    override fun onFailure(call: Call, e: IOException) {
        faild?.invoke(e)
    }

    override fun onResponse(call: Call, response: Response) {
        success?.invoke(response)
    }

    fun request() = OkHttpClient.Builder().run {
        connectTimeout(timeout, TimeUnit.MILLISECONDS)
        readTimeout(timeout, TimeUnit.MILLISECONDS)
        retryOnConnectionFailure(true)
        build().newCall(
            when (method) {
                "POST" -> Request.Builder().url(urls).post(body!!).build()
                else -> Request.Builder().url(urls).build()
            }
        )
    }.enqueue(this)
}

fun httpUtils(init: RequestBuilder.() -> Unit) {
    val wrap = RequestBuilder()
    wrap.init()
    wrap.request()
}