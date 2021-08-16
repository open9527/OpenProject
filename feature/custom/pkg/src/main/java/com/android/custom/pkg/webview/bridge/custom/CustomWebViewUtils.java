package com.android.custom.pkg.webview.bridge.custom;

import android.os.Looper;

/**
 * @author open_9527
 * Create at 2021/8/13
 **/
public class CustomWebViewUtils {

    public final static String RMT_SCHEMA = "rmt://";
    public final static String RMT_SCHEMA_QUEUE_MESSAGE = RMT_SCHEMA + "__QUEUE_MESSAGE__/";
    public final static String RMT_RETURN_DATA = RMT_SCHEMA + "return/";

    public static String UNDERLINE_STR = "_";

    private final static String RMT_FETCH_QUEUE = RMT_RETURN_DATA + "_fetchQueue/";
    private final static String EMPTY_STR = "";
    private final static String SPLIT_MARK = "/";


    final static String CALLBACK_ID_FORMAT = "JAVA_CB_%s";
    final static String JS_HANDLE_MESSAGE_FROM_JAVA = "javascript:WebViewJavascriptBridge._handleMessageFromNative('%s');";
    final static String JS_FETCH_QUEUE_FROM_JAVA = "javascript:WebViewJavascriptBridge._fetchQueue();";


    /**
     * 获取到传递信息的body值
     * url = yy://return/_fetchQueue/[{"responseId":"JAVA_CB_2_3957",
     * "responseData":"Javascript Says Right back aka!"}]
     * @param url				url
     * @return					返回字符串，注意获取的时候判断空
     */
    public static String getDataFromReturnUrl(String url) {
        if (url.startsWith(RMT_FETCH_QUEUE)) {
            return url.replace(RMT_FETCH_QUEUE, EMPTY_STR);
        }
        String temp = url.replace(RMT_RETURN_DATA, EMPTY_STR);
        String[] functionAndData = temp.split(SPLIT_MARK);

        if (functionAndData.length >= 2) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < functionAndData.length; i++) {
                stringBuilder.append(functionAndData[i]);
            }
            return stringBuilder.toString();
        }
        return null;
    }

    // 获取到传递信息的方法
    // url = yy://return/_fetchQueue/[{"responseId":"JAVA_CB_1_360","responseData":"Javascript Says Right back aka!"}]
    public static String getFunctionFromReturnUrl(String url) {
        // temp = _fetchQueue/[{"responseId":"JAVA_CB_1_360","responseData":"Javascript Says Right back aka!"}]
        String temp = url.replace(RMT_RETURN_DATA, EMPTY_STR);
        String[] functionAndData = temp.split(SPLIT_MARK);
        if(functionAndData.length >= 1){
            // functionAndData[0] = _fetchQueue
            return functionAndData[0];
        }
        return null;
    }

    /**
     * 例子 javascript:WebViewJavascriptBridge._fetchQueue(); --> _fetchQueue
     * @param jsUrl				url
     * @return					返回字符串，注意获取的时候判断空
     */
    public static String parseFunctionName(String jsUrl){
        return jsUrl.replace("javascript:WebViewJavascriptBridge.", "").replaceAll("\\(.*\\);", "");
    }


    public static boolean isMainThread(){
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
