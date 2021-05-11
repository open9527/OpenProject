package com.open9527.wanandroid.pkg.main.h5

import android.os.Bundle
import com.android.open9527.common.bundle.BundleUtils
import com.android.open9527.common.page.BaseCommonActivity
import com.android.open9527.page.DataBindingConfig
import com.blankj.utilcode.util.ActivityUtils
import com.open9527.wanandroid.pkg.BR
import com.open9527.wanandroid.pkg.R

/**
 * @author open_9527
 * Create at 2021/1/18
 */
class H5Activity : BaseCommonActivity() {
    private var mViewModel: H5ViewModel? = null
    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(H5ViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.h5_activity, BR.vm, mViewModel!!)
    }

    override fun initView(bundle: Bundle?) {
        super.initView(bundle)
        val h5Bundle: H5Bundle = BundleUtils.getBundleData(bundle)
            ?: throw IllegalArgumentException("H5Bundle is null")
        mViewModel!!.valueH5Url.set(h5Bundle.url)
        mViewModel!!.valueTitle.set(h5Bundle.title)
    }

    companion object {
        @JvmStatic
        fun startH5(url: String, title: String?) {
            ActivityUtils.startActivity(
                BundleUtils.createBundleJson(H5Bundle(url, title!!)),
                H5Activity::class.java
            )
        }
    }
}