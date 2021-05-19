package com.android.open9527.permissions

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.util.SparseBooleanArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.android.open9527.permissions.PermissionSettingPage.getInstallPermissionIntent
import com.android.open9527.permissions.PermissionSettingPage.getNotifyPermissionIntent
import com.android.open9527.permissions.PermissionSettingPage.getSettingPermissionIntent
import com.android.open9527.permissions.PermissionSettingPage.getStoragePermissionIntent
import com.android.open9527.permissions.PermissionSettingPage.getWindowPermissionIntent
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author open_9527
 * Create at 2021/5/19
 */
class PermissionFragment : Fragment(), Runnable {
    /** 是否申请了特殊权限  */
    private var mSpecialRequest = false

    /** 是否申请了危险权限  */
    private var mDangerousRequest = false

    /** 权限回调对象  */
    private var mCallBack: OnPermissionCallback? = null

    /** Activity 屏幕方向  */
    private var mScreenOrientation = 0

    /**
     * 绑定 Activity
     */
    fun attachActivity(activity: FragmentActivity) {
        activity.supportFragmentManager.beginTransaction().add(this, this.toString())
            .commitAllowingStateLoss()
    }

    /**
     * 解绑 Activity
     */
    fun detachActivity(activity: FragmentActivity) {
        activity.supportFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
    }

