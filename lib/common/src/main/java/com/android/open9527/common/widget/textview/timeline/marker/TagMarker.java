package com.android.open9527.common.widget.textview.timeline.marker;

import androidx.annotation.NonNull;

import com.android.open9527.common.widget.textview.timeline.parser.TagMarkerParser;


/**
 * 引用标签
 * 格式：[tag(id:title)]
 * 参数
 * id: 标签id
 * title: base64编码的标签名称
 * 样例：[tag(888888:5q+P5pel6ZqP5ouN)]
 */
public class TagMarker implements Marker {

    private String id;
    private String title;

    private TagMarker(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public static TagMarker create(String id, String title) {
        return new TagMarker(id, title);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    @NonNull
    public String toString() {
        return TagMarkerParser.toString(this);
    }
}
