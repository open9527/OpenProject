package com.open9527.wanandroid.pkg.main.share

import android.view.View
import com.android.open9527.common.binding.refresh.IRefresh
import com.android.open9527.common.net.data.response.DataResult
import com.android.open9527.common.page.BaseCommonFragment
import com.android.open9527.okhttp.OkHttpUtils
import com.android.open9527.okhttp.listener.OnHttpListener
import com.android.open9527.page.DataBindingConfig
import com.android.open9527.recycleview.adapter.BaseBindingCell
import com.android.open9527.recycleview.adapter.BaseBindingCellAdapter
import com.android.open9527.recycleview.decoration.SpacesItemDecoration
import com.android.open9527.recycleview.layout_manager.WrapContentLinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.open9527.wanandroid.pkg.BR
import com.open9527.wanandroid.pkg.R
import com.open9527.wanandroid.pkg.net.DataVo
import com.scwang.smart.refresh.layout.api.RefreshLayout
import okhttp3.Call

/**
 * @author open_9527
 * Create at 2021/1/12
 */
class ShareFragment : BaseCommonFragment(), OnHttpListener<Any?> {
    private var mPage = 0
    private var mViewModel: ShareViewModel? = null
    override fun initViewModel() {
        mViewModel = getFragmentScopeViewModel(ShareViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.share_fragment, BR.vm, mViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.layoutManager, WrapContentLinearLayoutManager(mActivity))
            .addBindingParam(
                BR.itemDecoration,
                SpacesItemDecoration(mActivity).setFooterNoShowDivider(1)
                    .setParam(R.color.common_line_color, 10)
            )
            .addBindingParam(BR.adapter, BaseBindingCellAdapter<BaseBindingCell<*>>())
    }

    override fun initRequest() {
        super.initRequest()
        requestShare()
    }

    override fun initEvent() {
        super.initEvent()
        mViewModel!!.shareRequest.getShareLiveData().observe(viewLifecycleOwner,
            { dataResult: DataResult<DataVo> ->
                mViewModel!!.onCreateCells(mPage, dataResult.result.dataList)
            })
    }

    inner class ClickProxy {
        @JvmField
        var onRefreshListeners: IRefresh<Boolean?> = object : IRefresh<Boolean?> {
            override fun onRefresh(refreshLayout: RefreshLayout?, isRefresh: Boolean?) {
                mPage = if (isRefresh!!) 0 else ++mPage
                requestShare()
            }
        }

        @JvmField
        var shareClick = View.OnClickListener {


        }
    }

    private fun requestShare() {
        mViewModel!!.shareRequest.requestShare(mPage, OkHttpUtils.get(viewLifecycleOwner))
    }

    override fun onStart(call: Call) {
        LogUtils.i(TAG, "onStart")
        //        mViewModel.valueCloseHeaderOrFooter.set(false);
    }

    override fun onEnd(call: Call) {
        LogUtils.i(TAG, "onEnd")
        //        mViewModel.valueCloseHeaderOrFooter.set(true);
    }

    companion object {
        fun newInstance(): ShareFragment {
            return ShareFragment()
        }
    }
}