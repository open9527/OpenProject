package com.android.open9527.okhttp.request;

import androidx.lifecycle.LifecycleOwner;

import com.android.open9527.okhttp.HttpLog;
import com.android.open9527.okhttp.callback.DownloadCallback;
import com.android.open9527.okhttp.config.RequestApi;
import com.android.open9527.okhttp.config.RequestServer;
import com.android.open9527.okhttp.listener.OnDownloadListener;
import com.android.open9527.okhttp.listener.OnHttpListener;
import com.android.open9527.okhttp.model.BodyType;
import com.android.open9527.okhttp.model.CallProxy;
import com.android.open9527.okhttp.model.HttpHeaders;
import com.android.open9527.okhttp.model.HttpMethod;
import com.android.open9527.okhttp.model.HttpParams;
import com.android.open9527.okhttp.model.ResponseClass;

import java.io.File;

import okhttp3.Request;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public final class DownloadRequest extends BaseRequest<DownloadRequest> {

    /** 下载请求方式 */
    private HttpMethod mMethod = HttpMethod.GET;

    /** 保存的文件 */
    private File mFile;

    /** 校验的 md5 */
    private String mMd5;

    /** 下载监听回调 */
    private OnDownloadListener mListener;

    /** 请求执行对象 */
    private CallProxy mCallProxy;

    public DownloadRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    /**
     * 设置请求方式
     */
    public DownloadRequest method(HttpMethod method) {
        mMethod = method;
        return this;
    }

    /**
     * 设置下载地址
     */
    public DownloadRequest url(String url) {
        server(new RequestServer(url));
        api(new RequestApi(""));
        return this;
    }

    /**
     * 设置保存的路径
     */
    public DownloadRequest file(File file) {
        mFile = file;
        return this;
    }

    public DownloadRequest file(String file) {
        mFile = new File(file);
        return this;
    }

    /**
     * 设置 MD5 值
     */
    public DownloadRequest md5(String md5) {
        mMd5 = md5;
        return this;
    }

    /**
     * 设置下载监听
     */
    public DownloadRequest listener(OnDownloadListener listener) {
        mListener = listener;
        return this;
    }

    @Override
    protected Request createRequest(String url, String tag, HttpParams params, HttpHeaders headers, BodyType type) {
        switch (mMethod) {
            case GET:
                // 如果这个下载请求方式是 Get
                return new GetRequest(getLifecycleOwner()).createRequest(url, tag, params, headers, type);
            case POST:
                // 如果这个下载请求方式是 Post
                return new PostRequest(getLifecycleOwner()).createRequest(url, tag, params, headers, type);
            default:
                throw new IllegalStateException("Please check the request method");
        }
    }

    /**
     * 开始下载
     */
    public DownloadRequest start() {
        HttpLog.print(new Throwable().getStackTrace());
        mCallProxy = new CallProxy(createCall());
        /** 下载回调对象 */
        mCallProxy.enqueue(new DownloadCallback(getLifecycleOwner(), mCallProxy, mFile, mMd5, mListener));
        return this;
    }

    /**
     * 取消下载
     */
    public DownloadRequest stop() {
        if (mCallProxy != null) {
            mCallProxy.cancel();
        }
        return this;
    }

    @Override
    public DownloadRequest request(OnHttpListener listener) {
        // 请调用 start 方法
        throw new IllegalStateException("Please call the start method");
    }

    @Override
    public <T> T execute(ResponseClass<T> t) {
        // 请调用 start 方法
        throw new IllegalStateException("Please call the start method");
    }

    @Override
    public DownloadRequest cancel() {
        // 请调用 stop 方法
        throw new IllegalStateException("Please call the stop method");
    }

    @Override
    protected String getRequestMethod() {
        return String.valueOf(mMethod);
    }
}