    /**
     * 设置权限监听回调监听
     */
    fun setCallBack(callback: OnPermissionCallback?) {
        mCallBack = callback
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity = activity ?: return
        // 如果当前没有锁定屏幕方向就获取当前屏幕方向并进行锁定
        mScreenOrientation = activity.requestedOrientation
        if (mScreenOrientation != ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            return
        }
        val activityOrientation = activity.resources.configuration.orientation
        try {
            // 兼容问题：在 Android 8.0 的手机上可以固定 Activity 的方向，但是这个 Activity 不能是透明的，否则就会抛出异常
            // 复现场景：只需要给 Activity 主题设置 <item name="android:windowIsTranslucent">true</item> 属性即可
            if (activityOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else if (activityOrientation == Configuration.ORIENTATION_PORTRAIT) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        } catch (e: IllegalStateException) {
            // java.lang.IllegalStateException: Only fullscreen activities can request orientation
            e.printStackTrace()
        }
    }

    override fun onDetach() {
        super.onDetach()
        val activity = activity
        if (activity == null || mScreenOrientation != ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            return
        }
        // 为什么这里不用跟上面一样 try catch ？因为这里是把 Activity 方向取消固定，只有设置横屏或竖屏的时候才可能触发 crash
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    override fun onDestroy() {
        super.onDestroy()
        // 取消引用监听器，避免内存泄漏
        mCallBack = null
    }

    override fun onResume() {
        super.onResume()
        // 如果在 Activity 不可见的状态下添加 Fragment 并且去申请权限会导致授权对话框显示不出来
        // 所以必须要在 Fragment 的 onResume 来申请权限，这样就可以保证应用回到前台的时候才去申请权限
        if (mSpecialRequest) {
            return
        }
        mSpecialRequest = true
        requestSpecialPermission()
    }

    /**
     * 申请特殊权限
     */
    fun requestSpecialPermission() {
        val arguments = arguments
        val activity = activity
        if (arguments == null || activity == null) {
            return
        }
        val permissions: List<String>? = arguments.getStringArrayList(REQUEST_PERMISSIONS)

        // 是否需要申请特殊权限
        var requestSpecialPermission = false

        // 判断当前是否包含特殊权限
        if (PermissionUtils.containsSpecialPermission(permissions)) {
            if (permissions!!.contains(Permission.MANAGE_EXTERNAL_STORAGE) && !PermissionUtils.isGrantedStoragePermission(
                    activity
                )
            ) {
                // 当前必须是 Android 11 及以上版本，因为 hasStoragePermission 在旧版本上是拿旧权限做的判断，所以这里需要多判断一次版本
                if (PermissionUtils.isAndroid11) {
                    // 跳转到存储权限设置界面
                    startActivityForResult(
                        getStoragePermissionIntent(activity), getArguments()!!.getInt(
                            REQUEST_CODE
                        )
                    )
                    requestSpecialPermission = true
                }
            }
            if (permissions.contains(Permission.REQUEST_INSTALL_PACKAGES) && !PermissionUtils.isGrantedInstallPermission(
                    activity
                )
            ) {
                // 跳转到安装权限设置界面
                startActivityForResult(
                    getInstallPermissionIntent(activity), getArguments()!!.getInt(
                        REQUEST_CODE
                    )
                )
                requestSpecialPermission = true
            }
            if (permissions.contains(Permission.SYSTEM_ALERT_WINDOW) && !PermissionUtils.isGrantedWindowPermission(
                    activity
                )
            ) {
                // 跳转到悬浮窗设置页面
                startActivityForResult(
                    getWindowPermissionIntent(activity), getArguments()!!.getInt(
                        REQUEST_CODE
                    )
                )
                requestSpecialPermission = true
            }
            if (permissions.contains(Permission.NOTIFICATION_SERVICE) && !PermissionUtils.isGrantedNotifyPermission(
                    activity
                )
            ) {
                // 跳转到通知栏权限设置页面
                startActivityForResult(
                    getNotifyPermissionIntent(activity), getArguments()!!.getInt(
                        REQUEST_CODE
                    )
                )
                requestSpecialPermission = true
            }
            if (permissions.contains(Permission.WRITE_SETTINGS) && !PermissionUtils.isGrantedSettingPermission(
                    activity
                )
            ) {
                // 跳转到系统设置权限设置页面
                startActivityForResult(
                    getSettingPermissionIntent(activity), getArguments()!!.getInt(
                        REQUEST_CODE
                    )
                )
                requestSpecialPermission = true
            }
        }

        // 当前必须没有跳转到悬浮窗或者安装权限界面
        if (!requestSpecialPermission) {
            requestDangerousPermission()
        }
    }

    /**
     * 申请危险权限
     */
    fun requestDangerousPermission() {
        val activity = activity
        val arguments = arguments
        if (activity == null || arguments == null) {
            return
        }
        val requestCode = arguments.getInt(REQUEST_CODE)
        val allPermissions = arguments.getStringArrayList(REQUEST_PERMISSIONS)
        if (allPermissions == null || allPermissions.size == 0) {
            return
        }
        var locationPermission: ArrayList<String>? = null
        // Android 10 定位策略发生改变，申请后台定位权限的前提是要有前台定位权限（授予了精确或者模糊任一权限）
        if (PermissionUtils.isAndroid10 && allPermissions.contains(Permission.ACCESS_BACKGROUND_LOCATION)) {
            locationPermission = ArrayList()
            if (allPermissions.contains(Permission.ACCESS_COARSE_LOCATION) &&
                !PermissionUtils.isGrantedPermission(activity, Permission.ACCESS_COARSE_LOCATION)
            ) {
                locationPermission.add(Permission.ACCESS_COARSE_LOCATION)
            }
            if (allPermissions.contains(Permission.ACCESS_FINE_LOCATION) &&
                !PermissionUtils.isGrantedPermission(activity, Permission.ACCESS_FINE_LOCATION)
            ) {
                locationPermission.add(Permission.ACCESS_FINE_LOCATION)
            }
        }

        // 如果不需要申请前台定位权限就直接申请危险权限
        if (locationPermission == null || locationPermission.isEmpty()) {
            requestPermissions(allPermissions.toTypedArray(), getArguments()!!.getInt(REQUEST_CODE))
            return
        }

        // 在 Android 10 的机型上，需要先申请前台定位权限，再申请后台定位权限
        beginRequest(activity, locationPermission, false, object : OnPermissionCallback {
            override fun onGranted(permissions: List<String>?, all: Boolean) {
                if (!all || !isAdded) {
                    return
                }
                // 前台定位权限授予了，现在申请后台定位权限
                beginRequest(activity, PermissionUtils.asArrayList(Permission.ACCESS_BACKGROUND_LOCATION),
                    false, object : OnPermissionCallback {
                        override fun onGranted(permissions: List<String>?, all: Boolean) {
                            if (!all || !isAdded) {
                                return
                            }

                            // 前台定位权限和后台定位权限都授予了
                            val grantResults = IntArray(allPermissions.size)
                            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED)
                            onRequestPermissionsResult(
                                requestCode,
                                allPermissions.toTypedArray(),
                                grantResults
                            )
                        }

                        override fun onDenied(permissions: List<String>?, never: Boolean) {
                            if (!isAdded) {
                                return
                            }

                            // 后台定位授权失败，但是前台定位权限已经授予了
                            val grantResults = IntArray(allPermissions.size)
                            for (i in allPermissions.indices) {
                                grantResults[i] =
                                    if (Permission.ACCESS_BACKGROUND_LOCATION == allPermissions[i]) PackageManager.PERMISSION_DENIED else PackageManager.PERMISSION_GRANTED
                            }
                            onRequestPermissionsResult(
                                requestCode,
                                allPermissions.toTypedArray(),
                                grantResults
                            )
                        }
                    })
            }

            override fun onDenied(permissions: List<String>?, never: Boolean) {
                if (!isAdded) {
                    return
                }

                // 前台定位授权失败，并且无法申请后台定位权限
                val grantResults = IntArray(allPermissions.size)
                Arrays.fill(grantResults, PackageManager.PERMISSION_DENIED)
                onRequestPermissionsResult(requestCode, allPermissions.toTypedArray(), grantResults)
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        val arguments = arguments
        val activity = activity
        if (activity == null || arguments == null || mCallBack == null || requestCode != arguments.getInt(
                REQUEST_CODE
            )
        ) {
            return
        }
        val useInterceptor = arguments.getBoolean(USE_INTERCEPTOR)
        val callback = mCallBack
        mCallBack = null
        for (i in permissions.indices) {
            val permission = permissions[i]
            if (PermissionUtils.isSpecialPermission(permission)) {
                // 如果这个权限是特殊权限，那么就重新进行权限检测
                grantResults[i] = PermissionUtils.getPermissionStatus(activity, permission)
                continue
            }

            // 重新检查 Android 11 后台定位权限
            if (PermissionUtils.isAndroid11 && Permission.ACCESS_BACKGROUND_LOCATION == permission) {
                // 这个权限是后台定位权限并且当前手机版本是 Android 11 及以上，那么就需要重新进行检测
                // 因为只要申请这个后台定位权限，grantResults 数组总对这个权限申请的结果返回 -1（拒绝）
                grantResults[i] = PermissionUtils.getPermissionStatus(activity, permission)
                continue
            }

            // 重新检查 Android 10.0 的三个新权限
            if (!PermissionUtils.isAndroid10 &&
                (Permission.ACCESS_BACKGROUND_LOCATION == permission || Permission.ACTIVITY_RECOGNITION == permission || Permission.ACCESS_MEDIA_LOCATION == permission)
            ) {
                // 如果当前版本不符合最低要求，那么就重新进行权限检测
                grantResults[i] = PermissionUtils.getPermissionStatus(activity, permission)
                continue
            }

            // 重新检查 Android 9.0 的一个新权限
            if (!PermissionUtils.isAndroid9 && Permission.ACCEPT_HANDOVER == permission) {
                // 如果当前版本不符合最低要求，那么就重新进行权限检测
                grantResults[i] = PermissionUtils.getPermissionStatus(activity, permission)
                continue
            }

            // 重新检查 Android 8.0 的两个新权限
            if (!PermissionUtils.isAndroid8 &&
                (Permission.ANSWER_PHONE_CALLS == permission || Permission.READ_PHONE_NUMBERS == permission)
            ) {
                // 如果当前版本不符合最低要求，那么就重新进行权限检测
                grantResults[i] = PermissionUtils.getPermissionStatus(activity, permission)
            }
        }

        // 释放对这个请求码的占用
        REQUEST_CODE_ARRAY.delete(requestCode)
        // 将 Fragment 从 Activity 移除
        detachActivity(activity)

        // 获取已授予的权限
        val grantedPermission = PermissionUtils.getGrantedPermissions(permissions, grantResults)

        // 如果请求成功的权限集合大小和请求的数组一样大时证明权限已经全部授予
        if (grantedPermission.size == permissions.size) {
            if (useInterceptor) {
                // 代表申请的所有的权限都授予了
                XXPermissions.interceptor?.grantedPermissions(
                    activity,
                    callback!!,
                    grantedPermission,
                    true
                )
            } else {
                callback?.onGranted(grantedPermission, true)
            }
            return
        }

        // 获取被拒绝的权限
        val deniedPermission = PermissionUtils.getDeniedPermissions(permissions, grantResults)
        if (useInterceptor) {
            // 代表申请的权限中有不同意授予的，如果有某个权限被永久拒绝就返回 true 给开发人员，让开发者引导用户去设置界面开启权限
            XXPermissions.interceptor?.deniedPermissions(
                activity,
                callback!!,
                deniedPermission,
                PermissionUtils.isPermissionPermanentDenied(activity, deniedPermission)
            )
        } else {
            callback?.onDenied(
                deniedPermission,
                PermissionUtils.isPermissionPermanentDenied(activity, deniedPermission)
            )
        }

        // 证明还有一部分权限被成功授予，回调成功接口
        if (grantedPermission.isNotEmpty()) {
            if (useInterceptor) {
                XXPermissions.interceptor?.grantedPermissions(
                    activity,
                    callback!!,
                    grantedPermission,
                    false
                )
            } else {
                callback?.onDenied(grantedPermission, false)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val activity = activity
        val arguments = arguments
        if (activity == null || arguments == null || requestCode != arguments.getInt(REQUEST_CODE) || mDangerousRequest) {
            return
        }
        mDangerousRequest = true
        // 需要延迟执行，不然有些华为机型授权了但是获取不到权限
        activity.window.decorView.postDelayed(this, 200)
    }

    override fun run() {
        // 如果用户离开太久，会导致 Activity 被回收掉
        // 所以这里要判断当前 Fragment 是否有被添加到 Activity
        // 可在开发者模式中开启不保留活动来复现这个 Bug
        if (!isAdded) {
            return
        }
        // 请求其他危险权限
        requestDangerousPermission()
    }

    companion object {
        /** 请求的权限组  */
        private const val REQUEST_PERMISSIONS = "request_permissions"

        /** 请求码（自动生成） */
        private const val REQUEST_CODE = "request_code"

        /** 是否经过拦截器  */
        private const val USE_INTERCEPTOR = "use_interceptor"

        /** 权限请求码存放集合  */
        private val REQUEST_CODE_ARRAY = SparseBooleanArray()
        fun beginRequest(
            activity: FragmentActivity,
            permissions: ArrayList<String>,
            callback: OnPermissionCallback
        ) {
            beginRequest(activity, permissions, true, callback)
        }

        /**
         * 开启权限申请
         */
        private fun beginRequest(
            activity: FragmentActivity,
            permissions: ArrayList<String>?,
            interceptor: Boolean,
            callback: OnPermissionCallback
        ) {
            val fragment = PermissionFragment()
            val bundle = Bundle()
            var requestCode: Int
            // 请求码随机生成，避免随机产生之前的请求码，必须进行循环判断
            do {
                requestCode = PermissionUtils.randomRequestCode
            } while (REQUEST_CODE_ARRAY[requestCode])
            // 标记这个请求码已经被占用
            REQUEST_CODE_ARRAY.put(requestCode, true)
            bundle.putInt(REQUEST_CODE, requestCode)
            bundle.putStringArrayList(REQUEST_PERMISSIONS, permissions)
            bundle.putBoolean(USE_INTERCEPTOR, interceptor)
            fragment.arguments = bundle
            // 设置保留实例，不会因为屏幕方向或配置变化而重新创建
            fragment.retainInstance = true
            // 设置权限回调监听
            fragment.setCallBack(callback)
            // 绑定到 Activity 上面
            fragment.attachActivity(activity)
        }
    }
}