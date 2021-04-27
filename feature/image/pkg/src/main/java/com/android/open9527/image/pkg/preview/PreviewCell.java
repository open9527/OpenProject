package com.android.open9527.image.pkg.preview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.android.open9527.image.pkg.BR;
import com.android.open9527.image.pkg.R;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;

/**
 * @author open_9527
 * Create at 2021/1/25
 **/
public class PreviewCell extends BaseBindingCell<PreviewCell> {

    public final ObservableField<String> valueImageUrl = new ObservableField<>();

    private final ObservableField<ICellClick> valueICellClick = new ObservableField<>();

    PreviewCell(String url, ICellClick iCellClick) {
        super(R.layout.preview_cell);
        valueImageUrl.set(url);
        valueICellClick.set(iCellClick);

    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }

    @Override
    public void onCellClick(View view, PreviewCell previewCell) {
        ICellClick iCellClick = valueICellClick.get();
        if (iCellClick != null) {
            iCellClick.onCellClick();
        }
    }

    @Override
    public boolean onCellLongClick(View view, PreviewCell previewCell) {
        ICellClick iCellClick = valueICellClick.get();
        if (iCellClick != null) {
            iCellClick.onDownload(valueImageUrl.get());
        }

        return true;
    }

    @Override
    public String getUUID() {
        return valueImageUrl.get();
    }

    public interface ICellClick {
        void onCellClick();

        void onDownload(String url);
    }

}
