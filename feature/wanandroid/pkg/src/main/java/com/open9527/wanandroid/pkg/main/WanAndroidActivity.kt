package com.open9527.wanandroid.pkg.main

import android.os.Bundle
import com.android.open9527.common.page.BaseCommonActivity
import com.android.open9527.page.DataBindingConfig
import com.blankj.utilcode.util.AppUtils
import com.open9527.annotation.router.Router
import com.open9527.wanandroid.pkg.BR
import com.open9527.wanandroid.pkg.R

/**
 * @author open_9527
 * Create at 2021/1/12
 */
@Router(path = "/wanandroid/WanAndroidActivity")
class WanAndroidActivity : BaseCommonActivity() {

    private var mViewModel: WanAndroidViewModel? = null

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(WanAndroidViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.wanandroid_activity, BR.vm, mViewModel!!)
    }

    override fun initView(bundle: Bundle?) {
        super.initView(bundle)
        mViewModel!!.initTab(supportFragmentManager)
    }

    private var exitTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            showShortToast("再按一次退出程序")
            exitTime = System.currentTimeMillis()
        } else {
            AppUtils.exitApp()
        }
    }
}