package com.android.open9527.common.widget.textview.timeline.delegate.render;

import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.annotation.NonNull;

import com.android.open9527.common.widget.textview.timeline.delegate.TextViewDelegate;
import com.android.open9527.common.widget.textview.timeline.marker.AtMarker;


class AtMarkerRender extends MarkerRender<AtMarker> {

    private int FOREGROUND_COLOR = 0xFF0091FF;

    private TextViewDelegate mDelegate;

    AtMarkerRender(TextViewDelegate delegate, AtMarker marker, int position) {
        super(marker, position);
        mDelegate = delegate;
    }

    AtMarkerRender(TextViewDelegate delegate, AtMarker marker, int position, int foreground_color) {
        super(marker, position);
        mDelegate = delegate;
        FOREGROUND_COLOR = foreground_color;
    }

    public String getDisplayText() {
        return "@" + getMarker().getName();
    }

    public void render(Spannable spannable) {
        int start = getPosition();
        int end = start + getMarker().getName().length() + 1;

        spannable.setSpan(getMarker(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannable.setSpan(new ForegroundColorSpan(FOREGROUND_COLOR), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                mDelegate.onMarkerClicked(widget, getMarker());
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(FOREGROUND_COLOR);
                ds.setUnderlineText(false);
            }
        }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }

}
