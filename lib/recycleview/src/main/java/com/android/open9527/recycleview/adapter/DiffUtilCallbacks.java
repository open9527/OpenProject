package com.android.open9527.recycleview.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/**
 * @author open_9527
 * Create at 2021/4/25
 **/
public final class DiffUtilCallbacks<CELL> {

    public DiffUtil.ItemCallback<CELL> getCellItemCallback() {
        return new DiffUtil.ItemCallback<CELL>() {
            @Override
            public boolean areItemsTheSame(@NonNull CELL oldItem, @NonNull CELL newItem) {
                if (oldItem == null || newItem == null) return false;
                return ((BaseBindingCell) oldItem).getUUID().equals(((BaseBindingCell) newItem).getUUID());
            }

            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(@NonNull CELL oldItem, @NonNull CELL newItem) {
                if (oldItem == null || newItem == null) return false;
                return oldItem.equals(newItem);

            }
        };
    }
}
