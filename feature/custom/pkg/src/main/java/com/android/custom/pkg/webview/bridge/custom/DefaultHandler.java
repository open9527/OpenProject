package com.android.custom.pkg.webview.bridge.custom;

public class DefaultHandler implements BridgeHandler{
	String TAG = "DefaultHandler";
	@Override
	public void handler(String data, Callback function) {
		if(function != null){
			function.onCallBack("DefaultHandler response data");
		}
	}

}
