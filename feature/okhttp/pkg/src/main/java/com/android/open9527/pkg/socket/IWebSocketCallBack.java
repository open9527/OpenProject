package com.android.open9527.pkg.socket;

public interface IWebSocketCallBack {

    void onOpen();

    void onMessage(String msg);

    void onHeart();

    void reconnectionWebSocket();

}
