package com.android.custom.pkg.layout.grid;

import android.view.View;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/6/3
 **/
public abstract class BaseNineGridAdapter<V extends View, E> implements NineGridAdapter<V, E> {

    private NineGridLayout<V, E> mNineGridLayout = null;

    private List<E> mDataList;

    public List<E> getDataList() {
        return mDataList;
    }

    public void setDataList(List<E> dataList) {
        this.mDataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged(){
        if (mNineGridLayout != null){
            mNineGridLayout.onLayoutPrepare();
        }
    }

    public void onAttachToLayout(NineGridLayout<V, E> layout){
        mNineGridLayout = layout;
    }
}
