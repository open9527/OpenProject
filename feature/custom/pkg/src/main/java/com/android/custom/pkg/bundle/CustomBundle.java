package com.android.custom.pkg.bundle;

import android.app.Activity;

import com.android.open9527.common.bundle.BaseBundleData;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/5/27
 **/
public class CustomBundle extends BaseBundleData implements Serializable {

    private Class<? extends Activity> cls;
    private String title;


    public CustomBundle( String title) {
        this.title = title;
    }

    public Class<? extends Activity> getCls() {
        return cls;
    }

    public void setCls(Class<? extends Activity> cls) {
        this.cls = cls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
