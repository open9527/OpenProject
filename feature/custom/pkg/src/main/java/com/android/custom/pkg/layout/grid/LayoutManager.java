package com.android.custom.pkg.layout.grid;

/**
 * @author open_9527
 * Create at 2021/6/3
 **/
public interface LayoutManager {

    int[] getParentSize(int childCount, int gridSpace);

    int getMaxChildCount();

    Place getChildPosition(int position, int columnCount, int gridWidth, int gridHeight, int gridSpacing);
}
