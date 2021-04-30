package com.open9527.webview.bridge;

public class DefaultHandler implements BridgeHandler{
	@Override
	public void handler(String data, Callback function) {
		if(function != null){
			function.onCallback("DefaultHandler response data");
		}
	}

}
