package com.android.open9527.common.widget.textview.timeline.marker;


import androidx.annotation.NonNull;

import com.android.open9527.common.widget.textview.timeline.parser.AtMarkerParser;


/**
 * @某人 格式：[@(id:name)]
 * 参数
 * id: 用户id
 * name: base64编码的用户名
 * 样例：[at(888888:5qix5qGD5bCP5Li45a2Q)]
 */
public class AtMarker implements Marker {

    private String id;
    private String name;

    private AtMarker(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AtMarker create(String id, String name) {
        return new AtMarker(id, name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @NonNull
    public String toString() {
        return AtMarkerParser.toString(this);
    }

}
