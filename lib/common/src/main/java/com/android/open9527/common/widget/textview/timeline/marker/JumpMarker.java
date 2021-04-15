package com.android.open9527.common.widget.textview.timeline.marker;

import androidx.annotation.NonNull;

import com.android.open9527.common.widget.textview.timeline.parser.JumpMarkerParser;


/**
 * 内部跳转（在APP浏览器中打开）
 * 格式：[jump(title:target)]
 * 参数
 * title: base64编码的标题
 * target: base64编码的内部跳转链接，见用例《通用跳转逻辑》
 * 样例：[inner(5paw5rWq:cm10Oi8vbGluaz90YXJnZXQ9aHR0cCUzYSUyZiUyZnd3dy5iYWlkdS5jb20lM2ZxJTNkYWJj)]
 */
public class JumpMarker implements Marker {

    private String title;
    private String target;

    private JumpMarker(String title, String target) {
        this.title = title;
        this.target = target;
    }

    public static JumpMarker create(String title, String target) {
        return new JumpMarker(title, target);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    @NonNull
    public String toString() {
        return JumpMarkerParser.toString(this);
    }

}
