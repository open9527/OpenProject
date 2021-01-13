package com.android.open9527.permission;

/**
 * @author open_9527
 * Create at 2021/1/6
 **/
public class ManifestException extends RuntimeException {

    ManifestException() {
        // 清单文件中没有注册任何权限
        super("No permissions are registered in the manifest file");
    }

    ManifestException(String permission) {
        // 申请的危险权限没有在清单文件中注册
        super(permission + ": Permissions are not registered in the manifest file");
    }

}
