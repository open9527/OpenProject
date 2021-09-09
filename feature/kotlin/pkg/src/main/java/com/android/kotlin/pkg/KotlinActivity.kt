package com.android.kotlin.pkg

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.android.kotlin.pkg.net.OkHttpClientUtils
import com.android.kotlin.pkg.net.ResultCallback
import com.android.kotlin.pkg.net.httpUtils
import com.android.open9527.common.page.BaseCommonActivity
import com.android.open9527.page.DataBindingConfig
import com.android.open9527.permissions.OnPermissionCallback
import com.android.open9527.permissions.Permission
import com.android.open9527.permissions.XXPermissions
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


/**
 * @author   open_9527
 * Create at 2021/5/18
 */
class KotlinActivity() : BaseCommonActivity() {

    private var mViewModel: KotlinViewModel? = null

    private val mUrl = "https://wanandroid.com/user_article/list/0/json"

    // 1、createClient
    private val mClient = OkHttpClient()

    // createClientTwo
    private val mClient2 = OkHttpClient.Builder().build()

    // 2、createRequest
    private val mRequest = Request.Builder().url(mUrl).build()


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(KotlinViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.kotlin_activity, BR.vm, mViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
    }


    override fun initView(bundle: Bundle?) {
        super.initView(bundle)
        LogUtils.i(TAG, "initView")

//        tv_title.text = mViewModel?.valueTitle?.get()

//        val title: TextView? = (getBinding() as KotlinActivityBinding?)?.tvTitle
//        title?.text = mViewModel?.valueTitle?.get()

//        (getBinding() as KotlinActivityBinding?)?.tvTitle?.text=mViewModel?.valueTitle?.get()
    }

    override fun initRequest() {
        super.initRequest()
//        okhttpRequest()

        getAsync(
            "https://wanandroid.com/user_article/list/0/json",
            object : ResultCallback<Response>() {
                override fun onError(request: Request, exception: Exception) {

                }

                override fun onResponse(response: Response) {
                    val body = response.body?.string()
                    LogUtils.i(TAG, GsonUtils.toJson(body))
                }
            }
        )

        // 3、发起同步get请求
//        GlobalScope.launch {
//            mClient.newCall(mRequest).execute().use { response ->
//                if (response.isSuccessful) {
//                    LogUtils.i(
//                        TAG,
//                        "request success code is ${response.code}  body is ${response.body?.string()}"
//                    )
//                } else {
//                    LogUtils.i(TAG, "request error code is ${response.code}")
//                }
//            }
//
//        }

    }


    private fun okhttpRequest() {
        httpUtils {
            urls = "https://wanandroid.com/user_article/list/0/json"
            method = "GET"
            timeout = 2500
            onSuccess {
                var stA = it.body?.string()
                runOnUiThread() {
                    LogUtils.i(TAG, stA)
                }
            }
            onFail {
                LogUtils.i(TAG, GsonUtils.toJson(it))
            }

        }
    }


    inner class ClickProxy {

        var backClick = View.OnClickListener {

            mViewModel?.valueTitle?.set("ClickProxy")
//            requestPermissions()
//            okhttpRequest();


            LogUtils.i(TAG, "backClick")
        }
    }

    fun requestPermissions() {
        XXPermissions.with(this)
            .permission(Permission.CAMERA)
            .permission(Permission.Group.STORAGE)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>?, all: Boolean) {
                    if (all) {
                        showLongToast("获取权限成功")
                    } else {
                        showLongToast("获取部分权限成功，但部分权限未正常授予")
                    }
                }

