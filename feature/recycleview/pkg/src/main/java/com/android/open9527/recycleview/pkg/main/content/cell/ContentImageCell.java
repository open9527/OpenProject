package com.android.open9527.recycleview.pkg.main.content.cell;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.android.open9527.recycleview.pkg.BR;
import com.android.open9527.recycleview.pkg.R;
import com.android.open9527.recycleview.pkg.main.content.bean.ContentBean;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class ContentImageCell extends BaseBindingCell<ContentImageCell> {
    public final ObservableField<RoundedCornersTransformation.CornerType> valueCornerType = new ObservableField<>(RoundedCornersTransformation.CornerType.ALL);

    public final ObservableField<String> valueTitle = new ObservableField<>("图片稿件");
    public final ObservableField<String> valueContent = new ObservableField<>("去年自然灾害造成直接经济损失占GDP比重较近5年均值下降24%");
    public final ObservableField<String> valueImageUrl = new ObservableField<>("https://hk.storage.shmedia.tech/1a599a175bd6474185bd35c91e8521c7.png");

    public ContentImageCell() {
        super(R.layout.content_image_cell);
    }

    public ContentImageCell(ContentBean contentBean) {
        super(R.layout.content_image_cell);
        valueTitle.set(contentBean.getTitle());
        valueContent.set(contentBean.getContent());
        valueImageUrl.set(contentBean.getImageUrl());
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }

    @Override
    public void onCellClick(View view, ContentImageCell contentTextCell) {

    }
}
