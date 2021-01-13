package com.android.open9527.common.net.data.json;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

/**
 *   float / Float 类型解析适配器，参考：{@link com.google.gson.internal.bind.TypeAdapters#FLOAT}
 */
public class FloatTypeAdapter extends DoubleTypeAdapter {

    @Override
    public Number read(JsonReader in) throws IOException {
        Number number = super.read(in);
        if (number != null) {
            return number.floatValue();
        }
        return null;
    }
}