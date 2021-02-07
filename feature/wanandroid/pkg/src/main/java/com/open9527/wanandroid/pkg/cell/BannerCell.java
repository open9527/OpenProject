package com.open9527.wanandroid.pkg.cell;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.open9527.wanandroid.pkg.BR;
import com.open9527.wanandroid.pkg.R;
import com.open9527.wanandroid.pkg.net.BannerVo;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class BannerCell extends BaseBindingCell<BannerCell> {

    public final ObservableList<BannerVo> observableList = new ObservableArrayList<>();

    public BannerCell(List<BannerVo> contentVoList) {
        super(R.layout.banner_cell);
        observableList.addAll(contentVoList);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }
}
