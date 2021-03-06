package com.android.open9527.page;

import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class DataBindingConfig {

    private final int layout;

    private final int vmVariableId;

    private final ViewModel stateViewModel;

    private SparseArray<Object> bindingParams = new SparseArray<>();

    public DataBindingConfig(@NonNull Integer layout,
                             @NonNull Integer vmVariableId,
                             @NonNull ViewModel stateViewModel) {
        this.layout = layout;
        this.vmVariableId = vmVariableId;
        this.stateViewModel = stateViewModel;
    }

    public int getLayout() {
        return layout;
    }

    public int getVmVariableId() {
        return vmVariableId;
    }

    public ViewModel getStateViewModel() {
        return stateViewModel;
    }

    public SparseArray getBindingParams() {
        return bindingParams;
    }

    public DataBindingConfig addBindingParam(@NonNull Integer variableId,
                                             @NonNull Object object) {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, object);
        }
        return this;
    }

}
