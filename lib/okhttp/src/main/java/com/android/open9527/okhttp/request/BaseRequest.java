package com.android.open9527.okhttp.request;

import androidx.lifecycle.LifecycleOwner;

import com.android.open9527.okhttp.HttpConfig;
import com.android.open9527.okhttp.HttpLog;
import com.android.open9527.okhttp.HttpUtils;
import com.android.open9527.okhttp.annotation.HttpHeader;
import com.android.open9527.okhttp.annotation.HttpIgnore;
import com.android.open9527.okhttp.annotation.HttpRename;
import com.android.open9527.okhttp.callback.NormalCallback;
import com.android.open9527.okhttp.config.IRequestApi;
import com.android.open9527.okhttp.config.IRequestHandler;
import com.android.open9527.okhttp.config.IRequestHost;
import com.android.open9527.okhttp.config.IRequestInterceptor;
import com.android.open9527.okhttp.config.IRequestPath;
import com.android.open9527.okhttp.config.IRequestServer;
import com.android.open9527.okhttp.config.IRequestType;
import com.android.open9527.okhttp.config.RequestApi;
import com.android.open9527.okhttp.config.RequestServer;
import com.android.open9527.okhttp.listener.OnHttpListener;
import com.android.open9527.okhttp.model.BodyType;
import com.android.open9527.okhttp.model.CallProxy;
import com.android.open9527.okhttp.model.HttpHeaders;
import com.android.open9527.okhttp.model.HttpParams;
import com.android.open9527.okhttp.model.ResponseClass;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
@SuppressWarnings("unchecked")
public abstract class BaseRequest<T extends BaseRequest> {

    /** OkHttp 客户端 */
    private OkHttpClient mClient = HttpConfig.getInstance().getClient();

    /** 请求处理策略 */
    private IRequestHandler mRequestHandler = HttpConfig.getInstance().getHandler();

    /** 接口主机地址 */
    private IRequestHost mRequestHost = HttpConfig.getInstance().getServer();
    /** 接口路径地址 */
    private IRequestPath mRequestPath = HttpConfig.getInstance().getServer();
    /** 提交参数类型 */
    private IRequestType mRequestType = HttpConfig.getInstance().getServer();

    /** 请求接口配置 */
    private IRequestApi mRequestApi;

    /** 请求生命周期控制 */
    private LifecycleOwner mLifecycleOwner;

    /** 请求执行代理类 */
    private CallProxy mCallProxy;

    /** 请求标记 */
    private String mTag;

