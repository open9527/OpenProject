package com.open9527.wanandroid.pkg.cell

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.android.open9527.recycleview.adapter.BaseBindingCell
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder
import com.open9527.wanandroid.pkg.BR
import com.open9527.wanandroid.pkg.R
import com.open9527.wanandroid.pkg.net.BannerVo

/**
 * @author open_9527
 * Create at 2021/1/12
 */
class BannerCell(contentVoList: List<BannerVo?>?) :
    BaseBindingCell<BannerCell>(R.layout.banner_cell) {
    @JvmField
    val observableList: ObservableList<BannerVo> = ObservableArrayList()

    override fun bind(holder: BaseBindingCellViewHolder<*>, position: Int) {
        holder.addBindingParam(BR.cell, this)
    }

    init {
        observableList.addAll(contentVoList!!)
    }
}