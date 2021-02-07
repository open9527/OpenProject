package com.android.feature.permission.export;

import com.android.open9527.common.bundle.BaseBundleData;

/**
 * @author open_9527
 * Create at 2021/2/7
 **/
public class PermissionParam extends BaseBundleData {
    private String name;
    private String type;

    public PermissionParam(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PermissionParam{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
