package com.android.open9527.common.page

import android.app.Activity
import android.os.Bundle
import com.android.open9527.base.BaseActivity
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils

/**
 * @author   open_9527
 * Create at 2021/5/18
 */
abstract class BaseCommonActivity : BaseActivity(), ICommonPage {

    protected var mActivity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mActivity = this
        initStatusBar()
        super.onCreate(savedInstanceState)
        initView(intent.extras)
    }

    override fun initStatusBar() {
        BarUtils.transparentStatusBar(this)
        BarUtils.setStatusBarLightMode(this, true)
    }

    override fun initView(bundle: Bundle?) {
//        getLifecycle().addObserver(NetworkStateManager.getInstance());
        initEvent()
        initRequest()
    }

    protected fun showLongToast(text: String?) {
        ToastUtils.showLong(text)
    }

    protected fun showShortToast(text: String?) {
        ToastUtils.showShort(text)
    }

    override fun onDestroy() {
        mActivity = null
        super.onDestroy()
    }
}