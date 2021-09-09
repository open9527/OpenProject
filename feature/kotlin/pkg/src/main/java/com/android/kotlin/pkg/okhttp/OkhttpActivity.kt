package com.android.kotlin.pkg.okhttp

import android.view.View
import com.android.kotlin.pkg.BR
import com.android.kotlin.pkg.R
import com.android.open9527.common.page.BaseCommonActivity
import com.android.open9527.page.DataBindingConfig
import com.blankj.utilcode.util.LogUtils


/**
 *@author   open_9527
 *Create at 2021/8/17
 **/
class OkhttpActivity() : BaseCommonActivity() {

    private var mViewModel: OkhttpViewModel? = null

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(OkhttpViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.okhttp_activity, BR.vm, mViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
    }


    inner class ClickProxy {
        var backClick = View.OnClickListener {

            LogUtils.i(TAG, "backClick")
        }
    }
}