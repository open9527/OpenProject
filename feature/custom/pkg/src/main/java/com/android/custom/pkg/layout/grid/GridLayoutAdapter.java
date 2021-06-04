package com.android.custom.pkg.layout.grid;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.android.open9527.recycleview.adapter.BaseCellViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/6/3
 **/
public abstract class GridLayoutAdapter<T> extends XBaseAdapter {

    protected int mLayoutId;
    protected List<T> mData;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public GridLayoutAdapter(Context context, List<T> data, int layoutId) {
        this.mContext = context;
        this.mData = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
        this.mLayoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
    }

    public GridLayoutAdapter(Context context, int layoutId) {
        this(context, null, layoutId);
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public View getView(int position, ViewGroup parent) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(mInflater, mLayoutId, parent, false);
        BaseBindingCellViewHolder xHolder = new BaseBindingCellViewHolder<>(viewDataBinding);
        convert(xHolder, mData.get(position), position);
        return viewDataBinding.getRoot();
    }

    public abstract void convert(BaseBindingCellViewHolder holder, T item, int position);

    //==========================================数据相关================================================
    public void add(T elem) {
        mData.add(elem);
        notifyDataSetChanged();
    }

    public void addAll(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addFirst(T elem) {
        mData.add(0, elem);
        notifyDataSetChanged();
    }

    public void set(T oldElem, T newElem) {
        set(mData.indexOf(oldElem), newElem);
    }

    public void set(int index, T elem) {
        mData.set(index, elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        mData.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mData.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        mData.clear();
        if (elem != null && elem.size() > 0) {
            mData.addAll(elem);
        }
        notifyDataSetChanged();
    }

    public void changeAll(List<T> elem) {
        mData.clear();
        mData.addAll(elem);
    }

    public boolean contains(T elem) {
        return mData.contains(elem);
    }

    /**
     * 清除
     */
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mData;
    }

    public boolean isLast(int position) {
        return mData != null && mData.size() - 1 == position;
    }
}
