package com.open9527.wanandroid.pkg.main.h5

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * @author open_9527
 * Create at 2021/1/18
 */
class H5ViewModel : ViewModel() {

    val valueTitle = ObservableField("h5")
    @JvmField
    val valueH5Url = ObservableField<String>()
}