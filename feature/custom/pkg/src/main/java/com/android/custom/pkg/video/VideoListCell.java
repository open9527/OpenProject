package com.android.custom.pkg.video;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.databinding.VideoListCellBinding;
import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.net.glide.ImageLoadUtils;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.android.custom.pkg.video.exo.player.ListPlayerView;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.request.target.Target;

/**
 * @author open_9527
 * Create at 2021/5/11
 **/
public class VideoListCell extends BaseBindingCell<VideoListCell> {


    public final ObservableField<String> valueVideoUrl = new ObservableField<>();
    public final ObservableField<String> valueTitle = new ObservableField<>();
    public final ObservableField<String> valueCoverUrl = new ObservableField<>();

    public VideoListCell(String url, String title, String coverUrl) {
        super(R.layout.video_list_cell);
        valueVideoUrl.set(url);
        valueTitle.set(title);
        valueCoverUrl.set(coverUrl);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
//        ListPlayerView listPlayerView = holder.itemView.findViewById(R.id.list_player);
        ListPlayerView listPlayerView = ((VideoListCellBinding)holder.getBinding()).listPlayer;
        ImageLoadUtils.loadImage(ImageLoadConfig.with(listPlayerView.cover)
                .setUrl(valueCoverUrl.get())
                .setWidth(Target.SIZE_ORIGINAL)
                .setHeight(Target.SIZE_ORIGINAL)
        );

        setBindingListPlayerView(listPlayerView,
                valueCoverUrl.get()+position,
                ScreenUtils.getScreenWidth(), ScreenUtils.getScreenWidth() * 16 / 9, valueCoverUrl.get(), valueVideoUrl.get());
    }

    @Override
    public void onCellClick(@NonNull View view, @NonNull VideoListCell videoListCell) {

    }





    @BindingAdapter(value = {
            "playerCategory",
            "playerWidthPx",
            "playerHeightPx",
            "playerCoverUrl",
            "playerVideoUrl",
    }, requireAll = false)
    public static void setBindingListPlayerView(ListPlayerView playerView, String category, int widthPx, int heightPx, String coverUrl, String videoUrl) {
        if (playerView == null) return;
        playerView.bindData(category, widthPx, heightPx, coverUrl, videoUrl);
    }

}
