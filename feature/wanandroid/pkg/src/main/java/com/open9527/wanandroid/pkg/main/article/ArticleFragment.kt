package com.open9527.wanandroid.pkg.main.article

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
import com.open9527.wanandroid.pkg.BR
import com.open9527.wanandroid.pkg.R
import com.open9527.wanandroid.pkg.net.BannerVo
import com.open9527.wanandroid.pkg.net.DataVo
import com.scwang.smart.refresh.layout.api.RefreshLayout

/**
 * @author open_9527
 * Create at 2021/1/12
 */
class ArticleFragment : BaseCommonFragment(), OnHttpListener<Any?> {
    private var mPage = 0
    private var mViewModel: ArticleViewModel? = null
    override fun initViewModel() {
        mViewModel = getFragmentScopeViewModel(ArticleViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.article_fragment, BR.vm, mViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.layoutManager, WrapContentLinearLayoutManager(mActivity))
            .addBindingParam(
                BR.itemDecoration,
                SpacesItemDecoration(mActivity).setParam(R.color.common_line_color, 10)
            )
            .addBindingParam(BR.adapter, BaseBindingCellAdapter<BaseBindingCell<*>>())
    }

    override fun initRequest() {
        super.initRequest()
        requestBanner()
    }

    override fun initEvent() {
        super.initEvent()
        mViewModel!!.articleRequest.bannerLiveData.observe(
            viewLifecycleOwner,
            { listDataResult: DataResult<List<BannerVo?>?> ->
                mViewModel!!.onBanner(mPage, listDataResult.result)
                requestArticle()
            })
        mViewModel!!.articleRequest.articleLiveData.observe(
            viewLifecycleOwner,
            { dataVoDataResult: DataResult<DataVo> ->
                dataVoDataResult.result.dataList?.let {
                    mViewModel!!.onCreateCells(
                        mPage,
                        it
                    )
                }
            })
    }

    private fun requestBanner() {
        mViewModel!!.articleRequest.requestBanner(OkHttpUtils.get(this))
    }

    private fun requestArticle() {
        mViewModel!!.articleRequest.requestArticle(mPage, OkHttpUtils.get(this))
    }

    inner class ClickProxy {
        @JvmField
        var onRefreshListeners: IRefresh<Boolean?> = object : IRefresh<Boolean?> {
            override fun onRefresh(refreshLayout: RefreshLayout?, isRefresh: Boolean?) {
                mPage = if (isRefresh!!) 0 else ++mPage
                if (isRefresh) {
                    requestBanner()
                } else {
                    requestArticle()
                }
                refreshLayout?.closeHeaderOrFooter()
            }
        }
    }

    companion object {
        fun newInstance(): ArticleFragment {
            return ArticleFragment()
        }
    }
}