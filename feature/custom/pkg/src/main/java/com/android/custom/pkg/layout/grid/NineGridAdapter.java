package com.android.custom.pkg.layout.grid;

import android.content.Context;
import android.view.View;

/**
 * @author open_9527
 * Create at 2021/6/3
 **/
public interface NineGridAdapter<V extends View, E> {

    V onCreateChildView(Context context, NineGridLayout<V, E> parent, int position);

    void onBindData(Context context, V childView, E data, int position);

    void notifyDataSetChanged();

}
