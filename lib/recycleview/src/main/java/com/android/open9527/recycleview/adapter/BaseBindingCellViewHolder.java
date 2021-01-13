package com.android.open9527.recycleview.adapter;


import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class BaseBindingCellViewHolder<BINDING extends ViewDataBinding> extends BaseCellViewHolder {

    private BINDING mBinding;

    public BaseBindingCellViewHolder(@NonNull BINDING binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    public void addBindingParam(@NonNull Integer variableId, @NonNull Object object) {
        mBinding.setVariable(variableId, object);
    }

//    public BINDING getBinding(@NonNull View itemView) {
//        return DataBindingUtil.getBinding(itemView);
//    }
}
