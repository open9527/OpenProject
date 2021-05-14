package com.android.video.pkg.tiktok;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.net.glide.ImageLoadUtils;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.android.video.export.component.TikTokView;
import com.android.video.pkg.BR;
import com.android.video.pkg.R;
import com.android.video.pkg.databinding.TikTokCellBinding;
import com.android.video.pkg.vo.TikTokVo;

/**
 * @author open_9527
 * Create at 2021/5/14
 **/
public class TikTokCell extends BaseBindingCell<TikTokCell> {


    public final ObservableField<String> valueVideoUrl = new ObservableField<>();
    public final ObservableField<String> valueVideoTitle = new ObservableField<>();
    public final ObservableField<String> valueVideoCoverUrl = new ObservableField<>();

    public final ObservableInt valueVideoIndex = new ObservableInt(-1);


    public TikTokCell(TikTokVo tikTokVo, int index) {
        super(R.layout.tik_tok_cell);
        valueVideoUrl.set(tikTokVo.getVideoUrl());
        valueVideoTitle.set(tikTokVo.getVideoTitle());
        valueVideoCoverUrl.set(tikTokVo.getVideoCoverImgUrl());
        valueVideoIndex.set(index);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
        TikTokView tikTokView = ((TikTokCellBinding) holder.getBinding()).tikTokView;
        TextView tikTokTitle = tikTokView.findViewById(R.id.tv_title);
        ImageLoadUtils.loadImage(ImageLoadConfig.with(tikTokView.findViewById(R.id.iv_thumb))
                .setSkipMemoryCache(false)
                .setUrl(valueVideoCoverUrl.get()));
        tikTokTitle.setText(valueVideoTitle.get());
        holder.itemView.setTag(this);
        holder.itemView.setTag(R.id.container, ((TikTokCellBinding) holder.getBinding()).container);
        holder.itemView.setTag(R.id.tik_tok_view, tikTokView);
    }

    @Override
    public String getUUID() {
        return valueVideoUrl.get();
    }
}
