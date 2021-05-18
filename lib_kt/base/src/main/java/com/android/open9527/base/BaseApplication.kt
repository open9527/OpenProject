package com.android.open9527.base

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * @author   open_9527
 * Create at 2021/5/18
 */
abstract class BaseApplication : Application(), ViewModelStoreOwner {

    private var mAppViewModelStore: ViewModelStore? = null

    override fun onCreate() {
        super.onCreate()
        mAppViewModelStore = ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore!!
    }
}