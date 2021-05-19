package com.android.open9527.page;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public abstract class DataBindingFragment extends Fragment {

    protected AppCompatActivity mActivity;

    private ViewDataBinding mBinding;

    protected abstract void initViewModel();

    protected abstract DataBindingConfig getDataBindingConfig();


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        DataBindingConfig dataBindingConfig = getDataBindingConfig();
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, dataBindingConfig.getLayout(), container, false);
        binding.setLifecycleOwner(this);
        binding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getStateViewModel());

        BindingVariableUtils.bindingVariable(binding, dataBindingConfig.getBindingParams());

        mBinding = binding;
        return binding.getRoot();
    }

    //警惕使用。非必要情况下，尽可能不在子类中拿到 binding 实例乃至获取 view 实例
    protected ViewDataBinding getBinding() {
        return mBinding;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding.unbind();
        mBinding = null;
    }
}
