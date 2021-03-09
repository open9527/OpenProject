package com.android.open9527.recycleview.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
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

    private List<CELL> mItems = new ArrayList<>();
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
            Log.i("BaseBindingCellAdapter", "position < 0 || position >= mItems.size()");
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

    private void updateItems(@IntRange(from = 0) final int index, int itemCount, Object payload) {
        notifyItemRangeChanged(index, itemCount, payload);
    }

    private void sortItems(@NonNull final Comparator<CELL> comparator) {
        Collections.sort(mItems, comparator);
    }

    private void setItems(@NonNull final List<CELL> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    private void addItems(@NonNull final List<CELL> items) {
        if (items.size() == 0) {
            return;
        }
        if (this.mItems == null || this.mItems.size() == 0) {
            setItems(items);
        } else {

            this.mItems.addAll(items);
            notifyItemRangeInserted(this.mItems.size() - items.size(), items.size());
        }
    }


    public void submitItems(@NonNull final List<CELL> items, boolean isRefresh) {
        if (items.size() == 0) {
            return;
        }
        if (this.mItems == null || this.mItems.size() == 0 || isRefresh) {
            Log.i("submitItems", "Add");
            this.mItems = items;
            notifyDataSetChanged();
        } else {
            Log.i("submitItems", "Update");
            this.mItems = items;
//            notifyItemRangeChanged(0, items.size(), items.size());
            notifyItemRangeChanged(0, mItems.size(), mItems.size());
        }
    }

    public void submitItems(@NonNull final ObservableList<CELL> items, boolean isRefresh) {
        if (items.size() == 0) {
            return;
        }
        mItems = items;
        if (isRefresh) {
            Log.i("submitItems", "Add");
            notifyDataSetChanged();
//            notifyItemRangeInserted(0, mItems.size());
        } else {
            Log.i("submitItems", "Update");
            notifyItemRangeChanged(0, mItems.size(), mItems.size());
        }
    }
}