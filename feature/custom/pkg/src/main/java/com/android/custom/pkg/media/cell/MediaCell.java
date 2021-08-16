package com.android.custom.pkg.media.cell;


import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.common.widget.image.RoundImageType;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.blankj.utilcode.util.LogUtils;


/**
 * @author open_9527
 * Create at 2021/7/8
 **/
public class MediaCell extends BaseBindingCell<MediaCell> {

    public final ObservableField<String> valueCornerType = new ObservableField<>(RoundImageType.CORNER_TYPE_ALL);
    public final ObservableField<String> valueRoundType = new ObservableField<>(RoundImageType.ROUND_TYPE_ROUND);

    public final ObservableField<String> valueImageUrl = new ObservableField<>();
    public final ObservableField<String> valueDesc = new ObservableField<>();
    public final ObservableField<Uri> valueImageUri = new ObservableField<>();
    public final ObservableInt valueIndex = new ObservableInt();

    public MediaCell(Uri uri, String desc) {
        super(R.layout.media_cell);
//        valueImageUri.set(uri);
        valueImageUrl.set(uri.toString());
        valueDesc.set(desc);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }

//    @Override
//    public String getUUID() {
//        return valueDesc.get();
//    }
}
