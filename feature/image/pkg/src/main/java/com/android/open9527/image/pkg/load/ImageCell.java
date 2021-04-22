package com.android.open9527.image.pkg.load;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.android.open9527.common.net.glide.ImageCallBack;
import com.android.open9527.common.widget.image.RoundImageType;
import com.android.open9527.image.pkg.BR;
import com.android.open9527.image.pkg.R;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author open_9527
 * Create at 2021/1/25
 **/
public class ImageCell extends BaseBindingCell<ImageCell> {

    public final ObservableField<String> valueCornerType = new ObservableField<>(RoundImageType.CORNER_TYPE_ALL);
    public final ObservableField<String> valueRoundType = new ObservableField<>(RoundImageType.ROUND_TYPE_ROUND);
    public final ObservableField<String> valueImageUrl = new ObservableField<>();
    public final ObservableField<Uri> valueImageUri = new ObservableField<>();

    public final ObservableField<ICellClick> valueICellClick = new ObservableField<>();

    public ImageCell(ImageVo imageVo, ICellClick iCellClick) {
        super(R.layout.image_cell);
        valueImageUrl.set(imageVo.getImageUrl());
        valueICellClick.set(iCellClick);
    }

    public ImageCell(Uri uri) {
        super(R.layout.image_cell);
        valueImageUri.set(uri);

    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }

    @Override
    public void onCellClick(View view, ImageCell imageCell) {
        ICellClick iCellClick = valueICellClick.get();
        if (iCellClick != null) {
            iCellClick.onCellClick(view, imageCell.getIndex());
        }

    }

    public interface ICellClick {
        void onCellClick(View view, int index);
    }

    public ImageCallBack imageCallBack = new ImageCallBack() {
        @Override
        public void onStart() {

        }

        @Override
        public void onStop() {

        }

        @Override
        public void onResourceReady(@NonNull Bitmap resource) {

        }
    };

}
