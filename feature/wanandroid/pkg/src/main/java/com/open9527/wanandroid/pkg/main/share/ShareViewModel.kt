package com.open9527.wanandroid.pkg.main.share

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
import com.open9527.wanandroid.pkg.cell.ShareContentTextCell
import com.open9527.wanandroid.pkg.net.ContentVo
import com.open9527.wanandroid.pkg.net.share.ShareRequest

/**
 * @author open_9527
 * Create at 2021/1/12
 */
class ShareViewModel : ViewModel() {
    @JvmField
    val valueTitle = ObservableField("share")

    @JvmField
    val valueTitleBarBg = ObservableField<Drawable>(ColorDrawable(Color.TRANSPARENT))

    @JvmField
    val valueNoMoreData = ObservableBoolean(false)



    @JvmField
    var valueCells = ObservableArrayList<BaseBindingCell<*>>()

    @JvmField
    val shareRequest = ShareRequest()

    fun onCreateCells(page: Int, contentVos: List<ContentVo>?) {
        if (page == 0 && valueCells.size > 0) valueCells.clear()
        if (CollectionUtils.isNotEmpty(contentVos) && contentVos != null) {
            for (contentVo in contentVos) {
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