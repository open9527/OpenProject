package com.android.open9527.permissions

import androidx.fragment.app.FragmentActivity
import java.util.*

/**
 * @author open_9527
 * Create at 2021/5/19
 */
interface IPermissionInterceptor {
    /**
     * 权限申请拦截，可在此处先弹 Dialog 再申请权限
     */
    fun requestPermissions(
        activity: FragmentActivity?,
        callback: OnPermissionCallback?,
        permissions: MutableList<String>?
    ) {
        PermissionFragment.beginRequest(activity!!, ArrayList(permissions), callback!!)
    }

    /**
     * 权限授予回调拦截，参见 [OnPermissionCallback.onGranted]
     */
    fun grantedPermissions(
        activity: FragmentActivity?,
        callback: OnPermissionCallback,
        permissions: List<String>?,
        all: Boolean
    ) {
        callback.onGranted(permissions, all)
    }

    /**
     * 权限拒绝回调拦截，参见 [OnPermissionCallback.onDenied]
     */
    fun deniedPermissions(
        activity: FragmentActivity?,
        callback: OnPermissionCallback,
        permissions: List<String>?,
        never: Boolean
    ) {
        callback.onDenied(permissions, never)
    }
}