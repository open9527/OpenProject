package com.android.open9527.recycleview.adapter;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public abstract class BaseBindingCell<CELL extends BaseBindingCell> implements IBaseCellClick<CELL> {

    @SuppressWarnings("unchecked")
    protected final String TAG = ((CELL) this).getClass().getSimpleName();

    private static final SparseIntArray LAYOUT_SPARSE_ARRAY = new SparseIntArray();
    private static final SparseArray<View> VIEW_SPARSE_ARRAY = new SparseArray<>();

    static BaseBindingCellViewHolder<ViewDataBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutByType = LAYOUT_SPARSE_ARRAY.get(viewType, -1);
        if (layoutByType != -1) {
            return new BaseBindingCellViewHolder<>(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutByType, parent, false));
        }

        View viewByType = VIEW_SPARSE_ARRAY.get(viewType);
        if (viewByType != null) {
            ViewDataBinding viewDataBinding = DataBindingUtil.bind(viewByType);

            if (viewDataBinding != null) {
                return new BaseBindingCellViewHolder<>(viewDataBinding);

            }
        }
        throw new RuntimeException("onCreateViewHolder: get holder from view type failed.");
    }

    public abstract void bind(@NonNull final BaseBindingCellViewHolder holder, final int position);


    public void partialUpdate(List<Object> payloads) {
    }

    void bindViewHolder(@NonNull final BaseBindingCellViewHolder holder, final int position) {
        bind(holder, position);
    }


    public void onViewRecycled(@NonNull final BaseBindingCellViewHolder holder, final int position) {/**/}

    public long getItemId() {
        return RecyclerView.NO_ID;

    }

    private int viewType;
    BaseBindingCellAdapter<CELL> mAdapter;

    public BaseBindingCell(@LayoutRes int layoutId) {
        viewType = getViewTypeByLayoutId(layoutId);
        LAYOUT_SPARSE_ARRAY.put(viewType, layoutId);
    }

    public BaseBindingCell(@NonNull View view) {
        viewType = getViewTypeByView(view);
        VIEW_SPARSE_ARRAY.put(viewType, view);
    }

    public int getViewType() {
        return viewType;
    }

    public BaseBindingCellAdapter<CELL> getAdapter() {
        return mAdapter;
    }


    public boolean isViewType(@LayoutRes int layoutId) {
        return viewType == getViewTypeByLayoutId(layoutId);
    }

    public boolean isViewType(@NonNull View view) {
        return viewType == getViewTypeByView(view);
    }

    private int getViewTypeByLayoutId(@LayoutRes int layoutId) {
        return layoutId + getClass().hashCode();
    }

    private int getViewTypeByView(@NonNull View view) {
        return view.hashCode() + getClass().hashCode();
    }

    /*----------------------------------------operate-------------------------------------------------------*/

    //    public List<CELL> getItems() {
//        if (getAdapter() == null) return new ArrayList<>();
//        return getAdapter().getItems();
//    }
//
//    public int getCount() {
//        if (getAdapter() == null) return 0;
//        return getAdapter().getItemCount();
//    }
//
    @SuppressWarnings("unchecked")
    public int getIndex() {
        if (getAdapter() == null) return -1;
        return getAdapter().getItems().indexOf((CELL) this);
    }


    /*----------------------------------------Click-------------------------------------------------------*/
    @SuppressWarnings("unchecked")
    @Override
    public boolean onLongClick(View view) {
        onCellLongClick(view, (CELL) this);
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onClick(View view) {
        onCellClick(view, (CELL) this);
    }
}
