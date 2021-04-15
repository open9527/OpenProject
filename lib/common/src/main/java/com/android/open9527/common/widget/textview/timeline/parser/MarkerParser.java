package com.android.open9527.common.widget.textview.timeline.parser;


import com.android.open9527.common.widget.textview.timeline.marker.Marker;

public interface MarkerParser {

    boolean test(String expression);

    Marker parse(String expression);

}
