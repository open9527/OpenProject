package com.android.custom.pkg.webview.bridge.custom;


public interface WebViewJavascriptBridge {
//
//    void send(String data);
//
//    void send(String data, Callback responseCallback);


    void callHandler(String handlerName);
    void callHandler(String handlerName, String data);
    void callHandler(String handlerName, String data, Callback callBack);
    void unregisterHandler(String handlerName);
    void registerHandler(String handlerName, BridgeHandler handler);
}
