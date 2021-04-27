package com.android.open9527.image.pkg.load

import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.android.open9527.common.net.glide.ImageCallBack
import com.android.open9527.common.widget.image.RoundImageType
import com.android.open9527.image.pkg.BR
import com.android.open9527.image.pkg.R
import com.android.open9527.recycleview.adapter.BaseBindingCell
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder

/**
 * @author open_9527
 * Create at 2021/1/25
 */
class ImageCell : BaseBindingCell<ImageCell> {
    @JvmField
    val valueCornerType = ObservableField(RoundImageType.CORNER_TYPE_ALL)

    @JvmField
    val valueRoundType = ObservableField(RoundImageType.ROUND_TYPE_ROUND)

    @JvmField
    val valueImageUrl = ObservableField<String>()

    @JvmField
    val valueImageUri = ObservableField<Uri>()

    @JvmField
    val valueICellClick = ObservableField<ICellClick>()

    val valueIndex = ObservableInt()

    constructor(imageVo: ImageVo, iCellClick: ICellClick) : super(R.layout.image_cell) {
        valueImageUrl.set(imageVo.imageUrl)
        valueICellClick.set(iCellClick)
    }

    constructor(uri: Uri) : super(R.layout.image_cell) {
        valueImageUri.set(uri)
    }


    override fun bind(holder: BaseBindingCellViewHolder<*>, position: Int) {
        holder.addBindingParam(BR.cell, this)
        valueIndex.set(position)
    }

    override fun onCellClick(view: View, imageCell: ImageCell) {
        val iCellClick = valueICellClick.get()
        iCellClick?.onCellClick(view, valueIndex.get())
    }

    interface ICellClick {
        fun onCellClick(view: View?, index: Int)
    }

    var imageCallBack: ImageCallBack = object : ImageCallBack {
        override fun onStart() {}
        override fun onStop() {}
        override fun onResourceReady(resource: Bitmap) {}
    }

    override fun getUUID(): String? {
        if (valueImageUri.get() != null) {
            return valueImageUri.get().toString()
        }
        return valueImageUrl.get()
    }
}