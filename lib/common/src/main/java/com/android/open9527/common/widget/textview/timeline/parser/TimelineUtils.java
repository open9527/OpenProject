package com.android.open9527.common.widget.textview.timeline.parser;

import android.util.Base64;

import com.blankj.utilcode.util.StringUtils;
import com.android.open9527.common.widget.textview.timeline.marker.TagMarker;

import java.nio.charset.StandardCharsets;

public class TimelineUtils {
    private static final char MARKER_PREFIX_CHAR = '[';
    private static final char MARKER_SUFFIX_CHAR = ']';


    public static String base64Encode(String plainString) {
        if (StringUtils.isEmpty(plainString)) {
            return "";
        }

        return Base64.encodeToString(plainString.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP);
    }

    public static String base64Decode(String encryptedString) {
        try {
            return new String(Base64.decode(encryptedString, Base64.NO_WRAP), StandardCharsets.UTF_8);

        } catch (Exception e) {
            return "";
        }
    }

    public static String createTag(String id, String title) {
        StringBuilder builder = new StringBuilder();
        builder.append(MARKER_PREFIX_CHAR)
                .append(TagMarker.create(id, title))
                .append(MARKER_SUFFIX_CHAR);
        return builder.toString();
    }
}
