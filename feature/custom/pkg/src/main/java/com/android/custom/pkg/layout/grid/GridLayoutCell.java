package com.android.custom.pkg.layout.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.databinding.GridLayoutCellBinding;
import com.android.custom.pkg.databinding.GridLayoutItemBinding;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.blankj.utilcode.util.SizeUtils;

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
        List<String> mData = new ArrayList<>();
        for (int i = 0; i < (position + 1); i++) {
            mData.add("test" + i);
        }

//        setGridLayout(mBinding.gridLayout,mData);
        setNineGridLayout(mBinding.gridLayout, mData);
    }


    @Override
    public void onCellClick(@NonNull View view, @NonNull GridLayoutCell gridLayoutCell) {

    }


    private void setNineGridLayout(NineGridLayout gridLayout, List<String> list) {
        BaseNineGridAdapter<View, String> mAdapter;
//        gridLayout.setMaxChildCount(9);
        gridLayout.setColumnCount(getSpan(list.size()));
        gridLayout.setRowCount(getSpan(list.size()));
        gridLayout.setMode(NineGridLayout.MODE_GRID);
        gridLayout.setGridSpace(3);

        gridLayout.setAdapter(mAdapter = new BaseNineGridAdapter<View, String>() {
            @Override
            public View onCreateChildView(Context context, NineGridLayout parent, int position) {
                return LayoutInflater.from(context).inflate(R.layout.grid_layout_item, parent, false);
            }

            @Override
            public void onBindData(Context context, View childView, String data, int position) {
                GridLayoutItemBinding mBinding = DataBindingUtil.bind(childView);
                mBinding.setValueUrl(valueUrl.get());
            }
        });
        mAdapter.setDataList(list);

    }

    private void setGridLayout(XGridLayout gridLayout, List<String> list) {
        gridLayout.setGridSpan(getSpan(list.size()));
//        gridLayout.setMaxItem((9));
//        gridLayout.setIsSquare(false);
//        gridLayout.setHorizontalSpace(3);
//        gridLayout.setVerticalSpace(3);

        gridLayout.setAdapter(new GridLayoutAdapter<String>(gridLayout.getContext(), list, R.layout.grid_layout_item) {
            @Override
            public void convert(BaseBindingCellViewHolder holder, String item, int position) {
                GridLayoutItemBinding mBinding = (GridLayoutItemBinding) holder.getBinding();
                mBinding.setValueUrl(valueUrl.get());
            }
        });
    }

    private int getSpan(int size) {
        if (size == 1) {
            return 1;
        } else if (size == 2 || size == 4) {
            return 2;
        }
        return 3;
    }


}
