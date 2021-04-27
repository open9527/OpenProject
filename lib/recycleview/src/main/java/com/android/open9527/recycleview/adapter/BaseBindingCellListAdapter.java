package com.android.open9527.recycleview.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/4/25
 **/
public class BaseBindingCellListAdapter<CELL extends BaseBindingCell> extends ListAdapter<CELL, BaseBindingCellViewHolder> {

    public BaseBindingCellListAdapter() {
        super(new DiffUtilCallbacks<CELL>().getCellItemCallback());
    }


    public BaseBindingCellListAdapter(@NonNull DiffUtil.ItemCallback<CELL> diffCallback) {
        super(diffCallback);
    }

    @Override
    public final int getItemViewType(int position) {
        CELL cell = getCurrentList().get(position);
//        cell.setAdapter(this);
        return cell.getViewType();
    }

    @Override
    public long getItemId(int position) {
        return getCurrentList().get(position).getItemId();
    }

    @NonNull
    @Override
    public BaseBindingCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CELL.onCreateViewHolder(parent, viewType);
    }


    @Override
    public final void onBindViewHolder(@NonNull BaseBindingCellViewHolder holder, int position) {
        getCurrentList().get(position).bindViewHolder(holder, position);
    }

    @Override
    public void onViewRecycled(@NonNull BaseBindingCellViewHolder holder) {
        super.onViewRecycled(holder);
        int position = holder.getBindingAdapterPosition();
        if (position < 0 || position >= getCurrentList().size()) {
            return;
        }
        getCurrentList().get(position).onViewRecycled(holder, position);
    }

    @NonNull
    @Override
    public List<CELL> getCurrentList() {
        return super.getCurrentList();
    }


    @Override
    public void submitList(@Nullable List<CELL> list) {
        super.submitList(list, () -> {
            super.submitList(list == null ? new ArrayList<>() : new ArrayList<>(list));
        });
    }
}