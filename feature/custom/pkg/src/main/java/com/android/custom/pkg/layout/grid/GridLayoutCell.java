package com.android.custom.pkg.layout.grid;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.databinding.GridLayoutCellBinding;
import com.android.open9527.common.widget.layout.NineGridLayout;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.android.open9527.recycleview.decoration.GridSpaceItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentGridLayoutManager;
import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/6/3
 **/
public class GridLayoutCell extends BaseBindingCell<GridLayoutCell> {

    public final ObservableField<String> valueUrl = new ObservableField<>();
    public final ObservableField<String> valueTitle = new ObservableField<>();
    public final ObservableField<String> valueDesc = new ObservableField<>();


    public GridLayoutCell(String imageUrl, int index) {
        super(R.layout.grid_layout_cell);
        valueUrl.set(imageUrl);
        valueTitle.set("这是一张或者多张得图片布局的标题" + index);
        valueDesc.set("这是一张或者多张得图片布局,我想给它来段不一样得描述,但是又不知道怎么去描述,那就这个样子吧,简简单单的,凑够一些字数就好了嘛" + index);
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
        GridLayoutCellBinding mBinding = (GridLayoutCellBinding) holder.getBinding();
        List<BaseBindingCell> mData = new ArrayList<>();
        for (int i = 0; i < position; i++) {
            mData.add(new GridLayoutItemCell(valueUrl.get(),i));
        }
        setGridLayout(mBinding.gridLayout, mData);

    }

    private void setGridLayout(NineGridLayout nineGridLayout, List<BaseBindingCell> list) {
        WrapContentGridLayoutManager gridLayoutManager = new WrapContentGridLayoutManager(nineGridLayout.getContext(), 6);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (list.size() == 1) {
                    return 6;
                } else if (list.size() == 2 || list.size() == 4) {
                    return 3;
                }
                return 2;
            }
        });
        NineGridLayout.newInstance(nineGridLayout.getContext())
//                .setRecyclerView(new RecyclerView(nineGridLayout.getContext()))
                .setLayoutManager(gridLayoutManager)
                .setItemDecoration(new GridSpaceItemDecoration(5))
                .setAdapter(new BaseBindingCellListAdapter())
                .setList(list)
                .into(nineGridLayout);
    }

    @Override
    public void onCellClick(@NonNull View view, @NonNull GridLayoutCell gridLayoutCell) {
        LogUtils.i(TAG, "onCellClick");
    }


}
