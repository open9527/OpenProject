package com.open9527.wanandroid.pkg.cell

import android.text.TextUtils
import android.view.View
import androidx.databinding.ObservableField
import com.android.feature.webview.export.api.WebApi
import com.android.feature.webview.export.api.WebBundle
import com.android.open9527.recycleview.adapter.BaseBindingCell
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder
import com.blankj.utilcode.util.ApiUtils
import com.blankj.utilcode.util.StringUtils
import com.open9527.wanandroid.pkg.BR
import com.open9527.wanandroid.pkg.R
import com.open9527.wanandroid.pkg.main.h5.H5Activity
import com.open9527.wanandroid.pkg.main.h5.H5Activity.Companion.startH5
import com.open9527.wanandroid.pkg.net.ContentVo
import java.util.*

/**
 * @author open_9527
 * Create at 2021/1/14
 */
class ShareContentTextCell(contentVo: ContentVo) :
    BaseBindingCell<ShareContentTextCell>(R.layout.share_content_text_cell) {
    @JvmField
    val valueTitle = ObservableField<String?>()
    private val valueLink = ObservableField<String?>()
    private val valueId =  ObservableField<String?>()
    @JvmField
    val valueShareUser = ObservableField<String>()

    @JvmField
    val valueNiceShareDate = ObservableField<String?>()
    override fun bind(holder: BaseBindingCellViewHolder<*>, position: Int) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, shareContentTextCell: ShareContentTextCell) {
        shareContentTextCell.valueLink.get()?.let {
            if (ApiUtils.getApi(WebApi::class.java).toString().contains("WebApiMockImpl")) {
                startH5(
                    it,
                    shareContentTextCell.valueTitle.get()
                )
            } else {
                ApiUtils.getApi(WebApi::class.java)
                    .startWeb(WebBundle(it, shareContentTextCell.valueTitle.get()))
            }
        }
    }

    init {
        valueId.set(contentVo.id)
        valueTitle.set(contentVo.title)
        valueLink.set(contentVo.link)
        if (TextUtils.isEmpty(contentVo.shareUser)) {
            valueShareUser.set(
                String.format(
                    StringUtils.getString(R.string.wan_share_author),
                    contentVo.author
                )
            )
        } else {
            valueShareUser.set(
                String.format(
                    StringUtils.getString(R.string.wan_share_sharer),
                    contentVo.shareUser
                )
            )
        }
        valueNiceShareDate.set(contentVo.niceShareDate)
    }

    override fun getUUID(): String? {
        return valueId.get()
    }
}