package com.android.open9527.dialog;

import android.util.SparseArray;

import androidx.annotation.NonNull;


public class DialogDataBindingConfig {

    private SparseArray<Object> bindingParams = new SparseArray<>();

    public DialogDataBindingConfig() {

    }

    public SparseArray getBindingParams() {
        return bindingParams;
    }

    public DialogDataBindingConfig addBindingParam(@NonNull Integer variableId,
                                                   @NonNull Object object) {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, object);
        }
        return this;
    }

}
