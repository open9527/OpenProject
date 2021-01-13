package com.android.open9527.page;

import android.util.SparseArray;

import androidx.databinding.ViewDataBinding;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public final class BindingVariableUtils {


    public static void bindingVariable(ViewDataBinding binding, SparseArray bindingParams) {
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
    }
}
