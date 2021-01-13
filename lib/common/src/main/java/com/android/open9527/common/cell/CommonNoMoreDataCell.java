package com.android.open9527.common.cell;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.android.open9527.common.BR;
import com.android.open9527.common.R;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;


public class CommonNoMoreDataCell extends BaseBindingCell<CommonNoMoreDataCell> {

    public final ObservableField<String> valueTitle = new ObservableField<>("无更多数据~");

    public CommonNoMoreDataCell() {
        super(R.layout.common_no_more_data_cell);
    }

    public CommonNoMoreDataCell(String string) {
        super(R.layout.common_no_more_data_cell);
        valueTitle.set(string);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }
}
