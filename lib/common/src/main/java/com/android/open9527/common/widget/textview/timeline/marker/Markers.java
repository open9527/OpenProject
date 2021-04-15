package com.android.open9527.common.widget.textview.timeline.marker;



import com.blankj.utilcode.util.StringUtils;
import com.android.open9527.common.widget.textview.timeline.parser.AtMarkerParser;
import com.android.open9527.common.widget.textview.timeline.parser.JumpMarkerParser;
import com.android.open9527.common.widget.textview.timeline.parser.MarkerParser;
import com.android.open9527.common.widget.textview.timeline.parser.TagMarkerParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Markers {

    private static final char MARKER_PREFIX_CHAR = '[';
    private static final char MARKER_SUFFIX_CHAR = ']';

    private static final String MARKER_PREFIX = String.valueOf(MARKER_PREFIX_CHAR);
    private static final String MARKER_SUFFIX = String.valueOf(MARKER_SUFFIX_CHAR);

    public static List<Marker> toMarkers(String content) {
        //按照"[标签内容]"格式解析成段落列表spanList，包含"[", "]", "普通文本"
        List<String> spanList = new ArrayList<>();
        String lastSpan = "";
        for (int idx = 0; idx < content.length(); idx++) {
            char character = content.charAt(idx);
            if (character == MARKER_PREFIX_CHAR || character == MARKER_SUFFIX_CHAR) {
                if (!StringUtils.isEmpty(lastSpan)) {
                    spanList.add(lastSpan);
                    lastSpan = "";
                }
                spanList.add(String.valueOf(character));

            } else {
                lastSpan = lastSpan.concat(String.valueOf(character));
            }
        }
        if (!StringUtils.isEmpty(lastSpan)) {
            spanList.add(lastSpan);
        }

        //spanList -> markerList
        List<Marker> markerList = new ArrayList<>();
        for (int idx = 0; idx < spanList.size(); idx++) {
            Marker marker = null;
            if (idx < spanList.size() - 2
                    && StringUtils.equals(spanList.get(idx), MARKER_PREFIX)
                    && StringUtils.equals(spanList.get(idx + 2), MARKER_SUFFIX)) {
                marker = parseExpression(spanList.get(idx + 1));
            }

            if (marker != null) {
                markerList.add(marker);
                idx += 2;

            } else {
                markerList.add(TextMarker.create(spanList.get(idx)));
            }
        }

        return markerList;
    }

    public static String fromMarkers(List<Marker> markerList) {
        String content = "";
        for (Marker marker : markerList) {
            if (marker instanceof TextMarker) {
                content = content.concat(marker.toString());
            } else {
                content = content.concat(MARKER_PREFIX)
                        .concat(marker.toString())
                        .concat(MARKER_SUFFIX);
            }
        }
        return content;
    }

    public static List<TagMarker> getTagMarkers(List<Marker> markerList) {
        List<TagMarker> tagMarkers = new ArrayList<>();
        for (Marker marker : markerList) {
            if (marker instanceof TagMarker) {
                tagMarkers.add((TagMarker) marker);
            }
        }
        return tagMarkers;
    }


    private static Set<MarkerParser> parsers = new HashSet<>();

    static {
        parsers.add(new AtMarkerParser());
        parsers.add(new JumpMarkerParser());
        parsers.add(new TagMarkerParser());
    }

    private static Marker parseExpression(String expression) {
        expression = expression.trim();
        for (MarkerParser parser : parsers) {
            if (parser.test(expression)) {
                Marker marker = parser.parse(expression);
                if (marker != null) {
                    return marker;
                }
            }
        }
        return null;
    }

}
