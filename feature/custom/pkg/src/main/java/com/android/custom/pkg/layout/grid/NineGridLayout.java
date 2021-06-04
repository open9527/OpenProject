package com.android.custom.pkg.layout.grid;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/6/3
 **/
public class NineGridLayout<V extends View, E> extends ViewGroup {

    public static final int MODE_LINEAR = 0;
    public static final int MODE_GRID = 1;

    private int mode = MODE_LINEAR;
    private int maxChildCount = 9;
    private int gridSpace = 0;
    private float singleChildPercent = 0.5F;
    private float singleChildRatio = 1.0F;
    private float fourChildPercent = 1.0F / 3.0F;

    private int columnCount;
    private int rowCount;
    private int gridWidth;
    private int gridHeight;

    private final List<V> mChildViews;
    private List<E> mDataList;
    private BaseNineGridAdapter<V, E> mAdapter = null;
    private LayoutManager mLayoutManager = null;

    public NineGridLayout(Context context) {
        this(context, null);
    }

    public NineGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mChildViews = new ArrayList<>();
    }

    private boolean isDataEmpty() {
        return mDataList == null || mDataList.size() == 0;
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    public LayoutManager getLayoutManager() {
        if (mLayoutManager == null) {
            mLayoutManager = new LinearLayoutManager();
        }
        return mLayoutManager;
    }

    public void setAdapter(@NonNull BaseNineGridAdapter<V, E> adapter) {
        mAdapter = adapter;
        mAdapter.onAttachToLayout(this);
    }

    public void onLayoutPrepare() {
        List<E> dataList = mAdapter.getDataList();
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        int size = dataList.size();
        if (maxChildCount > 0 && size > maxChildCount) {
            dataList = dataList.subList(0, maxChildCount);
            size = dataList.size();
        }
        //保证View的复用，避免重复创建
        if (mDataList == null) {
            for (int i = 0; i < size; i++) {
                View childView = getChildView(i);
                if (childView == null) {
                    return;
                }
                addView(childView);
            }
        } else {
            int oldViewCount = mDataList.size();
            if (oldViewCount > size) {
                removeViews(size, oldViewCount - size);
            } else if (oldViewCount < size) {
                for (int i = oldViewCount; i < size; i++) {
                    View childView = getChildView(i);
                    if (childView == null) {
                        return;
                    }
                    addView(childView);
                }
            }
        }
        mDataList = dataList;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;
        if (!isDataEmpty()) {
            int count = mDataList.size();
            int usableWidth = width - getPaddingLeft() - getPaddingRight();

            if (count == 1) {
                gridWidth = (int) (usableWidth * singleChildPercent);
                gridHeight = (int) (gridWidth / singleChildRatio);
            } else {
                gridWidth = gridHeight = (usableWidth - gridSpace * 2) / 3;
            }

            //默认是3列显示，行数根据图片的数量决定
            columnCount = count < 3 ? count % 3 : 3;
            rowCount = count / 3 + (count % 3 == 0 ? 0 : 1);
            //grid模式下，显示4张使用2X2模式
            if (mode == MODE_GRID) {
                if (count == 4) {
                    rowCount = 2;
                    columnCount = 2;
                }
            }
            width = gridWidth * columnCount + gridSpace * (columnCount - 1) + getPaddingLeft() + getPaddingRight();
            height = gridHeight * rowCount + gridSpace * (rowCount - 1) + getPaddingTop() + getPaddingBottom();

            int[] size = getLayoutManager().getParentSize(mDataList.size(), gridSpace);

        }
        setMeasuredDimension(width, height);

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            int wSpec = MeasureSpec.makeMeasureSpec(gridWidth, MeasureSpec.EXACTLY);
            int hSpec = MeasureSpec.makeMeasureSpec(gridHeight, MeasureSpec.EXACTLY);
            getChildAt(i).measure(wSpec, hSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (isDataEmpty()) {
            return;
        }
        final int childCount = mDataList.size();
        for (int i = 0; i < childCount; i++) {
            V childView = (V) getChildAt(i);

            Place childPlace = getLayoutManager().getChildPosition(i, columnCount, gridWidth, gridHeight, gridSpace);

            int rowNum = i / columnCount;
            int columnNum = i % columnCount;
            int left = (gridWidth + gridSpace) * columnNum + getPaddingLeft();
            int top = (gridHeight + gridSpace) * rowNum + getPaddingTop();
            int right = left + gridWidth;
            int bottom = top + gridHeight;
            childView.layout(left, top, right, bottom);

            if (mAdapter != null) {
                mAdapter.onBindData(getContext(), childView, mDataList.get(i), i);
            }
        }
    }

    private void layoutViewAt(View view, int l, int t, int r, int b) {
        int parentPaddingLeft = getPaddingLeft();
        int parentPaddingTop = getPaddingTop();
        int padding = gridSpace / 2;
        int left = l + padding;
        int top = t + padding;
        int right = r + padding;
        int bottom = b + padding;
        view.layout(left, top, right, bottom);
    }

    private V getChildView(final int position) {
        V childView;
        if (position < mChildViews.size()) {
            childView = mChildViews.get(position);
        } else {
            childView = mAdapter.onCreateChildView(getContext(), this, position);
            mChildViews.add(childView);
        }
        return childView;
    }

    public void setMode(int mode) {
        this.mode = mode;
        requestLayout();
    }

    public void setMaxChildCount(int maxChildCount) {
        this.maxChildCount = maxChildCount;
        requestLayout();
    }

    public void setGridSpace(int gridSpace) {
        this.gridSpace = gridSpace;
        requestLayout();
    }

    public void setSingleChildPercent(float singleChildPercent) {
        this.singleChildPercent = singleChildPercent;
        requestLayout();
    }

    public void setSingleChildRatio(float singleChildRatio) {
        this.singleChildRatio = singleChildRatio;
        requestLayout();
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        requestLayout();
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        requestLayout();
    }
}
