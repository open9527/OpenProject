package com.android.kotlin.pkg

import android.os.Bundle
import android.view.View
import com.android.open9527.base.BaseActivity
import com.android.open9527.page.DataBindingConfig
import com.blankj.utilcode.util.LogUtils

/**
 * @author   open_9527
 * Create at 2021/5/18
 */
class KotlinActivity : BaseActivity() {

    private var mViewModel: KotlinViewModel? = null

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(KotlinViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.kotlin_activity, BR.vm, mViewModel!!)
            .addBindingParam(BR.click,  ClickProxy())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtils.i(TAG,"onCreate")
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        LogUtils.i(TAG,"initView")
//        tv_title.text = mViewModel?.valueTitle?.get()

//        val title: TextView? = (getBinding() as KotlinActivityBinding?)?.tvTitle
//        title?.text = mViewModel?.valueTitle?.get()

//        (getBinding() as KotlinActivityBinding?)?.tvTitle?.text=mViewModel?.valueTitle?.get()
    }


     class ClickProxy {
        var backClick = View.OnClickListener { v: View? ->
            LogUtils.i("ClickProxy","backClick")
        }
    }


}