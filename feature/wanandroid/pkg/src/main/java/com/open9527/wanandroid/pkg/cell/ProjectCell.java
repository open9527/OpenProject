package com.open9527.wanandroid.pkg.cell;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.open9527.wanandroid.pkg.BR;
import com.open9527.wanandroid.pkg.R;
import com.open9527.wanandroid.pkg.main.h5.H5Activity;
import com.open9527.wanandroid.pkg.net.ContentVo;

/**
 * @author open_9527
 * Create at 2021/1/15
 **/
public class ProjectCell extends BaseBindingCell<ProjectCell> {

    public final ObservableField<String> valueTitle = new ObservableField<>();
    public final ObservableField<String> valueImageUrl = new ObservableField<>();
    private final ObservableField<String> valueLink = new ObservableField<>();
    public final ObservableField<String> valueShareUser = new ObservableField<>();
    public final ObservableField<String> valueNiceShareDate = new ObservableField<>();


    public ProjectCell(ContentVo contentVo) {
        super(R.layout.project_cell);
        valueTitle.set(contentVo.getTitle());
        valueLink.set(contentVo.getLink());
        valueImageUrl.set(contentVo.getEnvelopePic());
        if (TextUtils.isEmpty(contentVo.getShareUser())) {
            valueShareUser.set("作者:" + contentVo.getAuthor());
        } else {
            valueShareUser.set("分享人:" + contentVo.getShareUser());
        }

        valueNiceShareDate.set(contentVo.getNiceShareDate());
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }

    @Override
    public void onCellClick(View view, ProjectCell projectCell) {
        H5Activity.startH5(projectCell.valueLink.get(),projectCell.valueTitle.get());
    }
}
