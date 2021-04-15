package com.android.open9527.common.widget.textview.timeline.parser;


import com.blankj.utilcode.util.StringUtils;
import com.android.open9527.common.widget.textview.timeline.marker.JumpMarker;
import com.android.open9527.common.widget.textview.timeline.marker.Marker;

public class JumpMarkerParser implements MarkerParser {

    private static final String PREFIX = "jump(";
    private static final String SUFFIX = ")";
    private static final String PARAMS_SPLIT = ":";

    @Override
    public boolean test(String expression) {
        return expression.startsWith(PREFIX) && expression.endsWith(SUFFIX);
    }

    @Override
    public Marker parse(String expression) {
        String[] params = expression.substring(PREFIX.length(), expression.length() - SUFFIX.length())
                .split(PARAMS_SPLIT);

        if (params.length < 2 || StringUtils.isEmpty(params[0]) || StringUtils.isEmpty(params[1])) {
            return null;
        }

        String title = TimelineUtils.base64Decode(params[0]);
        String target = TimelineUtils.base64Decode(params[1]);

        return JumpMarker.create(title, target);
    }

    public static String toString(JumpMarker marker) {
        StringBuilder builder = new StringBuilder();
        builder.append(PREFIX);
        builder.append(TimelineUtils.base64Encode(marker.getTitle()));
        builder.append(PARAMS_SPLIT);
        builder.append(TimelineUtils.base64Encode(marker.getTarget()));
        builder.append(SUFFIX);

        return builder.toString();
    }

}
