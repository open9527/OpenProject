package com.android.open9527.okhttp.request;

import androidx.lifecycle.LifecycleOwner;

import com.android.open9527.okhttp.HttpConfig;
import com.android.open9527.okhttp.HttpLog;
import com.android.open9527.okhttp.model.BodyType;
import com.android.open9527.okhttp.model.HttpHeaders;
import com.android.open9527.okhttp.model.HttpParams;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
@SuppressWarnings("unchecked")
public abstract class UrlRequest<T extends UrlRequest> extends BaseRequest<T> {

    private CacheControl mCacheControl;

    public UrlRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    /**
     * 设置缓存模式
     */
    public T cache(CacheControl cacheControl) {
        mCacheControl = cacheControl;
        return (T) this;
    }

    @Override
    protected Request createRequest(String url, String tag, HttpParams params, HttpHeaders headers, BodyType type) {
        Request.Builder request = new Request.Builder();
        if (mCacheControl != null) {
            request.cacheControl(mCacheControl);
        }

        if (tag != null) {
            request.tag(tag);
        }

        // 添加请求头
        if (!headers.isEmpty()) {
            for (String key : headers.getNames()) {
                request.addHeader(key, headers.get(key));
            }
        }

        HttpUrl.Builder builder = HttpUrl.get(url).newBuilder();
        // 添加参数
        if (!params.isEmpty()) {
            for (String key : params.getNames()) {
                builder.addEncodedQueryParameter(key, String.valueOf(params.get(key)));
            }
        }
        HttpUrl link = builder.build();
        request.url(link);
        request.method(getRequestMethod(), null);

        HttpLog.print("RequestUrl", String.valueOf(link));
        HttpLog.print("RequestMethod", getRequestMethod());

        // 打印请求头和参数的日志
        if (HttpConfig.getInstance().isLogEnabled()) {

            if (!headers.isEmpty() || !params.isEmpty()) {
                HttpLog.print();
            }

            for (String key : headers.getNames()) {
                HttpLog.print(key, headers.get(key));
            }

            if (!headers.isEmpty() && !params.isEmpty()) {
                HttpLog.print();
            }

            for (String key : params.getNames()) {
                HttpLog.print(key, String.valueOf(params.get(key)));
            }

            if (!headers.isEmpty() || !params.isEmpty()) {
                HttpLog.print();
            }
        }
        return request.build();
    }
}