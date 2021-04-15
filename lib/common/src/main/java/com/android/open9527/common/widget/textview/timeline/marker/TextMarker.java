package com.android.open9527.common.widget.textview.timeline.marker;


import androidx.annotation.NonNull;

/**
 * 普通文本
 */
public class TextMarker implements Marker {

    private String text;

    private TextMarker(String text) {
        this.text = text;
    }

    public static TextMarker create(String text) {
        return new TextMarker(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    @NonNull
    public String toString() {
        return getText();
    }
}
