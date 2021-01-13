package com.android.open9527.recycleview.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpanCount;

    private int mSpacing;

    private boolean mIncludeEdge;

    private int mStartFromSize;

    private int mEndFromSize = 0;

    private int fullPosition = -1;

    public GridSpaceItemDecoration(int spacing) {
        this(spacing, true);
    }


    public GridSpaceItemDecoration(int spacing, boolean includeEdge) {
        this.mSpacing = spacing;
        this.mIncludeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int lastPosition = state.getItemCount() - 1;
        int position = parent.getChildAdapterPosition(view);
        if (mStartFromSize <= position && position <= lastPosition - mEndFromSize) {

            int spanGroupIndex = -1;

            int column = 0;

            boolean fullSpan = false;

            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
                int spanCount = gridLayoutManager.getSpanCount();

                int spanSize = spanSizeLookup.getSpanSize(position);

                mSpanCount = spanCount / spanSize;

                int spanIndex = spanSizeLookup.getSpanIndex(position, spanCount);

                column = spanIndex / spanSize;

                spanGroupIndex = spanSizeLookup.getSpanGroupIndex(position, spanCount) - mStartFromSize;

            } else if (layoutManager instanceof StaggeredGridLayoutManager) {

                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();

                column = params.getSpanIndex();

                fullSpan = params.isFullSpan();
                mSpanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            }

            position = position - mStartFromSize;

            if (mIncludeEdge) {
                /*
                 *eg
                 * spacing = 10 ；spanCount = 3
                 * ---------10--------
                 * 10   3+7   6+4    10
                 * ---------10--------
                 * 10   3+7   6+4    10
                 * ---------10--------
                 */
                if (fullSpan) {
                    outRect.left = 0;
                    outRect.right = 0;
                } else {
                    outRect.left = mSpacing - column * mSpacing / mSpanCount;
                    outRect.right = (column + 1) * mSpacing / mSpanCount;
                }

                if (spanGroupIndex > -1) {

                    if (spanGroupIndex < 1 && position < mSpanCount) {

                        outRect.top = mSpacing;
                    }
                } else {
                    if (fullPosition == -1 && position < mSpanCount && fullSpan) {

                        fullPosition = position;
                    }

                    boolean isFirstLineStagger = (fullPosition == -1 || position < fullPosition) && (position < mSpanCount);
                    if (isFirstLineStagger) {

                        outRect.top = mSpacing;
                    }
                }

                outRect.bottom = mSpacing;

            } else {
                /*
                 *eg
                 * spacing = 10 ；spanCount = 3
                 * --------0--------
                 * 0   3+7   6+4    0
                 * -------10--------
                 * 0   3+7   6+4    0
                 * --------0--------
                 */
                if (fullSpan) {
                    outRect.left = 0;
                    outRect.right = 0;
                } else {
                    outRect.left = column * mSpacing / mSpanCount;
                    outRect.right = mSpacing - (column + 1) * mSpacing / mSpanCount;
                }

                if (spanGroupIndex > -1) {
                    if (spanGroupIndex >= 1) {

                        outRect.top = mSpacing;
                    }
                } else {
                    if (fullPosition == -1 && position < mSpanCount && fullSpan) {

                        fullPosition = position;
                    }

                    boolean isStaggerShowTop = position >= mSpanCount || (fullSpan && position != 0) || (fullPosition != -1 && position != 0);

                    if (isStaggerShowTop) {

                        outRect.top = mSpacing;
                    }
                }
            }
        }
    }


    public GridSpaceItemDecoration setStartFrom(int startFromSize) {
        this.mStartFromSize = startFromSize;
        return this;
    }


    public GridSpaceItemDecoration setEndFromSize(int endFromSize) {
        this.mEndFromSize = endFromSize;
        return this;
    }


    public GridSpaceItemDecoration setNoShowSpace(int startFromSize, int endFromSize) {
        this.mStartFromSize = startFromSize;
        this.mEndFromSize = endFromSize;
        return this;
    }
}
