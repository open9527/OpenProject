package com.android.custom.pkg.webview.bridge.custom.vo;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;


/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class WebMsgJsonDeserializer implements JsonDeserializer<WebMessageVo> {
    private static final String TAG = "WebMsgJsonDeserializer";

    @Override
    public WebMessageVo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement handlerNameElement = json.getAsJsonObject().get("handlerName");
        if (handlerNameElement == null) {
            return new WebMessageVo();
        }
        Gson gson = ApiUtils.buildGsonExpectAdapter(WebMessageVo.class);
        String handlerName = handlerNameElement.getAsString();
//        if (StringUtils.equals(handlerName, WebMessageVo.HANDLER_NAME_UPDATE_WEB_VIEW_HEIGHT)) {
//            LogUtils.i(TAG, "json:" + json);
////            WebDataVo webDataVo = gson.fromJson(json, WebDataVo.class);
////            LogUtils.i(TAG, "webDataVo:" + GsonUtils.toJson(webDataVo));
//            return gson.fromJson(json, WebHeightVo.class);
//        }
        return gson.fromJson(json, WebMessageVo.class);
    }

}
