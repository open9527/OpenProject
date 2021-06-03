package com.android.open9527.page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.android.open9527.page.BindingVariableUtils.bindingVariable

/**
 * @author   open_9527
 * Create at 2021/5/18
 */
abstract class DataBindingFragment : Fragment() {
    protected var mActivity: AppCompatActivity? = null

    //警惕使用。非必要情况下，尽可能不在子类中拿到 binding 实例乃至获取 view 实例
    protected var mBinding: ViewDataBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    protected abstract fun initViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    protected abstract fun getDataBindingConfig(): DataBindingConfig


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBindingConfig = getDataBindingConfig()
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            dataBindingConfig.layout,
            container,
            false
        )
        binding.lifecycleOwner = this

        binding.setVariable(dataBindingConfig.vmVariableId, dataBindingConfig.stateViewModel)

        bindingVariable(binding, dataBindingConfig.getBindingParams())

        this.mBinding = binding

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding!!.unbind()
        mBinding = null
    }
}