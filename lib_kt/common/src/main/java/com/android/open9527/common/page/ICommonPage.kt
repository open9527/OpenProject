package com.android.open9527.common.page

import android.os.Bundle

/**
 * @author   open_9527
 * Create at 2021/5/18
 */
interface ICommonPage {

    fun initStatusBar() {}

    fun initView(bundle: Bundle?) {}

    fun initRequest() {}

    fun initEvent() {}

}