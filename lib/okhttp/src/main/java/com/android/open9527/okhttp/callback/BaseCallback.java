package com.android.open9527.okhttp.callback;

import androidx.lifecycle.LifecycleOwner;

import com.android.open9527.okhttp.HttpConfig;
import com.android.open9527.okhttp.HttpLog;
import com.android.open9527.okhttp.HttpUtils;
import com.android.open9527.okhttp.lifecycle.HttpLifecycleManager;
import com.android.open9527.okhttp.model.CallProxy;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public abstract class BaseCallback implements Callback {

    /** 请求任务对象 */
    private CallProxy mCall;

    /** 当前重试次数 */
    private int mRetryCount;

    /** 生命周期管理 */
    private LifecycleOwner mLifecycleOwner;

    BaseCallback(LifecycleOwner lifecycleOwner, CallProxy call) {
        mCall = call;
        mLifecycleOwner = lifecycleOwner;
        HttpLifecycleManager.bind(lifecycleOwner);
    }

    CallProxy getCall() {
        return mCall;
    }

    LifecycleOwner getLifecycleOwner() {
        return mLifecycleOwner;
    }

    @Override
    public void onResponse(Call call, Response response) {
        try {
            // 收到响应
            onResponse(response);
        } catch (Exception e) {
            // 回调失败
            onFailure(e);
        } finally {
            // 关闭响应
            response.close();
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {
        // 服务器请求超时重试
        if (e instanceof SocketTimeoutException && mRetryCount < HttpConfig.getInstance().getRetryCount()) {
            // 设置延迟 N 秒后重试该请求
            HttpUtils.postDelayed(() -> {

                // 前提是宿主还没有被销毁
                if (!HttpLifecycleManager.isLifecycleActive(mLifecycleOwner)) {
                    HttpLog.print("宿主已被销毁，无法对请求进行重试");
                    return;
                }

                mRetryCount++;
                Call newCall = call.clone();
                mCall.setCall(newCall);
                newCall.enqueue(BaseCallback.this);
                HttpLog.print("请求超时，正在延迟重试，重试次数：" + mRetryCount + "/" + HttpConfig.getInstance().getRetryCount());

            }, HttpConfig.getInstance().getRetryTime());

            return;
        }
        onFailure(e);
    }

    protected abstract void onResponse(Response response) throws Exception;

    protected abstract void onFailure(Exception e);
}
