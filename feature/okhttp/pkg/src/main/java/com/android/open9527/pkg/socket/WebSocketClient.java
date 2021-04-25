package com.android.open9527.pkg.socket;


import androidx.annotation.NonNull;

import com.android.open9527.common.net.okhttp.OkHttpClientUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketClient {
    private static final String TAG = "WebSocketClient";

    private static final int NORMAL_CLOSURE_STATUS = 1000;

    private static OkHttpClient sClient;
    private static WebSocket sWebSocket;
    private static final long READ_TIMEOUT = 5;//读取超时
    private static final long WRITE_TIMEOUT = 5;//写入超时
    private static final long CONNECT_TIMEOUT = 30;//连接超时
    private static final long INTERVAL_TIME = 10;//心跳时间间隔

    private static final String sWbSocketUrl = "ws://echo.websocket.org";

    private static IWebSocketCallBack mIWebSocketCallBack;

    public static synchronized void startRequest(@NonNull IWebSocketCallBack iWebSocketCallBack) {
        mIWebSocketCallBack = iWebSocketCallBack;
        if (sClient == null) {
            LogUtils.i(TAG, "sClient:  create");
            sClient=OkHttpClientUtils.getInstance().newBuilder()
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                    .retryOnConnectionFailure(true)//断线重连
//                    .pingInterval(1, TimeUnit.SECONDS)//心跳时间间隔
                    .build();
        }
        if (sWebSocket == null) {
            LogUtils.i(TAG, "sWebSocket:  create");
            Request request = new Request.Builder().url(sWbSocketUrl).build();
            LiveWebSocketListener listener = new LiveWebSocketListener();
            sWebSocket = sClient.newWebSocket(request, listener);
        }
    }

    private static void sendMessage(WebSocket webSocket, String msg) {
        LogUtils.i(TAG, "客户端发送消息:" + msg);
        webSocket.send(msg);
    }

    public static void sendMessage(String msg) {
        WebSocket webSocket;
        synchronized (WebSocketClient.class) {
            webSocket = sWebSocket;
        }
        if (webSocket != null) {
            sendMessage(webSocket, msg);
        }
    }

    public static synchronized void closeWebSocket() {
        if (sWebSocket != null) {
            sWebSocket.close(NORMAL_CLOSURE_STATUS, "close !");
            sWebSocket.cancel();
            sWebSocket = null;
        }

    }

    public static synchronized void destroy() {
        LogUtils.i(TAG, "destroy");
        if (sClient != null) {
            sClient.dispatcher().executorService().shutdown();
            sClient = null;
        }
    }

    public static void reconnection() {
        LogUtils.i(TAG, "reconnection");
        WebSocket webSocket;
        synchronized (WebSocketClient.class) {
            webSocket = sWebSocket;
        }
        if (webSocket != null) {
            webSocket.cancel();
            Request request = new Request.Builder()
                    .url(sWbSocketUrl)
                    .build();
            sWebSocket = sClient.newWebSocket(request, new LiveWebSocketListener());
        }
    }


    public static class LiveWebSocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            LogUtils.i(TAG, "连接成功!--->" + response.toString());
            if (mIWebSocketCallBack != null) {
                mIWebSocketCallBack.onOpen();
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            LogUtils.i(TAG, "客户端收到消息(text):" + text);
            if (StringUtils.isEmpty(text)) {
                return;
            }
            if (mIWebSocketCallBack != null) {
                if (StringUtils.equals(text, "pong")) {
                    mIWebSocketCallBack.onHeart();
                } else {
                    mIWebSocketCallBack.onMessage(text);
                }

            }
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
            LogUtils.i(TAG, "客户端收到消息(bytes):" + bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            LogUtils.i(TAG, "onClosing:" + code + "--" + reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            LogUtils.i(TAG, "onClosed:" + code + "--" + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            //Socket closed
            String msg = t.getMessage();
            assert msg != null;
            if (!msg.contains("closed")) {
                if (mIWebSocketCallBack != null) {
                    mIWebSocketCallBack.reconnectionWebSocket();
                }
            }
            try {
                LogUtils.i(TAG, "onFailure:" + t.getMessage() + "-->response->" + response.toString());
            } catch (Exception e) {
                LogUtils.i(TAG, "onFailure:" + t.getMessage() + "-->");
            }
        }
    }
}