                override fun onDenied(permissions: List<String>?, never: Boolean) {
                    if (never) {
                        showLongToast("被永久拒绝授权，请手动授予权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(mActivity!!, permissions)
                    } else {
                        showLongToast("获取权限失败")
                    }
                }
            })
    }


    fun getAsync(url: String, callback: ResultCallback<*>) {
        val request = Request.Builder()
            .url(url)
            .build()
        deliverResult(callback, request)
    }


    /**
     * get 只带token(放headers中)
     * @param headerMap token
     */
    fun getAsync(
        url: String,
        headerMap: Map<String, String>,
        callback: ResultCallback<*>
    ) {
        // okhttp get 请求添加参数
        val request = Request.Builder()
            .url(url)
            .addHeaderMap(headerMap)
            .build()
        deliverResult(callback, request)
    }

    /**
     * get 只带参数
     * @param hashMap 参数列表
     */
    fun getAsync(
        url: String,
        params: HashMap<String, String>,
        callback: ResultCallback<*>
    ) {

        // okhttp get 请求添加参数
        val urlBuilder = url.toHttpUrlOrNull()?.newBuilder()
            ?.apply {
                addQueryParamMap(params)
            }


        val request = Request.Builder()
            .url(urlBuilder!!.build())
            .build()
        deliverResult(callback, request)
    }

    /**
     * get 带参数请求,带token
     * @param hashMap 参数列表
     * @param headerMap token
     */
    fun getAsync(
        url: String,
        hashMap: HashMap<String, String>,
        headerMap: Map<String, String>,
        callback: ResultCallback<*>
    ) {

        // okhttp get 请求添加参数
        val urlBuilder = url.toHttpUrlOrNull()?.newBuilder()
            ?.apply {
                addQueryParamMap(hashMap)
            }

        val request = Request.Builder()
            .url(urlBuilder!!.build())
            .addHeaderMap(headerMap)
            .build()
        deliverResult(callback, request)
    }

    /**
     * put 带参数请求,带token
     * @param hashMap 参数列表(参数form-body请求)
     * @param headerMap token
     */
    fun putAsync(
        url: String,
        hashMap: HashMap<String, String>,
        headerMap: Map<String, String>,
        callback: ResultCallback<*>
    ) {

        val body = FormBody.Builder().apply {
            addHeaderMap(hashMap)
        }.build()

        val request = Request.Builder()
            .url(url)
            .put(body)
            .addHeaderMap(headerMap)
            .build()
        deliverResult(callback, request)
    }


    /**
     * post 带参数,(raw请求方式)
     *      带token
     */
    fun postAsync(
        url: String,
        headerMap: Map<String, String>,
        jsonObject: JSONObject,
        callback: ResultCallback<*>
    ) {
        val contentType: MediaType = "application/x-www-form-urlencoded".toMediaType()
//        val contentType: MediaType = MediaType.get("application/json; charset=utf-8")
        val body: RequestBody = jsonObject.toString().toRequestBody(contentType)

        val request: Request = Request
            .Builder()
            .url(url)
            .addHeaderMap(headerMap)
            .post(body)
            .build()
        deliverResult(callback, request)
    }

    /**
     * put请求 带参数,带token(参数row请求方式)
     */
    fun putAsync(
        url: String,
        jsonObject: JSONObject,
        headerMap: Map<String, String>,
        callback: ResultCallback<*>
    ) {
        val contentType: MediaType = "application/x-www-form-urlencoded".toMediaType()
        val body: RequestBody = jsonObject.toString().toRequestBody(contentType)

        val request = Request.Builder()
            .url(url)
            .addHeaderMap(headerMap)
            .put(body)
            .build()
        deliverResult(callback, request)
    }

    /**
     * delete
     * 带参数,带token
     */
    fun deleteAsync(
        url: String,
        paramMap: HashMap<String, String>,
        headerMap: Map<String, String>,
        callback: ResultCallback<*>
    ) {

        val body = FormBody.Builder().apply {
            addHeaderMap(paramMap)
        }.build()

        val request: Request = Request
            .Builder()
            .url(url)
            .addHeaderMap(headerMap)
            .delete(body)
            .build()

        deliverResult(callback, request)
    }

    private fun deliverResult(callback: ResultCallback<*>, request: Request) {
        OkHttpClientUtils.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                sendFailureStringCallback(request, e, callback)
            }

            override fun onResponse(call: Call, response: Response) {
                sendSuccessResultCallback(response, callback as ResultCallback<Any>)
            }
        })
    }

    // 创建一个单例的handler,发送在主线程的.
    private val mHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun sendFailureStringCallback(
        request: Request,
        exception: Exception,
        callback: ResultCallback<*>
    ) {
        // 这里回调在主线程
        mHandler.post {
            callback.onError(request, exception)
        }
    }

    /**
     * 这里就不在这里回调到主线程,前面做处理把
     */
    fun sendSuccessResultCallback(mObject: Any, callback: ResultCallback<Any>) {
        callback.onResponse(mObject)
    }


    //写接口扩展函数, 方便在做请求的时候,直接可以传入Map类型
    private fun Request.Builder.addHeaderMap(
        headerMap: Map<String, String>
    ): Request.Builder {
        headerMap.forEach { (key, token) ->
            this.addHeader(key, token)
        }
        return this
    }

    private fun FormBody.Builder.addHeaderMap(
        headerMap: Map<String, String>
    ): FormBody.Builder {
        headerMap.forEach { (key, token) ->
            this.add(key, token)
        }
        return this
    }

    private fun HttpUrl.Builder.addQueryParamMap(headerMap: HashMap<String, String>): HttpUrl.Builder {
        headerMap.forEach { (key, value) ->
            this.addQueryParameter(key, value)
        }
        return this
    }


}