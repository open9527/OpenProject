package com.android.open9527.titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * @author open_9527
 * Create at 2021/1/20
 **/
public class TitleBar extends FrameLayout implements View.OnClickListener,
        View.OnLayoutChangeListener {

    private static final String TAG = "TitleBar";

    private TitleBarConfig mTitleBarConfig;

    private TextView mLeftView, mTitleView, mRightView;
    private View mLineView;

    private int mHorizontalPadding, mVerticalPadding;


    public TitleBar(Context context) {
        this(context, null, 0, 0);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init() {
        Log.i(TAG, "init-->" + mTitleBarConfig.toString());
        if (mTitleBarConfig.getTitleBarInitialize() == null) {
            mTitleBarConfig.setTitleBarInitialize(new LightBarInitialize(mTitleBarConfig));
        }

        mHorizontalPadding = mTitleBarConfig.getTitleBarInitialize().getHorizontalPadding(getContext());
        mVerticalPadding = mTitleBarConfig.getTitleBarInitialize().getVerticalPadding(getContext());

        mLeftView = mTitleBarConfig.getTitleBarInitialize().getLeftView(getContext());
        mTitleView = mTitleBarConfig.getTitleBarInitialize().getTitleView(getContext());
        mRightView = mTitleBarConfig.getTitleBarInitialize().getRightView(getContext());
        mLineView = mTitleBarConfig.getTitleBarInitialize().getLineView(getContext());


        if (getBackground() == null && mTitleBarConfig.getTitleBarBgDrawable() != null) {
            BaseInitialize.setViewBackground(this, mTitleBarConfig.getTitleBarBgDrawable());
        }

        addView(mTitleView, 0);
        addView(mLeftView, 1);
        addView(mRightView, 2);
        addView(mLineView, 3);

        addOnLayoutChangeListener(this);

        mTitleView.setOnClickListener(this);
        mLeftView.setOnClickListener(this);
        mRightView.setOnClickListener(this);

    }


    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        // 先移除当前的监听，因为 setMaxWidth 会重新触发监听
        removeOnLayoutChangeListener(this);
        // 标题栏子 View 最大宽度限制算法
        int barWidth = right - left;
        int sideWidth = Math.max(mLeftView.getWidth(), mRightView.getWidth());
        int maxWidth = sideWidth * 2 + mTitleView.getWidth();
        // 算出来子 View 的宽大于标题栏的宽度
        if (maxWidth >= barWidth) {
            // 判断是左右项太长还是标题项太长
            if (sideWidth > barWidth / 3) {
                // 如果是左右项太长，那么按照比例进行划分
                mLeftView.setMaxWidth(barWidth / 4);
                mTitleView.setMaxWidth(barWidth / 2);
                mRightView.setMaxWidth(barWidth / 4);
            } else {
                // 如果是标题项太长，那么就进行动态计算
                mLeftView.setMaxWidth(sideWidth);
                mTitleView.setMaxWidth(barWidth - sideWidth * 2);
                mRightView.setMaxWidth(sideWidth);
            }
        } else {
            // 不限制子 View 的最大宽度
            mLeftView.setMaxWidth(Integer.MAX_VALUE);
            mTitleView.setMaxWidth(Integer.MAX_VALUE);
            mRightView.setMaxWidth(Integer.MAX_VALUE);
        }

        // TextView 里面必须有东西才能被点击
        mLeftView.setEnabled(BaseInitialize.checkContainContent(mLeftView));
        mTitleView.setEnabled(BaseInitialize.checkContainContent(mTitleView));
        mRightView.setEnabled(BaseInitialize.checkContainContent(mRightView));

        post(new Runnable() {
            @Override
            public void run() {
                // 这里再次监听需要延迟，否则会导致递归的情况发生
                addOnLayoutChangeListener(TitleBar.this);
            }
        });
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        if (params.width == LayoutParams.WRAP_CONTENT) {
            // 如果当前宽度是自适应则转换成占满父布局
            params.width = LayoutParams.MATCH_PARENT;
        }

        int horizontalPadding = mHorizontalPadding;
        int verticalPadding = 0;
        // 如果当前高度是自适应则设置默认的内间距
        if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            verticalPadding = mVerticalPadding;
        }
        setChildPadding(horizontalPadding, verticalPadding);
        super.setLayoutParams(params);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onClick(View view) {
        if (mTitleBarConfig.getTitleBarListener() == null) {
            return;
        }

        if (view == mLeftView) {
            mTitleBarConfig.getTitleBarListener().onLeftClick(view);

        } else if (view == mRightView) {
            mTitleBarConfig.getTitleBarListener().onRightClick(view);

        } else if (view == mTitleView) {
            mTitleBarConfig.getTitleBarListener().onTitleClick(view);

        }
    }

    /*set*/

    public void setTitleBarConfig(TitleBarConfig mTitleBarConfig) {
        this.mTitleBarConfig = mTitleBarConfig;
    }

    public TitleBar setChildPadding(int horizontal, int vertical) {
        if (mLeftView != null) {
            mLeftView.setPadding(horizontal, vertical, horizontal, vertical);
        }

        if (mTitleView != null) {
            mTitleView.setPadding(horizontal, vertical, horizontal, vertical);
        }
        if (mRightView != null) {
            mRightView.setPadding(horizontal, vertical, horizontal, vertical);
        }

        return this;
    }

    /*get*/

    public TextView getLeftView() {
        return mLeftView;
    }

    public TextView getTitleView() {
        return mTitleView;
    }

    public TextView getRightView() {
        return mRightView;
    }

    public View getLineView() {
        return mLineView;
    }
}
