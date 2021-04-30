package com.open9527.webview.bridge.impl.vo;

import android.util.ArrayMap;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Map;


/**
 * @author open_9527
 * Create at 2021/2/23
 **/

public class ApiUtils {

    private static final String DATE_FORMAT = ("yyyy-MM-dd HH:mm:ss");

    @NonNull
    public static Map<Type, Object> defaultAdapterSetting() {
        Map<Type, Object> setting = new ArrayMap<>();
        setting.put(MessageVo.class, new WebMsgJsonDeserializer());
        return setting;
    }

    @NonNull
    public static Gson buildGson(Map<Type, Object> typeAdapterSetting) {
        GsonBuilder builder = new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DATE_FORMAT);
        for (Type type : typeAdapterSetting.keySet()) {
            builder.registerTypeAdapter(type, typeAdapterSetting.get(type));
        }

        return builder.create();
    }

    @NonNull
    public static Gson buildGson() {
        return buildGson(ApiUtils.defaultAdapterSetting());
    }

    @NonNull
    public static Gson buildGsonExpectAdapter(Type... types) {
        Map<Type, Object> typeAdapterSetting = ApiUtils.defaultAdapterSetting();
        for (Type type : types) {
            typeAdapterSetting.remove(type);
        }

        return buildGson(typeAdapterSetting);
    }

}
