package com.android.open9527.page;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public abstract class DataBindingActivity extends AppCompatActivity {

    private ViewDataBinding mBinding;

    protected abstract void initViewModel();

    protected abstract DataBindingConfig getDataBindingConfig();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();

        DataBindingConfig dataBindingConfig = getDataBindingConfig();

        ViewDataBinding binding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        binding.setLifecycleOwner(this);
        binding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getStateViewModel());

        BindingVariableUtils.bindingVariable(binding, dataBindingConfig.getBindingParams());

        mBinding = binding;
    }

    //警惕使用。非必要情况下，尽可能不在子类中拿到 binding 实例乃至获取 view 实例
    protected ViewDataBinding getBinding() {
        return mBinding;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.unbind();
        mBinding = null;
    }
}
