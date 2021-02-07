package com.android.feature.permission.export;

import com.android.open9527.common.bundle.BaseBundleData;

/**
 * @author open_9527
 * Create at 2021/2/7
 **/
public class PermissionResult extends BaseBundleData {

    private String id;
    private String status;

    public PermissionResult(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PermissionResult{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
