package com.android.open9527.okhttp.request;

import androidx.lifecycle.LifecycleOwner;

import com.android.open9527.okhttp.HttpConfig;
import com.android.open9527.okhttp.HttpLog;
import com.android.open9527.okhttp.HttpUtils;
import com.android.open9527.okhttp.body.JsonBody;
import com.android.open9527.okhttp.body.ProgressBody;
import com.android.open9527.okhttp.body.StringBody;
import com.android.open9527.okhttp.body.UpdateBody;
import com.android.open9527.okhttp.listener.OnHttpListener;
import com.android.open9527.okhttp.listener.OnUpdateListener;
import com.android.open9527.okhttp.model.BodyType;
import com.android.open9527.okhttp.model.HttpHeaders;
import com.android.open9527.okhttp.model.HttpParams;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
@SuppressWarnings("unchecked")
public abstract class BodyRequest<T extends BodyRequest> extends BaseRequest<T> {

    private OnUpdateListener mUpdateListener;

    private RequestBody mRequestBody;

    public BodyRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    /**
     * 自定义 json 字符串
     */
    public T json(Map map) {
        if (map == null) {
            return (T) this;
        }
        return body(new JsonBody(map));
    }

    public T json(List list) {
        if (list == null) {
            return (T) this;
        }
        return body(new JsonBody(list));
    }

    public T json(String json) {
        if (json == null) {
            return (T) this;
        }
        return body(new JsonBody(json));
    }

    /**
     * 自定义文本字符串
     */
    public T body(String text) {
        if (text == null) {
            return (T) this;
        }
        return body(new StringBody(text));
    }

    /**
     * 自定义 RequestBody
     */
    public T body(RequestBody body) {
        mRequestBody = body;
        return (T) this;
    }

    @Override
    protected Request createRequest(String url, String tag, HttpParams params, HttpHeaders headers, BodyType type) {
        Request.Builder request = new Request.Builder();
        request.url(url);

        HttpLog.print("RequestUrl", url);
        HttpLog.print("RequestMethod", getRequestMethod());

        if (tag != null) {
            request.tag(tag);
        }

        // 添加请求头
        if (!headers.isEmpty()) {
            for (String key : headers.getNames()) {
                request.addHeader(key, headers.get(key));
            }
        }

        RequestBody body = mRequestBody != null ? mRequestBody : createBody(params, type);
        request.method(getRequestMethod(), body);

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

            if (body instanceof FormBody ||
                    body instanceof MultipartBody ||
                    body instanceof ProgressBody) {
                // 打印表单
                for (String key : params.getNames()) {
                    Object value = params.get(key);
                    if (value instanceof String) {
                        HttpLog.print(key, "\"" + value + "\"");
                    } else {
                        HttpLog.print(key, String.valueOf(value));
                    }
                }
            } else if (body instanceof JsonBody) {
                // 打印 Json
                HttpLog.json(body.toString());
            } else {
                // 打印文本
                HttpLog.print(body.toString());
            }

            if (!headers.isEmpty() || !params.isEmpty()) {
                HttpLog.print();
            }
        }

        return request.build();
    }

    /**
     * 执行异步请求（执行传入上传进度监听器）
     */
    @Override
    public T request(OnHttpListener<?> listener) {
        if (listener instanceof OnUpdateListener) {
            mUpdateListener = (OnUpdateListener) listener;
        }
        return super.request(listener);
    }

    /**
     * 组装 RequestBody 对象
     */
    private RequestBody createBody(HttpParams params, BodyType type) {
        if (params.isMultipart() && !params.isEmpty()) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            for (String key : params.getNames()) {
                Object object = params.get(key);

                // 如果这是一个文件
                if (object instanceof File) {
                    MultipartBody.Part part = HttpUtils.createPart(key, (File) object);
                    if (part != null) {
                        builder.addPart(part);
                    }
                    continue;
                }

                // 如果这是一个输入流
                if (object instanceof InputStream) {
                    MultipartBody.Part part = HttpUtils.createPart(key, (InputStream) object);
                    if (part != null) {
                        builder.addPart(part);
                    }
                    continue;
                }

                // 如果这是一个自定义 RequestBody
                if (object instanceof RequestBody) {
                    if (object instanceof UpdateBody) {
                        builder.addFormDataPart(key, HttpUtils.encodeString(((UpdateBody) object).getName()), (RequestBody) object);
                    } else {
                        builder.addFormDataPart(key, null, (RequestBody) object);
                    }
                    continue;
                }

                // 上传文件列表
                if (object instanceof List && HttpUtils.isFileList((List) object)) {
                    for (Object item : (List) object) {
                        MultipartBody.Part part = HttpUtils.createPart(key, (File) item);
                        if (part != null) {
                            builder.addPart(part);
                        }
                    }
                    continue;
                }

                // 如果这是一个普通参数
                builder.addFormDataPart(key, String.valueOf(object));
            }

            if (mUpdateListener != null) {
                return new ProgressBody(builder.build(), getLifecycleOwner(), mUpdateListener);
            }
            return builder.build();
        }

        if (type == BodyType.JSON) {
            if (mUpdateListener != null) {
                return new ProgressBody(new JsonBody(params.getParams()), getLifecycleOwner(), mUpdateListener);
            }
            return new JsonBody(params.getParams());
        }

        FormBody.Builder builder = new FormBody.Builder();
        if (!params.isEmpty()) {
            for (String key : params.getNames()) {
                builder.add(key, String.valueOf(params.get(key)));
            }
        }
        if (mUpdateListener != null) {
            return new ProgressBody(builder.build(), getLifecycleOwner(), mUpdateListener);
        }

        return builder.build();
    }
}