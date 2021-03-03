package com.android.feature.webview.pkg.browser.bridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.android.feature.webview.pkg.browser.Callback;
import com.android.feature.webview.pkg.gson.ApiUtils;
import com.android.feature.webview.pkg.gson.MessageVo;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.open9527.webview.NestedScrollWebView;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author open_9527
 * Create at 2021/3/1
 * <p>
 * eg:基于原生 WebView 封装
 **/


public final class BridgeBrowserView extends NestedScrollWebView
        implements LifecycleEventObserver {

    private static final String TAG = "BridgeBrowserView";


    static {
        // WebView 调试模式开关
        WebView.setWebContentsDebuggingEnabled(true);
    }

    public BridgeBrowserView(Context context) {
        this(context, null);
    }

    public BridgeBrowserView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.webViewStyle);
    }

    public BridgeBrowserView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(getFixedContext(context), attrs, defStyleAttr, 0);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public BridgeBrowserView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        WebSettings settings = getSettings();
        // 允许文件访问
        settings.setAllowFileAccess(true);
        // 允许网页定位
        settings.setGeolocationEnabled(true);
        // 允许保存密码
        //settings.setSavePassword(true);
        // 开启 JavaScript
        settings.setJavaScriptEnabled(true);
        // 允许网页弹对话框
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 加快网页加载完成的速度，等页面完成再加载图片
        settings.setLoadsImagesAutomatically(true);
        // 本地 DOM 存储（解决加载某些网页出现白板现象）
        settings.setDomStorageEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 解决 Android 5.0 上 WebView 默认不允许加载 Http 与 Https 混合内容
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 不显示滚动条
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);

        // 支持自适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        //支持屏幕缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        settings.setDisplayZoomControls(false);

        //设置编码格式
        settings.setDefaultTextEncodingName("utf-8");
    }

    /**
     * 修复原生 WebView 和 AndroidX 在 Android 5.x 上面崩溃的问题
     * <p>
     * doc：https://stackoverflow.com/questions/41025200/android-view-inflateexception-error-inflating-class-android-webkit-webview
     */
    private static Context getFixedContext(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // 为什么不用 ContextImpl，因为使用 ContextImpl 获取不到 Activity 对象，而 ContextWrapper 可以
            // 这种写法返回的 Context 是 ContextImpl，而不是 Activity 或者 ContextWrapper
            // return context.createConfigurationContext(new Configuration());
            // 如果使用 ContextWrapper 还是导致崩溃，因为 Resources 对象冲突了
            // return new ContextWrapper(context);
            // 如果使用 ContextThemeWrapper 就没有问题，因为它重写了 getResources 方法，返回的是一个新的 Resources 对象
            return new ContextThemeWrapper(context, context.getTheme());
        }
        return context;
    }

    /**
     * 获取当前的 url
     *
     * @return 返回原始的 url，因为有些url是被WebView解码过的
     */
    @Override
    public String getUrl() {
        String originalUrl = super.getOriginalUrl();
        // 避免开始时同时加载两个地址而导致的崩溃
        if (originalUrl != null) {
            return originalUrl;
        }
        return super.getUrl();
    }

    /**
     * 设置 WebView 生命管控（自动回调生命周期方法）
     */
    public void setLifecycleOwner(LifecycleOwner owner) {
        owner.getLifecycle().addObserver(this);
    }

    /**
     * {@link LifecycleEventObserver}
     */

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        switch (event) {
            case ON_RESUME:
                onResume();
                resumeTimers();
                break;
            case ON_PAUSE:
                onPause();
                pauseTimers();
                break;
            case ON_DESTROY:
                onDestroy();
                break;
            default:
                break;
        }
    }

    /**
     * 销毁 WebView
     */
    public void onDestroy() {
        // 停止加载网页
        stopLoading();
        // 清除历史记录
        clearHistory();
        // 取消监听引用
        setBrowserChromeClient(null);
        setBrowserViewClient(null);
        // 移除WebView所有的View对象
        removeAllViews();
        // 销毁此的WebView的内部状态
        destroy();
    }

    public void setBrowserViewClient(BridgeWebViewClient client) {
        super.setWebViewClient(client);
    }

    public void setBrowserChromeClient(BridgeWebChromeClient client) {
        super.setWebChromeClient(client);
    }


    /**
     * 获取到CallBackFunction data执行调用并且从数据集移除
     */
    void handlerReturnData(String url) {
        String data = BridgeUtil.getDataFromReturnUrl(url);
        LogUtils.i(TAG, "handlerReturnData: " + data);

        Map<Type, Object> adapterSetting = ApiUtils.defaultAdapterSetting();
        Gson gson = ApiUtils.buildGson(adapterSetting);

        List<MessageVo> messageList = gson.fromJson(data, new TypeToken<List<MessageVo>>() {
        }.getType());
        LogUtils.i(TAG, "handlerReturnData messageList: " + GsonUtils.toJson(messageList));

    }

    void loadUrl(WebView webView, String jsUrl, Callback returnCallback) {
        webView.loadUrl(jsUrl);

    }

    /**
     * 刷新消息队列
     */
    void flushMessageQueue(WebView webView) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            loadUrl(webView, BridgeUtil.JS_FETCH_QUEUE_FROM_JAVA, new Callback() {
                @Override
                public void onCallback(String data) {
                    List<MessageVo> messageList = GsonUtils.fromJson(data, new TypeToken<List<MessageVo>>() {
                    }.getType());
                    LogUtils.i(TAG, "flushMessageQueue messageList: " + GsonUtils.toJson(messageList));
                }
            });
        }
    }
}