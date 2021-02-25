package com.open9527.wanandroid.pkg.main.project

import com.android.open9527.common.net.data.response.DataResult
import com.android.open9527.common.page.BaseCommonFragment
import com.android.open9527.okhttp.OkHttpUtils
import com.android.open9527.page.DataBindingConfig
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.open9527.wanandroid.pkg.BR
import com.open9527.wanandroid.pkg.R
import com.open9527.wanandroid.pkg.net.project.ProjectTreeVo

/**
 * @author open_9527
 * Create at 2021/1/12
 */
class ProjectFragment : BaseCommonFragment() {
    private var mViewModel: ProjectViewModel? = null
    override fun initViewModel() {
        mViewModel = getFragmentScopeViewModel(ProjectViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.project_fragment, BR.vm, mViewModel!!)
    }

    override fun initRequest() {
        super.initRequest()
        requestProjectTree()
        LogUtils.i(TAG, "getStatusBarHeight: " + BarUtils.getStatusBarHeight())
        LogUtils.i(TAG, "getNavBarHeight: " + BarUtils.getNavBarHeight())
    }

    override fun initEvent() {
        super.initEvent()
        mViewModel!!.projectRequest.projectTreeLiveData.observe(
            viewLifecycleOwner,
            {
                    listDataResult: DataResult<List<ProjectTreeVo?>?> ->
                listDataResult.result?.let {
                    mViewModel!!.initTab(
                        childFragmentManager, it
                    )
                }
            })
    }

    private fun requestProjectTree() {
        mViewModel!!.projectRequest.requestProjectTreeApi(OkHttpUtils.get(this))
    }

    companion object {
        fun newInstance(): ProjectFragment {
            return ProjectFragment()
        }
    }
}