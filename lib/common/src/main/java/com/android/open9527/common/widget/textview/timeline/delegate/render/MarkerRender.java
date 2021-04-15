package com.android.open9527.common.widget.textview.timeline.delegate.render;

import android.text.Spannable;

import com.android.open9527.common.widget.textview.timeline.marker.Marker;


public abstract class MarkerRender<T extends Marker> {
    private T mMarker;
    private int mPosition;

    MarkerRender(T marker, int position) {
        this.mMarker = marker;
        this.mPosition = position;
    }

    public T getMarker() {
        return mMarker;
    }

    public void setMarker(T mMarker) {
        this.mMarker = mMarker;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }

    public abstract String getDisplayText();

    public abstract void render(Spannable spannable);

    public boolean clickable() {
        return true;
    }
}
