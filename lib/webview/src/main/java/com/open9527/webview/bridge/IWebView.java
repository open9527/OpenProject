package com.open9527.webview.bridge;

public interface IWebView {


    void send(String data);

    void send(String data, Callback responseCallback);

    void callHandler(String handlerName, String data, Callback responseCallback);

    void registerHandler(String handlerName, BridgeHandler handler);
}
