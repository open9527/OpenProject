package com.android.open9527.image.pkg.gif;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.android.open9527.image.pkg.R;
import com.android.open9527.image.pkg.load.ImageVo;
import com.android.open9527.page.BR;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;

/**
 * @author open_9527
 * Create at 2021/5/26
 **/
public class GifListCell extends BaseBindingCell<GifListCell> {

    public final ObservableField<String> valueUrl = new ObservableField<>();
    public final ObservableField<String> valueTitle = new ObservableField<>();
    public final ObservableField<String> valueDesc = new ObservableField<>();

    public GifListCell(ImageVo imageVo, int index) {
        super(R.layout.gif_list_cell);
        valueUrl.set(imageVo.getImageUrl());
        valueTitle.set("这是一张动图的标题" + index);
        valueDesc.set("这是一张动图,我想给它来段不一样得描述,但是又不知道怎么去描述,那就这个样子吧,简简单单的,凑够一些字数就好了嘛" + index);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }

    @Override
    public void onCellClick(@NonNull View view, @NonNull GifListCell gifListCell) {

    }
}
