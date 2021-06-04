package com.android.open9527.common.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/6/4
 **/
public class NineGridLayout extends FrameLayout {

    private RecyclerView mRecyclerView;

    public NineGridLayout(Context context) {
        this(context, null, 0, 0);
    }

    public NineGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public NineGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NineGridLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (isInEditMode()) {
            return;
        }
        addView(mRecyclerView = new RecyclerView(context));
    }


    public static NineGridLayout.Builder newInstance(@NonNull Context context) {
        return new NineGridLayout.Builder(context);
    }


    public static final class Builder {
        private final Context mContext;

        private RecyclerView mRecyclerView;
        private RecyclerView.LayoutManager mLayoutManager;
        private RecyclerView.ItemDecoration mItemDecoration;
        private ListAdapter mAdapter;
        private List mList = new ArrayList();


        public Builder(@NonNull Context context) {
            mContext = context;
        }

        public Builder setRecyclerView(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
            return this;
        }

        public Builder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
            this.mLayoutManager = layoutManager;
            return this;
        }

        public Builder setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
            this.mItemDecoration = itemDecoration;
            return this;
        }

        public Builder setAdapter(ListAdapter adapter) {
            this.mAdapter = adapter;
            return this;
        }

        public Builder setList(List list) {
            this.mList = list;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder into(@NonNull NineGridLayout gridLayout) {

            if (mRecyclerView != null) {
                gridLayout.removeView(gridLayout.getRecyclerView());
                gridLayout.mRecyclerView = mRecyclerView;
                gridLayout.addView(mRecyclerView);
            } else {
                mRecyclerView = gridLayout.getRecyclerView();
            }
            mRecyclerView.setLayoutManager(mLayoutManager);
            if (mRecyclerView.getItemDecorationCount() == 0) {
                mRecyclerView.addItemDecoration(mItemDecoration);
            }
            gridLayout.mRecyclerView.setAdapter(mAdapter);
            mAdapter.submitList(mList);
            return this;
        }

        public NineGridLayout build() {
            return new NineGridLayout(mContext);
        }
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
