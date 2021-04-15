package com.android.open9527.common.widget.textview.timeline.delegate.render;


import com.android.open9527.common.widget.textview.timeline.delegate.TextViewDelegate;
import com.android.open9527.common.widget.textview.timeline.marker.AtMarker;
import com.android.open9527.common.widget.textview.timeline.marker.JumpMarker;
import com.android.open9527.common.widget.textview.timeline.marker.Marker;
import com.android.open9527.common.widget.textview.timeline.marker.TagMarker;

public class MarkerRenders {

    public static MarkerRender<?> createMarkerRender(TextViewDelegate delegate, Marker marker, int position) {
        if (marker instanceof AtMarker) {
            return new AtMarkerRender(delegate, (AtMarker) marker, position);

        } else if (marker instanceof JumpMarker) {
            return new JumpMarkerRender(delegate, (JumpMarker) marker, position);

        } else if (marker instanceof TagMarker) {
            return new TagMarkerRender(delegate, (TagMarker) marker, position);

        } else {

            throw new IllegalArgumentException("undefined timeline marker");
        }
    }


    public static MarkerRender<?> createMarkerRender(TextViewDelegate delegate, Marker marker, int position,int foreground_color) {
        if (marker instanceof AtMarker) {
            return new AtMarkerRender(delegate, (AtMarker) marker, position,foreground_color);

        } else if (marker instanceof JumpMarker) {
            return new JumpMarkerRender(delegate, (JumpMarker) marker, position,foreground_color);

        } else if (marker instanceof TagMarker) {
            return new TagMarkerRender(delegate, (TagMarker) marker, position,foreground_color);

        } else {
            throw new IllegalArgumentException("undefined timeline marker");
        }
    }

}
