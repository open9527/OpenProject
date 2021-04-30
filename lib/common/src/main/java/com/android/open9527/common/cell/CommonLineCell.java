package com.android.open9527.common.cell;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;

import com.android.open9527.common.BR;
import com.android.open9527.common.R;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.blankj.utilcode.util.SizeUtils;


public class CommonLineCell extends BaseBindingCell<CommonLineCell> {

    public final ObservableInt valueBgColor = new ObservableInt(R.color.common_line_color);
    public final ObservableInt valueViewHeight = new ObservableInt(SizeUtils.dp2px(10));

    public CommonLineCell() {
        super(R.layout.common_line_cell);
    }

    public CommonLineCell(int color, int height) {
        super(R.layout.common_line_cell);
        valueBgColor.set(color);
        valueViewHeight.set(height);
    }


    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);

    }
}
