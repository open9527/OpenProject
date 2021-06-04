package com.android.custom.pkg.layout.grid;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.blankj.utilcode.util.LogUtils;

/**
 * @author open_9527
 * Create at 2021/6/4
 **/
public class GridLayoutItemCell extends BaseBindingCell<GridLayoutItemCell> {
    public final ObservableField<String> valueUrl = new ObservableField<>();

    public GridLayoutItemCell(String imageUrl) {
        super(R.layout.grid_layout_item_cell);
        valueUrl.set(imageUrl);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell,this);
    }

    @Override
    public void onCellClick(@NonNull View view, @NonNull GridLayoutItemCell gridLayoutItemCell) {
        LogUtils.i(TAG,"onCellClick");
    }
}
