package com.open9527.wanandroid.pkg.cell

import android.text.TextUtils
import android.view.View
import androidx.databinding.ObservableField
import com.android.open9527.image.export.api.ImageApi
import com.android.open9527.image.export.api.ImageBundle
import com.android.open9527.recycleview.adapter.BaseBindingCell
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder
import com.blankj.utilcode.util.ApiUtils
import com.blankj.utilcode.util.StringUtils
import com.open9527.wanandroid.pkg.BR
import com.open9527.wanandroid.pkg.R
import com.open9527.wanandroid.pkg.main.h5.H5Activity.Companion.startH5
import com.open9527.wanandroid.pkg.net.ContentVo

/**
 * @author open_9527
 * Create at 2021/1/15
 */
class ProjectCell(contentVo: ContentVo, iCellClick: ICellClick) :
    BaseBindingCell<ProjectCell>(R.layout.project_cell) {
    @JvmField
    val valueTitle = ObservableField<String?>()

    @JvmField
    val valueImageUrl = ObservableField<String?>()
    private val valueLink = ObservableField<String?>()

    @JvmField
    val valueShareUser = ObservableField<String>()

    @JvmField
    val valueNiceShareDate = ObservableField<String?>()
    private val valueICellClick = ObservableField<ICellClick?>()
    private val valueId =  ObservableField<String?>()
    override fun bind(holder: BaseBindingCellViewHolder<*>, position: Int) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, projectCell: ProjectCell) {
        val iCellClick = valueICellClick.get()
        if (view.id == R.id.iv_pic) {
            iCellClick?.onImageClick(view, projectCell.valueImageUrl.get())

        } else {
            projectCell.valueLink.get()?.let {
//                startH5(
//                    it,
//                    projectCell.valueTitle.get()
//                )
                iCellClick?.onContentClick(projectCell.valueTitle.get(), it)
            }
        }
    }

    interface ICellClick {

        fun onImageClick(view: View?, url: String?)

        fun onContentClick( title: String?, link: String?)
    }

    init {
        valueId.set(contentVo.id)
        valueTitle.set(contentVo.title)
        valueLink.set(contentVo.link)
        valueImageUrl.set(contentVo.envelopePic)
        valueICellClick.set(iCellClick)
        if (TextUtils.isEmpty(contentVo.shareUser)) {
            valueShareUser.set(
                String.format(
                    StringUtils.getString(R.string.wan_project_author),
                    contentVo.author
                )
            )
        } else {
            valueShareUser.set(
                String.format(
                    StringUtils.getString(R.string.wan_project_sharer),
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