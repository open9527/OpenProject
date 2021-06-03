package com.android.kotlin.pkg

import android.os.Bundle
import android.view.View
import com.android.open9527.common.page.BaseCommonActivity
import com.android.open9527.page.DataBindingConfig
import com.android.open9527.permissions.OnPermissionCallback
import com.android.open9527.permissions.Permission
import com.android.open9527.permissions.XXPermissions
import com.blankj.utilcode.util.LogUtils

/**
 * @author   open_9527
 * Create at 2021/5/18
 */
class KotlinActivity() : BaseCommonActivity() {

    private var mViewModel: KotlinViewModel? = null

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(KotlinViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.kotlin_activity, BR.vm, mViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
    }



    override fun initView(bundle: Bundle?) {
        super.initView(bundle)
        LogUtils.i(TAG, "initView")

//        tv_title.text = mViewModel?.valueTitle?.get()

//        val title: TextView? = (getBinding() as KotlinActivityBinding?)?.tvTitle
//        title?.text = mViewModel?.valueTitle?.get()

//        (getBinding() as KotlinActivityBinding?)?.tvTitle?.text=mViewModel?.valueTitle?.get()
    }


    inner class ClickProxy {
        var backClick = View.OnClickListener { v: View? ->

            mViewModel?.valueTitle?.set("ClickProxy")
            request()
            LogUtils.i(TAG, "backClick")
        }
    }

    fun request() {
        XXPermissions.with(this)
            .permission(Permission.CAMERA)
            .permission(Permission.Group.STORAGE)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>?, all: Boolean) {
                    if (all) {
                        showLongToast("获取权限成功")
                    } else {
                        showLongToast("获取部分权限成功，但部分权限未正常授予")
                    }
                }

                override fun onDenied(permissions: List<String>?, never: Boolean) {
                    if (never) {
                        showLongToast("被永久拒绝授权，请手动授予权限")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(mActivity!!, permissions)
                    } else {
                        showLongToast("获取权限失败")
                    }
                }
            })
    }

}