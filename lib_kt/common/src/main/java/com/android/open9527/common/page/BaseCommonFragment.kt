package com.android.open9527.common.page

import android.os.Bundle
import android.view.View
import com.android.open9527.base.BaseFragment
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils

/**
 * @author   open_9527
 * Create at 2021/5/18
 */
abstract class BaseCommonFragment : BaseFragment(), ICommonPage {
    // 是否第一次加载
    private var isFirstLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.i(TAG, "onCreate")
        val fm = fragmentManager

        if (savedInstanceState != null) {
            val isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN)
            val ft = fm?.beginTransaction()
            if (isSupportHidden) {
                ft?.hide(this)
            } else {
                ft?.show(this)
            }
            ft?.commitAllowingStateLoss()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(arguments)
        initEvent()
        LogUtils.i(TAG, "onViewCreated")
    }

    override fun initView(bundle: Bundle?) {
        LogUtils.i(TAG, "initView")
    }

    override fun onResume() {
        super.onResume()
        initStatusBar()
        if (isFirstLoad) {
            initRequest()
            isFirstLoad = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden)
    }

    protected fun showLongToast(text: String?) {
        ToastUtils.showLong(text)
    }

    protected fun showShortToast(text: String?) {
        ToastUtils.showShort(text)
    }

    companion object {
        private const val STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"
    }
}