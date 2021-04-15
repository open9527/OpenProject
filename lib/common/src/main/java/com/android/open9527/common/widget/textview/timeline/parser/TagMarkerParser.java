package com.android.open9527.common.widget.textview.timeline.parser;


import com.blankj.utilcode.util.StringUtils;
import com.android.open9527.common.widget.textview.timeline.marker.Marker;
import com.android.open9527.common.widget.textview.timeline.marker.TagMarker;

public class TagMarkerParser implements MarkerParser {

    private static final String PREFIX = "tag(";
    private static final String SUFFIX = ")";
    private static final String PARAMS_SPLIT = ":";

    @Override
    public boolean test(String expression) {
        return expression.startsWith(PREFIX) && expression.endsWith(SUFFIX);
    }

    @Override
    public Marker parse(String expression) {
        String[] params = expression.substring(PREFIX.length(), expression.length() - SUFFIX.length())
                .split(":");

        if (params.length < 2 || StringUtils.isEmpty(params[0]) || StringUtils.isEmpty(params[1])) {
            return null;
        }

        String id = params[0];
        String title = TimelineUtils.base64Decode(params[1]);

        return TagMarker.create(id, title);
    }

    public static String toString(TagMarker marker) {
        StringBuilder builder = new StringBuilder();
        builder.append(PREFIX)
                .append(marker.getId())
                .append(PARAMS_SPLIT);

        builder.append(TimelineUtils.base64Encode(marker.getTitle()));
        builder.append(SUFFIX);

        String text = TimelineUtils.base64Encode(marker.getTitle());
        System.out.println(text);

        return builder.toString();
    }
}
