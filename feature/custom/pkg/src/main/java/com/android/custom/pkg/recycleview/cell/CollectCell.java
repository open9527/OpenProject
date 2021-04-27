package com.android.custom.pkg.recycleview.cell;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;

import com.android.custom.pkg.R;
import com.android.custom.pkg.recycleview.user.ContentVo;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;

/**
 * @author open_9527
 * Create at 2021/4/26
 **/
public class CollectCell extends BaseBindingCell<CollectCell> {
    private final ObservableField<String> valueId = new ObservableField<>();
    public final ObservableField<String> valueTitle = new ObservableField<>();
    public final ObservableField<String> valueDesc = new ObservableField<>();

    public CollectCell(ContentVo contentVo) {
        super(R.layout.collect_item);
        valueTitle.set(contentVo.getTitle());
        valueDesc.set(contentVo.getDesc());
        valueId.set(contentVo.getId());
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }

    @Override
    public void onCellClick(@NonNull View view, @NonNull CollectCell collectCell) {

    }

    @Override
    public String getUUID() {
        return valueId.get();
    }
}
