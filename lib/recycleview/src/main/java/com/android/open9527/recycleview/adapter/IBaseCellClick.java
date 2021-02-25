package com.android.open9527.recycleview.adapter;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public interface IBaseCellClick<CELL> extends View.OnClickListener, View.OnLongClickListener {

    default void onCellClick(@NonNull View view, @NonNull CELL cell) {

    }

    default boolean onCellLongClick(@NonNull View view, @NonNull CELL cell) {

        return false;
    }

}
