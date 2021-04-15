package com.android.open9527.common.widget.textview.timeline.delegate.render;

import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

import androidx.annotation.NonNull;

import com.android.open9527.common.widget.textview.timeline.delegate.TextViewDelegate;
import com.android.open9527.common.widget.textview.timeline.marker.JumpMarker;


class JumpMarkerRender extends MarkerRender<JumpMarker> {

    private int FOREGROUND_COLOR = 0xFF0091FF;

    private TextViewDelegate mDelegate;

    JumpMarkerRender(TextViewDelegate delegate, JumpMarker marker, int position) {
        super(marker, position);
        mDelegate = delegate;
    }

    JumpMarkerRender(TextViewDelegate delegate, JumpMarker marker, int position, int foreground_color) {
        super(marker, position);
        mDelegate = delegate;
        FOREGROUND_COLOR = foreground_color;
    }

    public String getDisplayText() {
        return getMarker().getTitle();
    }

    public void render(Spannable spannable) {
        int start = getPosition();
        int end = start + getDisplayText().length();

        spannable.setSpan(getMarker(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannable.setSpan(new ForegroundColorSpan(FOREGROUND_COLOR), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                mDelegate.onMarkerClicked(widget, getMarker());
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(FOREGROUND_COLOR);
                ds.setUnderlineText(true);
            }
        }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

}
