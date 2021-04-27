package com.android.custom.pkg.recycleview.user;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author open_9527
 * Create at 2021/4/25
 **/
public class ContentVo implements Serializable {
    private String id;
    private String title;
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentVo contentVo = (ContentVo) o;
        return id.equals(contentVo.id) &&
                title.equals(contentVo.title) &&
                desc.equals(contentVo.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, desc);
    }
}
