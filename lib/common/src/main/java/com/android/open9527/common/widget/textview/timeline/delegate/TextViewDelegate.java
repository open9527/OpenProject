package com.android.open9527.common.widget.textview.timeline.delegate;

import android.text.Editable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.open9527.common.widget.textview.timeline.OnMarkerClickListener;
import com.android.open9527.common.widget.textview.timeline.delegate.render.MarkerRender;
import com.android.open9527.common.widget.textview.timeline.delegate.render.MarkerRenders;
import com.android.open9527.common.widget.textview.timeline.marker.Marker;
import com.android.open9527.common.widget.textview.timeline.marker.Markers;
import com.android.open9527.common.widget.textview.timeline.marker.TextMarker;

import java.util.ArrayList;
import java.util.List;

public class TextViewDelegate {

    private TextView mTextView;
    private int mForegroundColor = -1;
    private List<OnMarkerClickListener> mOnMarkerClickListenerList = new ArrayList<>();


    public TextViewDelegate(TextView textView) {
        mTextView = textView;
    }

    public void addOnMarkerClickListener(OnMarkerClickListener listener) {
        mOnMarkerClickListenerList.add(listener);
    }

    public void onMarkerClicked(@NonNull View widget, Marker marker) {
        for (OnMarkerClickListener listener : mOnMarkerClickListenerList) {
            listener.onMarkerClick(widget, marker);

        }
    }

    public void setForegroundColor(int foregroundColor) {
        mForegroundColor = foregroundColor;
    }

    public void setContent(String content) {
        List<Marker> markerList = Markers.toMarkers(content);
        //字符串位置-marker map
        //Range有版本兼容问题，先用Point表示, x=start; y=end
        List<MarkerRender> renderList = new ArrayList<>();
        String displayText = "";
        for (Marker marker : markerList) {
            if (marker instanceof TextMarker) {
                displayText = displayText.concat(((TextMarker) marker).getText());

            } else {
                if (-1 != mForegroundColor) {
                    MarkerRender<?> render = MarkerRenders.createMarkerRender(this, marker, displayText.length(), mForegroundColor);
                    renderList.add(render);
                    displayText = displayText.concat(render.getDisplayText());
                } else {
                    MarkerRender<?> render = MarkerRenders.createMarkerRender(this, marker, displayText.length());
                    renderList.add(render);
                    displayText = displayText.concat(render.getDisplayText());
                }
            }

        }

        boolean clickable = false;
        SpannableString spannableString = SpannableString.valueOf(displayText);

        for (MarkerRender render : renderList) {
            render.render(spannableString);
            if (render.clickable()) {
                clickable = true;
            }
        }

        if (clickable) {
            mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }

        mTextView.setText(spannableString);

    }

    public String getContent() {
        Editable editableText = mTextView.getEditableText();
        if (editableText == null) {
            return "";
        }

        return Markers.fromMarkers(getMarkerList());
    }

    @NonNull
    public List<Marker> getMarkerList() {
        List<Marker> markerList = new ArrayList<>();

        Editable editableText = mTextView.getEditableText();
        if (editableText == null) {
            return markerList;
        }

        for (int pos = editableText.length() - 1; pos >= 0; pos--) {
            Marker[] markers = editableText.getSpans(pos, pos + 1, Marker.class);
            if (markers != null && markers.length > 0) {
                Marker marker = markers[0];
                pos = editableText.getSpanStart(marker);
                markerList.add(0, marker);

            } else {
                markerList.add(0, TextMarker.create(String.valueOf(editableText.charAt(pos))));
            }
        }

        return markerList;
    }


    public boolean replaceMarker(Marker oldMarker, Marker newMarker) {
        Editable editableText = mTextView.getEditableText();
        if (editableText == null) {
            return false;
        }

        int start = editableText.getSpanStart(oldMarker);
        if (start < 0) {
            return false;
        }
        int end = editableText.getSpanEnd(oldMarker);

        replaceMarker(start, end, newMarker);

        return true;
    }

    public boolean replaceMarker(int start, int end, Marker newMarker) {
        Editable editableText = mTextView.getEditableText();
        if (editableText == null) {
            return false;
        }

        if (start >= 0 && end >= 0 && start != end) {
            editableText.delete(start, end);
        }

        if (newMarker != null) {
            if (-1 != mForegroundColor) {
                MarkerRender<?> render = MarkerRenders.createMarkerRender(this, newMarker, start, mForegroundColor);
                editableText.insert(start, render.getDisplayText());
                render.render(editableText);
                if (render.clickable()) {
                    mTextView.setMovementMethod(LinkMovementMethod.getInstance());
                }
            } else {
                MarkerRender<?> render = MarkerRenders.createMarkerRender(this, newMarker, start);
                editableText.insert(start, render.getDisplayText());
                render.render(editableText);
                if (render.clickable()) {
                    mTextView.setMovementMethod(LinkMovementMethod.getInstance());
                }
            }


        }

        return true;
    }

}
