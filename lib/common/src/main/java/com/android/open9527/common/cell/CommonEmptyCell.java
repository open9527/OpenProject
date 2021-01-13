package com.android.open9527.common.cell;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.android.open9527.common.BR;
import com.android.open9527.common.R;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;


public class CommonEmptyCell extends BaseBindingCell<CommonEmptyCell> {

    public final ObservableInt valueIcon = new ObservableInt(R.drawable.common_empty_icon);
    public final ObservableField<String> valueHint = new ObservableField<>("暂无数据!");


    public CommonEmptyCell() {
        super(R.layout.common_empty_cell);
    }

    public CommonEmptyCell(int icon, String title) {
        super(R.layout.common_empty_cell);
        valueIcon.set(icon);
        valueHint.set(title);
    }


    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }

}
