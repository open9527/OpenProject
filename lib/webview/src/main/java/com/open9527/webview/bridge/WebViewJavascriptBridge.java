package com.open9527.webview.bridge;


public interface WebViewJavascriptBridge {

    void send(String data);

    void send(String data, Callback responseCallback);

}