    public BaseRequest(LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner == null) {
            throw new IllegalArgumentException("Please check LifecycleOwner");
        }
        mLifecycleOwner = lifecycleOwner;
        tag(lifecycleOwner);
    }

    public T api(Class<? extends IRequestApi> api) {
        try {
            return api(api.newInstance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public T api(String api) {
        return api(new RequestApi(api));
    }

    /**
     * 设置请求配置
     */
    public T api(IRequestApi api) {
        mRequestApi = api;
        if (api instanceof IRequestHost) {
            mRequestHost = (IRequestHost) api;
        }
        if (api instanceof IRequestPath) {
            mRequestPath = (IRequestPath) api;
        }
        if (api instanceof IRequestType) {
            mRequestType = (IRequestType) api;
        }
        return (T) this;
    }

    public T server(Class<? extends IRequestServer> api) {
        try {
            return server(api.newInstance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public T server(String host) {
        return server(new RequestServer(host));
    }

    /**
     * 替换默认的服务器配器（推荐使用 api 的方式来替代 server，具体实现可见 api 方法源码）
     */
    public T server(IRequestServer server) {
        mRequestHost = server;
        mRequestPath = server;
        mRequestType = server;
        return (T) this;
    }

    /**
     * 替换默认的请求处理策略
     */
    public T handler(IRequestHandler handler) {
        mRequestHandler = handler;
        return (T) this;
    }

    /**
     * 设置请求的标记
     */
    public T tag(Object tag) {
        if (tag != null) {
            return tag(String.valueOf(tag));
        }
        return (T) this;
    }

    public T tag(String tag) {
        mTag = tag;
        return (T) this;
    }

    /**
     * 替换 OkHttpClient
     */
    public T client(OkHttpClient client) {
        mClient = client;
        return (T) this;
    }

    /**
     * 创建连接对象
     */
    protected Call createCall() {

        BodyType type = mRequestType.getType();

        HttpParams params = new HttpParams();
        HttpHeaders headers = new HttpHeaders();

        Field[] fields = mRequestApi.getClass().getDeclaredFields();
        params.setMultipart(HttpUtils.isMultipart(fields));
        // 如果参数中包含流参数并且当前请求方式不是表单的话
        if (params.isMultipart() && type != BodyType.FORM) {
            // 就强制设置成以表单形式提交参数
            type = BodyType.FORM;
        }

        for (Field field : fields) {
            // 允许访问私有字段
            field.setAccessible(true);

            try {
                // 获取字段的对象
                Object value = field.get(mRequestApi);

                // 获取字段的名称
                String key;
                HttpRename annotation = field.getAnnotation(HttpRename.class);
                if (annotation != null) {
                    key = annotation.value();
                } else {
                    key = field.getName();
                    // 如果是内部类则会出现一个字段名为 this$0 的外部类对象，会导致无限递归，这里要忽略掉，如果使用静态内部类则不会出现这个问题
                    // 和规避 Kotlin 自动生成的伴生对象
                    if (key.matches("this\\$\\d+") || "Companion".equals(key)) {
                        continue;
                    }
                }

                // 如果这个字段需要忽略，则进行忽略
                if (field.isAnnotationPresent(HttpIgnore.class)) {
                    if (field.isAnnotationPresent(HttpHeader.class)) {
                        headers.remove(key);
                    } else {
                        params.remove(key);
                    }
                    continue;
                }

                // 前提是这个字段值不能为空（基本数据类型有默认的值，而对象默认的值为 null）
                if (HttpUtils.isEmpty(value)) {
                    // 遍历下一个字段
                    continue;
                }

                // 如果这是一个请求头参数
                if (field.isAnnotationPresent(HttpHeader.class)) {
                    if (value instanceof Map) {
                        Map map = ((Map) value);
                        for (Object o : map.keySet()) {
                            if (o != null && map.get(o) != null) {
                                headers.put(String.valueOf(o), String.valueOf(map.get(o)));
                            }
                        }
                    } else {
                        headers.put(key, String.valueOf(value));
                    }
                    continue;
                }

                // 否则这就是一个普通的参数
                switch (type) {
                    case FORM:
                        if (value instanceof Map) {
                            Map map = ((Map) value);
                            for (Object o : map.keySet()) {
                                if (o != null && map.get(o) != null) {
                                    params.put(String.valueOf(o), map.get(o));
                                }
                            }
                        } else {
                            params.put(key, value);
                        }
                        break;
                    case JSON:
                        if (value instanceof List) {
                            // 如果这是一个 List 参数
                            params.put(key, HttpUtils.listToJsonArray(((List) value)));
                        } else if (value instanceof Map) {
                            // 如果这是一个 Map 参数
                            params.put(key, HttpUtils.mapToJsonObject(((Map) value)));
                        } else if (HttpUtils.isBeanType(value)) {
                            // 如果这是一个 Bean 参数
                            params.put(key, HttpUtils.mapToJsonObject(HttpUtils.beanToHashMap(value)));
                        } else {
                            // 如果这是一个普通的参数
                            params.put(key, value);
                        }
                        break;
                    default:
                        break;
                }

            } catch (IllegalAccessException e) {
                HttpLog.print(e);
            }
        }

        String url = mRequestHost.getHost() + mRequestPath.getPath() + mRequestApi.getApi();
        IRequestInterceptor interceptor = HttpConfig.getInstance().getInterceptor();
        if (interceptor != null) {
            interceptor.intercept(url, mTag, params, headers);
        }
        return mClient.newCall(createRequest(url, mTag, params, headers, type));
    }

    /**
     * 执行异步请求
     */
    public T request(OnHttpListener<?> listener) {
        HttpLog.print(new Throwable().getStackTrace());
        mCallProxy = new CallProxy(createCall());
        mCallProxy.enqueue(new NormalCallback(getLifecycleOwner(), mCallProxy, mRequestHandler, listener));
        return (T) this;
    }

    /**
     * 执行同步请求
     * @param t                 需要解析泛型的对象
     * @return                  返回解析完成的对象
     * @throws Exception        如果请求失败或者解析失败则抛出异常
     */
    public <T> T execute(ResponseClass<T> t) throws Exception {
        HttpLog.print(new Throwable().getStackTrace());
        try {
            mCallProxy = new CallProxy(createCall());
            Response response = mCallProxy.execute();
            return (T) mRequestHandler.requestSucceed(getLifecycleOwner(), response, HttpUtils.getReflectType(t));
        } catch (Exception e) {
            throw mRequestHandler.requestFail(getLifecycleOwner(), e);
        }
    }

    /**
     * 取消请求
     */
    public T cancel() {
        if (mCallProxy != null) {
            mCallProxy.cancel();
        }
        return (T) this;
    }

    /**
     * 获取生命周期管控对象
     */
    protected LifecycleOwner getLifecycleOwner() {
        return mLifecycleOwner;
    }

    /**
     * 获取请求的方式
     */
    protected abstract String getRequestMethod();

    /**
     * 创建请求的对象
     */
    protected abstract Request createRequest(String url, String tag, HttpParams params, HttpHeaders headers, BodyType type);
}