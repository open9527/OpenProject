package com.android.feature.webview.pkg.gson;

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
public class WebMsgJsonDeserializer implements JsonDeserializer<MessageVo> {

    @Override
    public MessageVo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement contentTypeElement = json.getAsJsonObject().get("handlerName");
        if (contentTypeElement == null) {
            return new MessageVo();
        }

        Gson gson = ApiUtils.buildGsonExpectAdapter(MessageVo.class);

        String contentType = contentTypeElement.getAsString();
        if (StringUtils.equals(contentType, MessageVo.UPDATE_WEB_VIEW_HEIGHT)) {
            return gson.fromJson(json, WebHeightVo.class);

        } else {
            return gson.fromJson(json, MessageVo.class);
        }
    }

}
