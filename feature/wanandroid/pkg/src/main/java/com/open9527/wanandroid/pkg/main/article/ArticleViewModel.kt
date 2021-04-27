package com.open9527.wanandroid.pkg.main.article

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.android.open9527.common.cell.CommonEmptyCell
import com.android.open9527.recycleview.adapter.BaseBindingCell
import com.blankj.utilcode.util.CollectionUtils
import com.blankj.utilcode.util.LogUtils
import com.open9527.wanandroid.pkg.cell.BannerCell
import com.open9527.wanandroid.pkg.cell.ShareContentTextCell
import com.open9527.wanandroid.pkg.net.BannerVo
import com.open9527.wanandroid.pkg.net.ContentVo
import com.open9527.wanandroid.pkg.net.article.ArticleRequest

/**
 * @author open_9527
 * Create at 2021/1/12
 */
class ArticleViewModel : ViewModel() {
    @JvmField
    val valueTitle = ObservableField("article")

    @JvmField
    val valueTitleBarBg = ObservableField<Drawable>(ColorDrawable(Color.WHITE))

    @JvmField
    val valueNoMoreData = ObservableBoolean(false)


    @JvmField
    var valueCells = ObservableArrayList<BaseBindingCell<*>>()

    @JvmField
    val articleRequest = ArticleRequest()
    fun onBanner(page: Int, bannerVos: List<BannerVo?>?) {
        if (page == 0 && valueCells.size > 0) valueCells.clear()
        valueCells.add(BannerCell(bannerVos))
    }

    fun onCreateCells(page: Int, contentVos: List<ContentVo?>) {
        if (page == 0 && valueCells.size > 0) valueCells.clear()
        if (CollectionUtils.isNotEmpty(contentVos)) {
            for (contentVo in contentVos) {
                if (contentVo == null) continue
                valueCells.add(ShareContentTextCell(contentVo))
            }
//            valueNoMoreData.set(contentVos.size < 20)
            valueNoMoreData.set(false)
        } else {
            if (page == 0) valueCells.add(CommonEmptyCell())
            valueNoMoreData.set(true)
        }
    }
}