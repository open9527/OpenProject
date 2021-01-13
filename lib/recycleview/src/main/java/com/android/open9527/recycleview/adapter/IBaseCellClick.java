package com.android.open9527.recycleview.adapter;

import android.view.View;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public interface IBaseCellClick <CELL> extends View.OnClickListener, View.OnLongClickListener {

    default void onCellClick(View view, CELL cell) {

    }

    default boolean onCellLongClick(View view, CELL cell) {

        return false;
    }

}
