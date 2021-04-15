package com.android.open9527.common.widget.textview.timeline;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


import com.android.open9527.common.widget.textview.timeline.delegate.TextViewDelegate;
import com.android.open9527.common.widget.textview.timeline.marker.Marker;

import java.util.List;

public class TimelineTextView extends AppCompatTextView {

    private TextViewDelegate mDelegate;

    public TimelineTextView(Context context) {
        super(context);
        initialize();
    }

    public TimelineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        mDelegate = new TextViewDelegate(this);
    }

    public void addOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        this.mDelegate.addOnMarkerClickListener(onMarkerClickListener);
    }

    public void setContent(String content) {
        this.mDelegate.setContent(content==null?"":content);
    }

    public String getContent() {
        return this.mDelegate.getContent();
    }

    public List<Marker> getMarkerList() {
        return this.mDelegate.getMarkerList();
    }

    //TODO:注意在setContent()方法之前调用
    public void setForegroundColor(int foregroundColor) {
        this.mDelegate.setForegroundColor(foregroundColor);
    }

}
