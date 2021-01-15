package com.android.open9527.common.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/

public final class FontUtils {

    private static Map<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface(String fontName, Context context) {

        Typeface typeface = fontCache.get(fontName);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(fontName, typeface);
        }
        return typeface;
    }
}
