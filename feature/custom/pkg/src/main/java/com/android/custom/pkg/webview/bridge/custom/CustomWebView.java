package com.android.custom.pkg.webview.bridge.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.custom.pkg.webview.bridge.custom.vo.WebMessageVo;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author open_9527
 * Create at 2021/8/9
 **/
public class CustomWebView extends WebView implements WebViewJavascriptBridge {
    public static final String TAG = "CustomWebView";

    private long uniqueId = 0;

    private Map<String, Callback> responseCallbacks = new HashMap<>();
    private Map<String, BridgeHandler> messageHandlers = new HashMap<>();
    private BridgeHandler defaultHandler = new DefaultHandler();
    private List<WebMessageVo> startupMessage = new ArrayList<>();


    public CustomWebView(@NonNull Context context) {
        this(context, null, 0, 0);
    }

    public CustomWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public CustomWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initWebViewSetting(getSettings());
    }


    @SuppressLint("SetJavaScriptEnabled")
    void initWebViewSetting(WebSettings webSettings) {
        // 不显示滚动条
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);

        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        //是否允许访问文件
        webSettings.setAllowFileAccess(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 支持内容重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(12);

        //页面缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        //不显示缩放按钮
        webSettings.setDisplayZoomControls(false);

        //设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");

        // 自适应屏幕
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
//        webSettings.setLoadWithOverviewMode(true);
    }


    void loadUrl(WebView webView, String jsUrl, Callback callback) {
        webView.loadUrl(jsUrl);
        // 添加至 Map<String, CallBackFunction>
        responseCallbacks.put(CustomWebViewUtils.parseFunctionName(jsUrl), callback);
    }


    /**
     * 获取到CallBackFunction data执行调用并且从数据集移除
     */
    void handlerReturnData(String url) {
        String json = CustomWebViewUtils.getDataFromReturnUrl(url);
        String functionName = CustomWebViewUtils.getFunctionFromReturnUrl(url);
        LogUtils.i(TAG, "handlerReturnData: " + json);
        LogUtils.i(TAG, "handlerReturnData: " + functionName);

        if (functionName != null) {
            Callback callback = responseCallbacks.get(functionName);
            String data = CustomWebViewUtils.getDataFromReturnUrl(url);
            if (callback != null) {
                callback.onCallBack(data);
                responseCallbacks.remove(functionName);
            }
        }
    }


    /**
     * 刷新消息队列
     */
    void flushMessageQueue(WebView webView) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            loadUrl(webView, CustomWebViewUtils.JS_FETCH_QUEUE_FROM_JAVA, data -> {
                LogUtils.i(TAG, "flushMessageQueue data: " + data);
                List<WebMessageVo> messageList = GsonUtils.fromJson(data, new TypeToken<List<WebMessageVo>>() {
                }.getType());
                LogUtils.i(TAG, "flushMessageQueue messageList: " + GsonUtils.toJson(messageList));

                if (CollectionUtils.isNotEmpty(messageList)) {
                    for (WebMessageVo webMessageVo : messageList) {
                        if (!TextUtils.isEmpty(webMessageVo.getResponseId())) {
                            Callback function = responseCallbacks.get(webMessageVo.getResponseId());
                            String responseData = webMessageVo.getResponseData();
                            if (function != null) {
                                function.onCallBack(responseData);
                                responseCallbacks.remove(webMessageVo.getResponseId());
                            }
                        } else {
                            Callback responseFunction = null;
                            // if had callbackId 如果有回调Id
                            final String callbackId = webMessageVo.getCallbackId();
                            if (!TextUtils.isEmpty(callbackId)) {
                                responseFunction = data12 -> {
                                    WebMessageVo responseMsg = new WebMessageVo();
                                    responseMsg.setResponseId(callbackId);
                                    responseMsg.setResponseData(data12);
                                    queueMessage(responseMsg);
                                };
                            } else {
                                responseFunction = data1 -> {
                                    // do nothing
                                };
                            }
                            // BridgeHandler执行
                            BridgeHandler handler;
                            if (!TextUtils.isEmpty(webMessageVo.getHandlerName())) {
                                handler = messageHandlers.get(webMessageVo.getHandlerName());
                            } else {
                                handler = defaultHandler;
                            }
                            if (handler != null) {
                                handler.handler(GsonUtils.toJson(webMessageVo.getData()), responseFunction);
                            }
                        }


                    }
                }

            });
        }
    }

    /**
     * 分发message 必须在主线程才分发成功
     */
    void dispatchMessage(WebMessageVo webMessageVo) {
        String webMessageJson = GsonUtils.toJson(webMessageVo);
        //escape special characters for json string  为json字符串转义特殊字符
        webMessageJson = webMessageJson.replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2");
        webMessageJson = webMessageJson.replaceAll("(?<=[^\\\\])(\")", "\\\\\"");
        webMessageJson = webMessageJson.replaceAll("(?<=[^\\\\])(\')", "\\\\\'");
        webMessageJson = webMessageJson.replaceAll("%7B", URLEncoder.encode("%7B"));
        webMessageJson = webMessageJson.replaceAll("%7D", URLEncoder.encode("%7D"));
        webMessageJson = webMessageJson.replaceAll("%22", URLEncoder.encode("%22"));
        String javascriptCommand = String.format(CustomWebViewUtils.JS_HANDLE_MESSAGE_FROM_JAVA, webMessageJson);
        CustomWebView.super.evaluateJavascript(javascriptCommand,null);
        // 必须要找主线程才会将数据传递出去 --- 划重点
//        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
//            LogUtils.i(TAG, "dispatchMessage loadUrl:" + javascriptCommand);
//            this.loadUrl(javascriptCommand);
//
//        }
    }

    /**
     * 保存message到消息队列
     *
     * @param handlerName      handlerName
     * @param data             data
     * @param responseCallback CallBackFunction
     */
    private void doSend(String handlerName, String data, Callback responseCallback) {
        WebMessageVo webMessageVo = new WebMessageVo();
        if (!TextUtils.isEmpty(data)) {
            webMessageVo.setData(data);
        }
        if (responseCallback != null) {
            String callbackStr = String.format(CustomWebViewUtils.CALLBACK_ID_FORMAT, ++uniqueId + (CustomWebViewUtils.UNDERLINE_STR + SystemClock.currentThreadTimeMillis()));
            responseCallbacks.put(callbackStr, responseCallback);
            webMessageVo.setCallbackId(callbackStr);
        }
        if (!TextUtils.isEmpty(handlerName)) {
            webMessageVo.setHandlerName(handlerName);
        }
        queueMessage(webMessageVo);
    }


    //list<message> != null 添加到消息集合否则分发消息
    private void queueMessage(WebMessageVo webMessageVo) {
        if (startupMessage != null) {
            startupMessage.add(webMessageVo);
        } else {
            dispatchMessage(webMessageVo);
        }
    }

    /**
     * register handler,so that javascript can call it
     * 注册处理程序,以便javascript调用它
     *
     * @param handlerName handlerName
     * @param handler     BridgeHandler
     */
    @Override
    public void registerHandler(String handlerName, BridgeHandler handler) {
        if (handler != null) {
            // 添加至 Map<String, BridgeHandler>
            messageHandlers.put(handlerName, handler);
        }
    }

    /**
     * 解绑注册js操作，一般可以在onDestory中处理
     *
     * @param handlerName 方法name
     */
    @Override
    public void unregisterHandler(String handlerName) {
        if (handlerName != null) {
            messageHandlers.remove(handlerName);
        }
    }

    /**
     * call javascript registered handler
     * 调用javascript处理程序注册
     *
     * @param handlerName handlerName
     */
    @Override
    public void callHandler(String handlerName) {
        callHandler(handlerName, null);
    }

    /**
     * call javascript registered handler
     * 调用javascript处理程序注册
     *
     * @param handlerName handlerName
     * @param data        data
     */
    @Override
    public void callHandler(String handlerName, String data) {
        callHandler(handlerName, data, null);
    }

    /**
     * call javascript registered handler
     * 调用javascript处理程序注册
     *
     * @param handlerName handlerName
     * @param data        data
     * @param callBack    CallBackFunction
     */
    @Override
    public void callHandler(final String handlerName, final String data, final Callback callBack) {
        if (CustomWebViewUtils.isMainThread()) {
            doSend(handlerName, data, callBack);
        } else {
            if (getHandler() == null) {
                return;
            }
            getHandler().post(() -> doSend(handlerName, data, callBack));
        }
    }
}
