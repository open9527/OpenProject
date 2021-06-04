package com.android.custom.pkg.layout.grid;

/**
 * @author open_9527
 * Create at 2021/6/3
 **/
public class LinearLayoutManager implements LayoutManager {

    @Override
    public int[] getParentSize(int childCount, int gridSpace) {
        return new int[0];
    }

    @Override
    public int getMaxChildCount() {
        return 0;
    }

    @Override
    public Place getChildPosition(int position, int columnCount, int gridWidth, int gridHeight, int gridSpacing) {
        return null;
    }
}
