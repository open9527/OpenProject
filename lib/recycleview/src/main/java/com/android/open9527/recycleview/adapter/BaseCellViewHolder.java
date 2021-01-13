package com.android.open9527.recycleview.adapter;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class BaseCellViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> viewArray = new SparseArray<>();

    public BaseCellViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(@IdRes final int viewId) {
        View view = viewArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewArray.put(viewId, view);
        }
        return (T) view;
    }
}