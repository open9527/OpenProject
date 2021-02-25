package com.android.open9527.titlebar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
public abstract class BaseInitialize implements TitleBarInitialize {

    @Override
    public TextView getLeftView(Context context) {
        TextView leftView = createTextView(context);
        leftView.setPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, leftView.getResources().getDisplayMetrics()), 0, 0, 0);
        leftView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.START));
        leftView.setGravity(Gravity.CENTER_VERTICAL);
        leftView.setFocusable(true);
        leftView.setSingleLine();
        leftView.setEllipsize(TextUtils.TruncateAt.END);
        leftView.setCompoundDrawablePadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, leftView.getResources().getDisplayMetrics()));
        leftView.setTextSize(14);
        return leftView;
    }

    @Override
    public TextView getTitleView(Context context) {
        TextView titleView = createTextView(context);
        titleView.setPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, titleView.getResources().getDisplayMetrics()), 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, titleView.getResources().getDisplayMetrics()), 0);
        titleView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER_HORIZONTAL));
        titleView.setGravity(Gravity.CENTER);
        titleView.setFocusable(true);
        titleView.setSingleLine();
        // 给标题设置跑马灯效果（仅在标题过长的时候才会显示）
        titleView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        // 设置跑马灯的循环次数
        titleView.setMarqueeRepeatLimit(-1);
        // 设置跑马灯之后需要设置选中才能有效果
        titleView.setSelected(true);
        titleView.setCompoundDrawablePadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, titleView.getResources().getDisplayMetrics()));
        titleView.setTextSize(16);
        return titleView;
    }

    @Override
    public TextView getRightView(Context context) {
        TextView rightView = createTextView(context);
        rightView.setPadding(0, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, rightView.getResources().getDisplayMetrics()), 0);
        rightView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.END));
        rightView.setGravity(Gravity.CENTER_VERTICAL);
        rightView.setFocusable(true);
        rightView.setSingleLine();
        rightView.setEllipsize(TextUtils.TruncateAt.END);
        rightView.setCompoundDrawablePadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, rightView.getResources().getDisplayMetrics()));
        rightView.setTextSize(14);
        return rightView;
    }

    @Override
    public View getLineView(Context context) {
        View lineView = new View(context);
        lineView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM));
        return lineView;
    }


    @Override
    public int getHorizontalPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getVerticalPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, context.getResources().getDisplayMetrics());
    }


    private TextView createTextView(Context context) {
        //new FontTextView();
        return new TextView(context);
    }


    /**
     * 获取图片资源
     */
    public static Drawable getDrawableResources(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(id, context.getTheme());
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    /**
     * 设置 View 背景
     */
    @SuppressLint("ObsoleteSdkInt")
    public static void setViewBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 检查 TextView 是否包含内容
     */
    public static boolean checkContainContent(TextView textView) {
        CharSequence text = textView.getText();
        if (!TextUtils.isEmpty(text)) {
            return true;
        }
        Drawable[] drawables = textView.getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                return true;
            }
        }
        return false;
    }

}
