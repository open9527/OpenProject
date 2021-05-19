package com.android.kotlin.app

import com.android.open9527.common.app.CommonApplication
import com.android.open9527.permissions.XXPermissions


/**
 * @author   open_9527
 * Create at 2021/5/18
 */
class KotlinApp : CommonApplication() {

    override fun onCreate() {
        super.onCreate()
        XXPermissions.setDebugMode(BuildConfig.DEBUG)
    }
}