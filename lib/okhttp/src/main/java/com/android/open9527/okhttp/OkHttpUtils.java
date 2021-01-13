package com.android.open9527.okhttp;

import androidx.lifecycle.LifecycleOwner;

import com.android.open9527.okhttp.request.DeleteRequest;
import com.android.open9527.okhttp.request.DownloadRequest;
import com.android.open9527.okhttp.request.GetRequest;
import com.android.open9527.okhttp.request.HeadRequest;
import com.android.open9527.okhttp.request.PatchRequest;
import com.android.open9527.okhttp.request.PostRequest;
import com.android.open9527.okhttp.request.PutRequest;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public final class OkHttpUtils {

    /**
     * Get 请求
     *
     * @param lifecycleOwner      请传入 AppCompatActivity 或者 AndroidX.Fragment 子类
     *                            如需传入其他对象请参考以下两个类
     *                            {@link com.android.open9527.okhttp.lifecycle.ActivityLifecycle}
     *                            {@link com.android.open9527.okhttp.lifecycle.ApplicationLifecycle}
     */
    public static GetRequest get(LifecycleOwner lifecycleOwner) {
        return new GetRequest(lifecycleOwner);
    }

    /**
     * Post 请求
     *
     * @param lifecycleOwner      请传入 AppCompatActivity 或者 AndroidX.Fragment 子类
     *                            如需传入其他对象请参考以下两个类
     *                            {@link com.android.open9527.okhttp.lifecycle.ActivityLifecycle}
     *                            {@link com.android.open9527.okhttp.lifecycle.ApplicationLifecycle}
     */
    public static PostRequest post(LifecycleOwner lifecycleOwner) {
        return new PostRequest(lifecycleOwner);
    }

    /**
     * Head 请求
     *
     * @param lifecycleOwner      请传入 AppCompatActivity 或者 AndroidX.Fragment 子类
     *                            如需传入其他对象请参考以下两个类
     *                            {@link com.android.open9527.okhttp.lifecycle.ActivityLifecycle}
     *                            {@link com.android.open9527.okhttp.lifecycle.ApplicationLifecycle}
     */
    public static HeadRequest head(LifecycleOwner lifecycleOwner) {
        return new HeadRequest(lifecycleOwner);
    }

    /**
     * Delete 请求
     *
     * @param lifecycleOwner      请传入 AppCompatActivity 或者 AndroidX.Fragment 子类
     *                            如需传入其他对象请参考以下两个类
     *                            {@link com.android.open9527.okhttp.lifecycle.ActivityLifecycle}
     *                            {@link com.android.open9527.okhttp.lifecycle.ApplicationLifecycle}
     */
    public static DeleteRequest delete(LifecycleOwner lifecycleOwner) {
        return new DeleteRequest(lifecycleOwner);
    }

    /**
     * Put 请求
     *
     * @param lifecycleOwner      请传入 AppCompatActivity 或者 AndroidX.Fragment 子类
     *                            如需传入其他对象请参考以下两个类
     *                            {@link com.android.open9527.okhttp.lifecycle.ActivityLifecycle}
     *                            {@link com.android.open9527.okhttp.lifecycle.ApplicationLifecycle}
     */
    public static PutRequest put(LifecycleOwner lifecycleOwner) {
        return new PutRequest(lifecycleOwner);
    }

    /**
     * Patch 请求
     *
     * @param lifecycleOwner      请传入 AppCompatActivity 或者 AndroidX.Fragment 子类
     *                            如需传入其他对象请参考以下两个类
     *                            {@link com.android.open9527.okhttp.lifecycle.ActivityLifecycle}
     *                            {@link com.android.open9527.okhttp.lifecycle.ApplicationLifecycle}
     */
    public static PatchRequest patch(LifecycleOwner lifecycleOwner) {
        return new PatchRequest(lifecycleOwner);
    }

    /**
     * 下载请求
     *
     * @param lifecycleOwner      请传入 AppCompatActivity 或者 AndroidX.Fragment 子类
     *                            如需传入其他对象请参考以下两个类
     *                            {@link com.android.open9527.okhttp.lifecycle.ActivityLifecycle}
     *                            {@link com.android.open9527.okhttp.lifecycle.ApplicationLifecycle}
     */
    public static DownloadRequest download(LifecycleOwner lifecycleOwner) {
        return new DownloadRequest(lifecycleOwner);
    }

    /**
     * 取消请求
     */
    public static void cancel(LifecycleOwner lifecycleOwner) {
        cancel(String.valueOf(lifecycleOwner));
    }

    /**
     * 根据 TAG 取消请求任务
     */
    public static void cancel(Object tag) {
        if (tag == null) {
            return;
        }

        OkHttpClient client = HttpConfig.getInstance().getClient();

        // 清除排队等候的任务
        for (Call call : client.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }

        // 清除正在执行的任务
        for (Call call : client.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    /**
     * 清除所有请求任务
     */
    public static void cancel() {
        OkHttpClient client = HttpConfig.getInstance().getClient();

        // 清除排队等候的任务
        for (Call call : client.dispatcher().queuedCalls()) {
            call.cancel();
        }

        // 清除正在执行的任务
        for (Call call : client.dispatcher().runningCalls()) {
            call.cancel();
        }
    }
}
