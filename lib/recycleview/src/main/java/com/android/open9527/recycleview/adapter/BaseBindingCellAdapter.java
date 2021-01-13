package com.android.open9527.recycleview.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class BaseBindingCellAdapter<CELL extends BaseBindingCell> extends RecyclerView.Adapter<BaseBindingCellViewHolder> {

    public List<CELL> mItems = new ArrayList<>();
    private RecyclerView mRecyclerView;

    public BaseBindingCellAdapter() {
        this(false);
    }

    public BaseBindingCellAdapter(boolean hasStableIds) {
        setHasStableIds(hasStableIds);
    }

    @Override
    public final int getItemViewType(int position) {
        CELL item = mItems.get(position);
        item.mAdapter = this;
        return item.getViewType();
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).getItemId();
    }

    @NonNull
    @Override
    public BaseBindingCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CELL.onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(@NonNull BaseBindingCellViewHolder holder, int position) {
        mItems.get(position).bindViewHolder(holder, position);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingCellViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
            return;
        }
        mItems.get(position).partialUpdate(payloads);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onViewRecycled(@NonNull BaseBindingCellViewHolder holder) {
        super.onViewRecycled(holder);
        int position = holder.getAdapterPosition();
        if (position < 0 || position >= mItems.size()) {
            return;
        }
        mItems.get(position).onViewRecycled(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /*----------------------------------------operate-------------------------------------------------------*/

    protected List<CELL> getItems() {
        return Collections.unmodifiableList(mItems);
    }

    public CELL getItem(@IntRange(from = 0) final int position) {
        return mItems.get(position);
    }

    public boolean isEmpty() {
        return mItems.isEmpty();
    }

    /*----------------------------------------submit-------------------------------------------------------*/

    private void setItems(@NonNull final List<CELL> items) {
        mItems = items;
    }

    private void sortItems(@NonNull final Comparator<CELL> comparator) {
        Collections.sort(mItems, comparator);
    }

    private void replaceItems(@NonNull final List<CELL> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    private void updateItems(@IntRange(from = 0) final int index, int itemCount, Object payload) {
        notifyItemRangeChanged(index, itemCount, payload);
    }


    public void submitItems(@NonNull final List<CELL> items) {
        if (this.mItems == items) {
            replaceItems(items);
            Log.i("submitItems", "Refresh");
        } else {
            int currentSize = mItems.size();
            mItems.addAll(items);
            updateItems(currentSize - 1, items.size(), items);
            Log.i("submitItems", "Add");
        }
    }
}