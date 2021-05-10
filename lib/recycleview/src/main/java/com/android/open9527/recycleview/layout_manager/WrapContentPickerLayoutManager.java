package com.android.open9527.recycleview.layout_manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class WrapContentPickerLayoutManager extends LinearLayoutManager {

    private LinearSnapHelper mLinearSnapHelper;
    private RecyclerView mRecyclerView;

    private @RecyclerView.Orientation
    int mOrientation;
    private int mMaxItem;
    private float mScale;
    private boolean mAlpha;


    private static WrapContentPickerLayoutManager newInstance(@NonNull Context context) {
        return new WrapContentPickerLayoutManager(context);
    }

    public WrapContentPickerLayoutManager(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        mLinearSnapHelper = new LinearSnapHelper();
    }


    @Override
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        mRecyclerView = recyclerView;
        // 设置子控件的边界可以超过父布局的范围
        mRecyclerView.setClipToPadding(false);
        // 添加 LinearSnapHelper
        mLinearSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        mRecyclerView = null;
    }

    @Override
    public boolean isAutoMeasureEnabled() {
        return mMaxItem == 0;
    }


    @Override
    public void onMeasure(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int widthSpec, int heightSpec) {
        int width = RecyclerView.LayoutManager.chooseSize(widthSpec,
                getPaddingLeft() + getPaddingRight(),
                ViewCompat.getMinimumWidth(mRecyclerView));
        int height = RecyclerView.LayoutManager.chooseSize(heightSpec,
                getPaddingTop() + getPaddingBottom(),
                ViewCompat.getMinimumHeight(mRecyclerView));

        if (state.getItemCount() != 0 && mMaxItem != 0) {

            View itemView = recycler.getViewForPosition(0);
            measureChildWithMargins(itemView, widthSpec, heightSpec);

            if (mOrientation == HORIZONTAL) {
                int measuredWidth = itemView.getMeasuredWidth();
                int paddingHorizontal = (mMaxItem - 1) / 2 * measuredWidth;
                mRecyclerView.setPadding(paddingHorizontal, 0, paddingHorizontal, 0);
                width = measuredWidth * mMaxItem;
            } else if (mOrientation == VERTICAL) {
                int measuredHeight = itemView.getMeasuredHeight();
                int paddingVertical = (mMaxItem - 1) / 2 * measuredHeight;
                mRecyclerView.setPadding(0, paddingVertical, 0, paddingVertical);
                height = measuredHeight * mMaxItem;
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        // 当 RecyclerView 停止滚动时
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            if (mListener != null) {
                mListener.onPicked(mRecyclerView, getPickedPosition());
            }
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        if (getItemCount() < 0 || state.isPreLayout()) {
            return;
        }

        if (mOrientation == HORIZONTAL) {
            scaleHorizontalChildView();
        } else if (mOrientation == VERTICAL) {
            scaleVerticalChildView();
        }
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        scaleHorizontalChildView();
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        scaleVerticalChildView();
        return super.scrollVerticallyBy(dy, recycler, state);
    }





    /*-----------------------------------------------------------------------------------------*/

    /**
     * 横向情况下的缩放
     */
    private void scaleHorizontalChildView() {
        float mid = getWidth() / 2.0f;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView != null) {
                float childMid = (getDecoratedLeft(childView) + getDecoratedRight(childView)) / 2.0f;
                float scale = 1.0f + (-1 * (1 - mScale)) * (Math.min(mid, Math.abs(mid - childMid))) / mid;
                childView.setScaleX(scale);
                childView.setScaleY(scale);
                if (mAlpha) {
                    childView.setAlpha(scale);
                }
            }
        }
    }

    /**
     * 竖向方向上的缩放
     */
    private void scaleVerticalChildView() {
        float mid = getHeight() / 2.0f;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView != null) {
                float childMid = (getDecoratedTop(childView) + getDecoratedBottom(childView)) / 2.0f;
                float scale = 1.0f + (-1 * (1 - mScale)) * (Math.min(mid, Math.abs(mid - childMid))) / mid;
                childView.setScaleX(scale);
                childView.setScaleY(scale);
                if (mAlpha) {
                    childView.setAlpha(scale);
                }
            }
        }
    }

    /**
     * 获取选中的位置
     */
    public int getPickedPosition() {
        View itemView = mLinearSnapHelper.findSnapView(this);
        if (itemView != null) {
            return getPosition(itemView);
        }
        return 0;
    }


    private OnPickerListener mListener;

    public void setOnPickerListener(OnPickerListener listener) {
        mListener = listener;
    }

    public interface OnPickerListener {

        /**
         * 滚动停止时触发的监听
         *
         * @param recyclerView RecyclerView 对象
         * @param position     当前滚动的位置
         */
        void onPicked(RecyclerView recyclerView, int position);
    }


    public static WrapContentPickerLayoutManager with(@NonNull Context context) {
        return WrapContentPickerLayoutManager.newInstance(context);
    }

    /*-----------------------------------------------------------------------------------------*/

    public static final class Builder {

        private final Context mContext;
        private @RecyclerView.Orientation
        int mOrientation = VERTICAL;
        private boolean mReverseLayout;
        private OnPickerListener mListener;

        private int mMaxItem = 3;
        private float mScale = 0.6f;
        private boolean mAlpha = true;

        public Builder(@NonNull Context context) {
            mContext = context;
        }

        @SuppressLint("WrongConstant")
        public Builder setOrientation(@RecyclerView.Orientation int orientation) {
            mOrientation = orientation;
            return this;
        }

        public Builder setReverseLayout(boolean reverseLayout) {
            mReverseLayout = reverseLayout;
            return this;
        }

        public Builder setMaxItem(int maxItem) {
            mMaxItem = maxItem;
            return this;
        }


        public Builder setScale(float scale) {
            mScale = scale;
            return this;
        }

        public Builder setAlpha(boolean alpha) {
            mAlpha = alpha;
            return this;
        }

        public Builder setOnPickerListener(OnPickerListener listener) {
            mListener = listener;
            return this;
        }

        @SuppressLint("WrongConstant")
        public WrapContentPickerLayoutManager build() {
            WrapContentPickerLayoutManager layoutManager = WrapContentPickerLayoutManager.newInstance(mContext);
            layoutManager.mOrientation = mOrientation;
            layoutManager.mMaxItem = mMaxItem;
            layoutManager.mScale = mScale;
            layoutManager.mAlpha = mAlpha;
            layoutManager.setOrientation(mOrientation);
            layoutManager.setReverseLayout(mReverseLayout);
            if (mListener != null) {
                layoutManager.setOnPickerListener(mListener);
            }
            return layoutManager;
        }

        public void into(@NonNull RecyclerView recyclerView) {
            recyclerView.setLayoutManager(build());
        }

    }

}