package com.android.open9527.common.net.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LifecycleOwner;

import com.android.open9527.common.R;
import com.android.open9527.common.net.data.json.BooleanTypeAdapter;
import com.android.open9527.common.net.data.json.DoubleTypeAdapter;
import com.android.open9527.common.net.data.json.FloatTypeAdapter;
import com.android.open9527.common.net.data.json.IntegerTypeAdapter;
import com.android.open9527.common.net.data.json.ListTypeAdapter;
import com.android.open9527.common.net.data.json.LongTypeAdapter;
import com.android.open9527.common.net.data.json.StringTypeAdapter;
import com.android.open9527.okhttp.HttpLog;
import com.android.open9527.okhttp.config.IRequestHandler;
import com.android.open9527.okhttp.exception.CancelException;
import com.android.open9527.okhttp.exception.DataException;
import com.android.open9527.okhttp.exception.HttpException;
import com.android.open9527.okhttp.exception.NetworkException;
import com.android.open9527.okhttp.exception.ResponseException;
import com.android.open9527.okhttp.exception.ResultException;
import com.android.open9527.okhttp.exception.ServerException;
import com.android.open9527.okhttp.exception.TimeoutException;
import com.android.open9527.okhttp.exception.TokenException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.TypeAdapters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *    请求处理类
 */
public final class RequestHandler implements IRequestHandler {

    private final Context mContext;

    private Gson mGson;

    public RequestHandler(Context context) {
        mContext = context;
    }

    @Override
    public Object requestSucceed(LifecycleOwner lifecycle, Response response, Type type) throws Exception {

        if (Response.class.equals(type)) {
            return response;
        }

        if (!response.isSuccessful()) {
            // 返回响应异常
            throw new ResponseException(mContext.getString(R.string.http_response_error) + "，responseCode：" + response.code() + "，message：" + response.message(), response);
        }

        if (Headers.class.equals(type)) {
            return response.headers();
        }

        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }

        if (InputStream.class.equals(type)) {
            return body.byteStream();
        }

        String text;
        try {
            text = body.string();
        } catch (IOException e) {
            // 返回结果读取异常
            throw new DataException(mContext.getString(R.string.http_data_explain_error), e);
        }

        // 打印这个 Json
        HttpLog.json(text);

        final Object result;
        if (String.class.equals(type)) {
            // 如果这是一个 String 对象
            result = text;
        } else if (JSONObject.class.equals(type)) {
            try {
                // 如果这是一个 JSONObject 对象
                result = new JSONObject(text);
            } catch (JSONException e) {
                throw new DataException(mContext.getString(R.string.http_data_explain_error), e);
            }
        } else if (JSONArray.class.equals(type)) {
            try {
                // 如果这是一个 JSONArray 对象
                result = new JSONArray(text);
            } catch (JSONException e) {
                throw new DataException(mContext.getString(R.string.http_data_explain_error), e);
            }
        } else {

            try {
                if (mGson == null) {
                    // Json 容错处理
                    mGson = new GsonBuilder()
                            .registerTypeAdapterFactory(TypeAdapters.newFactory(String.class, new StringTypeAdapter()))
                            .registerTypeAdapterFactory(TypeAdapters.newFactory(boolean.class, Boolean.class, new BooleanTypeAdapter()))
                            .registerTypeAdapterFactory(TypeAdapters.newFactory(int.class, Integer.class, new IntegerTypeAdapter()))
                            .registerTypeAdapterFactory(TypeAdapters.newFactory(long.class, Long.class, new LongTypeAdapter()))
                            .registerTypeAdapterFactory(TypeAdapters.newFactory(float.class, Float.class, new FloatTypeAdapter()))
                            .registerTypeAdapterFactory(TypeAdapters.newFactory(double.class, Double.class, new DoubleTypeAdapter()))
                            .registerTypeHierarchyAdapter(List.class, new ListTypeAdapter())
                            .create();
                }
                result = mGson.fromJson(text, type);
            } catch (JsonSyntaxException e) {
                // 返回结果读取异常
                throw new DataException(mContext.getString(R.string.http_data_explain_error), e);
            }

            if (result instanceof HttpData) {
                HttpData model = (HttpData) result;
                if (model.getCode() == 0) {
                    // 代表执行成功
                    return result;
                } else if (model.getCode() == 1001) {
                    // 代表登录失效，需要重新登录
                    throw new TokenException(mContext.getString(R.string.http_account_error));
                } else {
                    // 代表执行失败
                    throw new ResultException(model.getMessage(), model);
                }
            }
        }
        return result;
    }

    @Override
    public Exception requestFail(LifecycleOwner lifecycle,  Exception e) {
        // 判断这个异常是不是自己抛的
        if (e instanceof HttpException) {
            if (e instanceof TokenException) {
                // 登录信息失效，跳转到登录页

            }
        } else {
            if (e instanceof SocketTimeoutException) {
                e = new TimeoutException(mContext.getString(R.string.http_server_out_time), e);
            } else if (e instanceof UnknownHostException) {
                NetworkInfo info = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
                // 判断网络是否连接
                if (info != null && info.isConnected()) {
                    // 有连接就是服务器的问题
                    e = new ServerException(mContext.getString(R.string.http_server_error), e);
                } else {
                    // 没有连接就是网络异常
                    e = new NetworkException(mContext.getString(R.string.http_network_error), e);
                }
            } else if (e instanceof IOException) {
                //e = new CancelException(context.getString(R.string.http_request_cancel), e);
                e = new CancelException("", e);
            } else {
                e = new HttpException(e.getMessage(), e);
            }
        }
        return e;
    }
}