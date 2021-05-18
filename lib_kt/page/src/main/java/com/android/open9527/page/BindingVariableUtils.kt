package com.android.open9527.page

import android.util.SparseArray
import androidx.databinding.ViewDataBinding

/**
 *@author   open_9527
 *Create at 2021/5/18
 **/
object BindingVariableUtils {
    fun bindingVariable(binding: ViewDataBinding, bindingParams: SparseArray<*>) {
        var i = 0
        val length = bindingParams.size()
        while (i < length) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
            i++
        }
    }
}