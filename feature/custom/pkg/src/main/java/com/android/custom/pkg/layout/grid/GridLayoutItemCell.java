package com.android.custom.pkg.layout.grid;

import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.recyclerview.widget.RecyclerView;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.dialog.PreviewImageDialog;
import com.android.open9527.common.widget.image.LoadImageView;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/6/4
 **/
public class GridLayoutItemCell extends BaseBindingCell<GridLayoutItemCell> {
    public final ObservableField<String> valueUrl = new ObservableField<>();
    public final ObservableInt valueIndex = new ObservableInt(0);

    public GridLayoutItemCell(String imageUrl, int index) {
        super(R.layout.grid_layout_item_cell);
        valueUrl.set(imageUrl);
        valueIndex.set(index);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }

    @Override
    public void onCellClick(@NonNull View view, @NonNull GridLayoutItemCell gridLayoutItemCell) {
        ViewParent parent = view.getParent();
        if (parent instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) parent;
            int count = recyclerView.getAdapter().getItemCount();
            List<String> urlList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                urlList.add("https://fs.res-storage.shmedia.tech/1403777.jpg");
            }
            PreviewImageDialog.with(view.getContext())
                    .setData(urlList, valueIndex.get())
                    .setImageView(recyclerView.getChildAt(valueIndex.get()).findViewById(R.id.iv_pic))
                    .setListener((index, dialog) -> {
                        dialog.updateImageView(recyclerView.getChildAt(index).findViewById(R.id.iv_pic));
                    })
                    .showDialog();
        }
    }
}
