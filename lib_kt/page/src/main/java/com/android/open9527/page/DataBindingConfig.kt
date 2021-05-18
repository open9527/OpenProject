package com.android.open9527.page

import android.util.SparseArray
import androidx.lifecycle.ViewModel

/**
 *@author   open_9527
 *Create at 2021/5/18
 **/
class DataBindingConfig(
    val layout: Int,
    val vmVariableId: Int,
    val stateViewModel: ViewModel
) {
    private val bindingParams = SparseArray<Any?>()
    fun getBindingParams(): SparseArray<*> {
        return bindingParams
    }

    fun addBindingParam(
        variableId: Int,
        `object`: Any
    ): DataBindingConfig {
        if (bindingParams[variableId] == null) {
            bindingParams.put(variableId, `object`)
        }
        return this
    }
}