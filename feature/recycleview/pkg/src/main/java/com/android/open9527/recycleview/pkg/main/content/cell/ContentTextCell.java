package com.android.open9527.recycleview.pkg.main.content.cell;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.android.open9527.recycleview.pkg.BR;
import com.android.open9527.recycleview.pkg.R;
import com.android.open9527.recycleview.pkg.main.content.MainFragment;
import com.android.open9527.recycleview.pkg.main.content.bean.ContentBean;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class ContentTextCell extends BaseBindingCell<ContentTextCell> {

    public final ObservableField<String> valueTitle = new ObservableField<>("纯文本稿件");
    public final ObservableField<String> valueContent = new ObservableField<>("去年自然灾害造成直接经济损失占GDP比重较近5年均值下降24%");


    public ContentTextCell() {
        super(R.layout.content_text_cell);
    }

    public ContentTextCell(ContentBean contentBean) {
        super(R.layout.content_text_cell);
        valueTitle.set(contentBean.getTitle());
        valueContent.set(contentBean.getContent());
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }

    @Override
    public void onCellClick(View view, ContentTextCell contentTextCell) {

    }
}
