package com.open9527.wanandroid.pkg.main.project.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.open9527.common.binding.refresh.IRefresh
import com.android.open9527.common.bundle.BaseBundleData
import com.android.open9527.common.bundle.BundleUtils
import com.android.open9527.common.callback.SharedViewModel
import com.android.open9527.common.page.BaseCommonFragment
import com.android.open9527.okhttp.OkHttpUtils
import com.android.open9527.page.DataBindingConfig
import com.android.open9527.recycleview.adapter.BaseBindingCell
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.android.open9527.recycleview.decoration.SpacesItemDecoration
import com.android.open9527.recycleview.layout_manager.WrapContentLinearLayoutManager
import com.open9527.wanandroid.pkg.BR
import com.open9527.wanandroid.pkg.R
import com.open9527.wanandroid.pkg.cell.ProjectCell
import com.open9527.wanandroid.pkg.main.h5.H5Activity
import com.scwang.smart.refresh.layout.api.RefreshLayout

/**
 * @author open_9527
 * Create at 2021/1/18
 */
class ProjectContentFragment : BaseCommonFragment() {
    private var mPage = 0
    private var mViewModel: ProjectContentViewModel? = null
    private var mSharedViewModel: SharedViewModel? = null
    private var mBundleData: BundleData? = null
    override fun initViewModel() {
        mViewModel = getFragmentScopeViewModel(ProjectContentViewModel::class.java)
        mSharedViewModel = getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.project_content_fragment, BR.vm, mViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.layoutManager, WrapContentLinearLayoutManager(mActivity))
            .addBindingParam(
                BR.itemDecoration,
                SpacesItemDecoration(mActivity).setParam(R.color.common_line_color, 10)
            )
            .addBindingParam(BR.adapter, BaseBindingCellListAdapter<BaseBindingCell<*>>())
    }

    override fun initView(bundle: Bundle?) {
        super.initView(bundle)
        //        mBundleData =  getBundleData();
        mBundleData = BundleUtils.getBundleData(bundle)
//        requireNotNull(mBundleData) { "mBundleData is null" }
        mViewModel!!.valueICellClick.set(iCellClick)
        mViewModel!!.valueTitle.set(mBundleData!!.title)
    }

    override fun initRequest() {
        super.initRequest()
        requestProject()
    }

    override fun initEvent() {
        super.initEvent()

        mViewModel!!.projectRequest.projectLiveData.observe(viewLifecycleOwner, Observer {
            it.result.dataList?.let { it1 ->
                mViewModel!!.onCreateCells(
                    mPage,
                    it1
                )
            }

        })
    }

    private fun requestProject() {
        mViewModel!!.projectRequest.requestProject(mPage, mBundleData!!.id, OkHttpUtils.get(this))
    }

    private class BundleData(val id: String, val title: String) : BaseBundleData()
    inner class ClickProxy {
        @JvmField
        var onRefreshListeners: IRefresh<Boolean?> = object : IRefresh<Boolean?> {
            override fun onRefresh(refreshLayout: RefreshLayout?, isRefresh: Boolean?) {
                mPage = if (isRefresh!!) 0 else ++mPage
                requestProject()
            }
        }
    }


    private val iCellClick: ProjectCell.ICellClick = object : ProjectCell.ICellClick {
        override fun onImageClick(view: View?, url: String?) {
            if (url != null) {
                H5Activity.startH5(url, " ")
            }

        }

        override fun onContentClick(title: String?, link: String?) {
            if (link != null) {
                H5Activity.startH5(link, title)
            }
//                Router.getsInstance()
//                    .build("/webview/WebViewActivity")
//                    .withBundle(
//                        BundleUtils.createBundleJson(
//                            H5Bundle(
//                                link!!,
//                                title!!
//                            )
//                        )
//                    )
//                    .navigation(mActivity)
        }
    }


    companion object {
        fun newInstance(cId: String, title: String): ProjectContentFragment {
            val fragment = ProjectContentFragment()
            fragment.arguments = BundleUtils.create(
                BundleData(
                    cId,
                    title
                )
            )
            return fragment
        }
    }
}