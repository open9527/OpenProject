package com.android.open9527.titlebar;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * @author open_9527
 * Create at 2021/1/20
 **/
public class LightBarInitialize extends BaseInitialize {

    private TitleBarConfig mTitleBarConfig;

    public LightBarInitialize(TitleBarConfig titleBarConfig) {
        mTitleBarConfig = titleBarConfig;
    }

    @Override
    public TextView getLeftView(Context context) {
        TextView leftView = super.getLeftView(context);
        if (mTitleBarConfig.getLeftDrawable() != null) {
            leftView.setCompoundDrawablesWithIntrinsicBounds(mTitleBarConfig.getLeftDrawable(), null, null, null);
            leftView.setCompoundDrawablePadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, leftView.getResources().getDisplayMetrics()));
        }
        if (mTitleBarConfig.getLeftTextSize() > 0) {
            leftView.setTextSize(mTitleBarConfig.getLeftTextSize());
        }
        if (!TextUtils.isEmpty(mTitleBarConfig.getLeftText())) {
            leftView.setText(mTitleBarConfig.getLeftText());
        }
        if (mTitleBarConfig.getLeftTextColor() > 0) {
            leftView.setTextColor(context.getResources().getColor(mTitleBarConfig.getLeftTextColor()));
        }

        return leftView;
    }

    @Override
    public TextView getTitleView(Context context) {
        TextView titleView = super.getTitleView(context);
        titleView.setCompoundDrawablePadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, titleView.getResources().getDisplayMetrics()));

        if (mTitleBarConfig.getTitleTextSize() > 0) {
            titleView.setTextSize(mTitleBarConfig.getTitleTextSize());
        }
        if (!TextUtils.isEmpty(mTitleBarConfig.getTitleText())) {
            titleView.setText(mTitleBarConfig.getTitleText());
        }
        if (mTitleBarConfig.getTitleTextColor() > 0) {
            titleView.setTextColor(context.getResources().getColor(mTitleBarConfig.getTitleTextColor()));
        }
        return titleView;
    }

    @Override
    public TextView getRightView(Context context) {
        TextView rightView = super.getRightView(context);

        if (mTitleBarConfig.getRightDrawable() != null) {
            rightView.setCompoundDrawablesWithIntrinsicBounds(null, null, mTitleBarConfig.getRightDrawable(), null);
            rightView.setCompoundDrawablePadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, rightView.getResources().getDisplayMetrics()));
        }
        if (mTitleBarConfig.getRightTextSize() > 0) {
            rightView.setTextSize(mTitleBarConfig.getRightTextSize());
        }
        if (!TextUtils.isEmpty(mTitleBarConfig.getRightText())) {
            rightView.setText(mTitleBarConfig.getRightText());
        }
        if (mTitleBarConfig.getRightTextColor() > 0) {
            rightView.setTextColor(context.getResources().getColor(mTitleBarConfig.getRightTextColor()));
        }
        return rightView;
    }

    @Override
    public View getLineView(Context context) {
        View lineView = super.getLineView(context);
        if (mTitleBarConfig.getLineBgHigh() > 0) {
            lineView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mTitleBarConfig.getLineBgHigh(), Gravity.BOTTOM));
        }
        if (mTitleBarConfig.getLineBg() > 0) {
            lineView.setBackgroundColor(context.getResources().getColor(mTitleBarConfig.getLineBg()));
        }
        lineView.setVisibility(mTitleBarConfig.getLineVisibility());

        return lineView;
    }

    @Override
    public int getHorizontalPadding(Context context) {
        return 0;
    }

    @Override
    public int getVerticalPadding(Context context) {
        return 0;
    }


}
