package com.open9527.webview.bridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.reflect.TypeToken;
import com.open9527.webview.bridge.impl.WebAgreementImpl;
import com.open9527.webview.bridge.impl.vo.MessageVo;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class X5BridgeWebView extends WebView implements WebViewJavascriptBridge, IWebView {
    private final static String TAG = "X5BridgeWebView";


    private Map<String, Callback> responseCallbacks = new HashMap<>();
    private Map<String, BridgeHandler> messageHandlers = new HashMap<>();
    private BridgeHandler defaultHandler = new DefaultHandler();
    private List<MessageVo> startupMessage = new ArrayList<>();
    private WebAgreementImpl mWebAgreementImpl;

    public List<MessageVo> getStartupMessage() {
        return startupMessage;
    }

    public void setStartupMessage(List<MessageVo> startupMessage) {
        this.startupMessage = startupMessage;

    }

    private long uniqueId = 0;


    public X5BridgeWebView(Context context) {
        this(context, null, 0);
    }

    public X5BridgeWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public X5BridgeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        View view = getView();
        view.setVerticalScrollBarEnabled(false);
        view.setHorizontalScrollBarEnabled(false);
        view.setOverScrollMode(View.OVER_SCROLL_NEVER);
        initWebSettings(this);
        this.setWebViewClient(new X5BridgeWebViewClient(this));
        mWebAgreementImpl = WebAgreementImpl.newInstance(this);
    }

    public WebAgreementImpl getWebAgreementImpl() {
        return mWebAgreementImpl;
    }

    /**
     * @param handler default handler,handle messages send by js without assigned handler name,
     *                if js message has handler name, it will be handled by named handlers registered by native
     */
    public void setDefaultHandler(BridgeHandler handler) {
        this.defaultHandler = handler;
    }

    /**
     * 获取到CallBackFunction data执行调用并且从数据集移除
     */
    protected void handlerReturnData(String url) {
        String functionName = BridgeUtil.getFunctionFromReturnUrl(url);
        Callback callback = responseCallbacks.get(functionName);
        String data = BridgeUtil.getDataFromReturnUrl(url);
        if (callback != null) {
            callback.onCallback(data);
            responseCallbacks.remove(functionName);
        }
    }

    @Override
    public void send(String data) {
        send(data, null);
    }

    @Override
    public void send(String data, Callback responseCallback) {
        doSend(null, data, responseCallback);
    }


    /**
     * 保存message到消息队列
     *
     * @param handlerName      handlerName
     * @param data             data
     * @param responseCallback CallBackFunction
     */
    private void doSend(String handlerName, String data, Callback responseCallback) {
        MessageVo messageVo = new MessageVo();
        if (!TextUtils.isEmpty(data)) {
            messageVo.setData(data);
        }
        if (responseCallback != null) {
            String callbackStr = String.format(BridgeUtil.CALLBACK_ID_FORMAT, ++uniqueId + (BridgeUtil.UNDERLINE_STR + SystemClock.currentThreadTimeMillis()));
            responseCallbacks.put(callbackStr, responseCallback);
            messageVo.setCallbackId(callbackStr);
        }
        if (!TextUtils.isEmpty(handlerName)) {
            messageVo.setHandlerName(handlerName);
        }
        queueMessage(messageVo);
    }

    /**
     * list<message> != null 添加到消息集合否则分发消息
     *
     * @param messageVo messageVo
     */
    private void queueMessage(MessageVo messageVo) {
        if (startupMessage != null) {
            startupMessage.add(messageVo);
        } else {
            dispatchMessage(messageVo);
        }
    }

    /**
     * 分发message 必须在主线程才分发成功
     *
     * @param messageVo Message
     */
    public void dispatchMessage(MessageVo messageVo) {
        String messageJson = GsonUtils.toJson(messageVo);
        LogUtils.i(TAG, "dispatchMessage messageJson:" + messageJson);
        //escape special characters for json string  为json字符串转义特殊字符
        messageJson = messageJson.replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2");
        messageJson = messageJson.replaceAll("(?<=[^\\\\])(\")", "\\\\\"");
        messageJson = messageJson.replaceAll("(?<=[^\\\\])(\')", "\\\\\'");
        messageJson = messageJson.replaceAll("%7B", URLEncoder.encode("%7B"));
        messageJson = messageJson.replaceAll("%7D", URLEncoder.encode("%7D"));
        messageJson = messageJson.replaceAll("%22", URLEncoder.encode("%22"));

        LogUtils.i(TAG, "dispatchMessage messageJson replace:" + messageJson);

        String javascriptCommand = String.format(BridgeUtil.JS_HANDLE_MESSAGE_FROM_JAVA, messageJson);
        // 必须要找主线程才会将数据传递出去 --- 划重点
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            LogUtils.i(TAG, "dispatchMessage javascriptCommand:" + javascriptCommand);
            this.loadUrl(javascriptCommand);
        }
    }

    /**
     * 刷新消息队列
     */
    void flushMessageQueue() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            loadUrl(BridgeUtil.JS_FETCH_QUEUE_FROM_JAVA, data -> {
                // deserializeMessage 反序列化消息
                LogUtils.i(TAG, "flushMessageQueue  data: " + data);
//                Map<Type, Object> adapterSetting = ApiUtils.defaultAdapterSetting();
//                Gson gson = ApiUtils.buildGson(adapterSetting);
                List<MessageVo> messageList = GsonUtils.fromJson(data, new TypeToken<List<MessageVo>>() {
                }.getType());
                if (messageList == null || messageList.size() == 0) {
                    return;
                }
                LogUtils.i(TAG, "flushMessageQueue messageList: " + GsonUtils.toJson(messageList));
                for (int i = 0; i < messageList.size(); i++) {
                    MessageVo messageVo = messageList.get(i);
                    String responseId = messageVo.getResponseId();
                    // 是否是response  CallBackFunction
                    if (!TextUtils.isEmpty(responseId)) {
                        Callback function = responseCallbacks.get(responseId);
                        if (function != null) {
                            String responseData = messageVo.getResponseData();
                            function.onCallback(responseData);
                            responseCallbacks.remove(responseId);
                        }

                    } else {
                        Callback responseFunction = null;
                        // if had callbackId 如果有回调Id
                        final String callbackId = messageVo.getCallbackId();
                        if (!TextUtils.isEmpty(callbackId)) {
                            responseFunction = new Callback() {
                                @Override
                                public void onCallback(String data) {
                                    MessageVo responseMsg = new MessageVo();
                                    responseMsg.setResponseId(callbackId);
                                    responseMsg.setResponseData(data);
                                    queueMessage(responseMsg);
                                }
                            };
                        } else {
                            responseFunction = new Callback() {
                                @Override
                                public void onCallback(String data) {
                                    // do nothing
                                }
                            };
                        }
                        // BridgeHandler执行
                        BridgeHandler handler;
                        if (!TextUtils.isEmpty(messageVo.getHandlerName())) {
                            handler = messageHandlers.get(messageVo.getHandlerName());
                        } else {
                            handler = defaultHandler;
                        }
                        if (handler != null) {
                            handler.handler(messageVo.getData() != null ? GsonUtils.toJson(messageVo.getData()) : "", responseFunction);
                        }
                    }
                }
            });
        }
    }


    public void loadUrl(String jsUrl, Callback returnCallback) {
        this.loadUrl(jsUrl);
        LogUtils.i(TAG, "loadUrl jsUrl:" + jsUrl);
        // 添加至 Map<String, CallBackFunction>
        responseCallbacks.put(BridgeUtil.parseFunctionName(jsUrl), returnCallback);
    }

    /**
     * register handler,so that javascript can call it
     * 注册处理程序,以便javascript调用它
     *
     * @param handlerName handlerName
     * @param handler     BridgeHandler
     */
    public void registerHandler(String handlerName, BridgeHandler handler) {
        if (handler != null) {
            // 添加至 Map<String, BridgeHandler>
            messageHandlers.put(handlerName, handler);
        }
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
    public void callHandler(String handlerName, String data, Callback callBack) {
        doSend(handlerName, data, callBack);
    }

    public boolean iSgoBack() {
        if (canGoBack()) {
            goBack();
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void scrollBy(int x, int y) {
        // 把滑动交给它的子view
        getView().scrollBy(x, y);
        if (!isSlide) {
            isSlide = true;
        }
    }

    private float mRawX = 0;
    private float mRawY = 0;
    private boolean isSlide;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mRawX = event.getRawX();
                mRawY = event.getRawY();
                // 处理输入的按下事件
                break;
            case MotionEvent.ACTION_UP:
                // 处理输入的离开事件
                isSlide = false;
                break;
        }
        return super.onTouchEvent(event);
    }

    public float getRawX() {
        return mRawX;
    }

    public float getRawY() {
        return mRawY;
    }

    public boolean isSlide() {
        return isSlide;
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initWebSettings(X5BridgeWebView mBridgeWebView) {
        WebView.setWebContentsDebuggingEnabled(true);
        WebSettings settings = mBridgeWebView.getSettings();
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


        // 不显示滚动条
        mBridgeWebView.setVerticalScrollBarEnabled(false);
        mBridgeWebView.setHorizontalScrollBarEnabled(false);

        //支持屏幕缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        settings.setDisplayZoomControls(false);

        //设置编码格式
        settings.setDefaultTextEncodingName("utf-8");

    }

    public void setUserAgentString(String suffix){
        getSettings().setUserAgentString(BridgeUtil.USER_AGENT + suffix);
    }
}
