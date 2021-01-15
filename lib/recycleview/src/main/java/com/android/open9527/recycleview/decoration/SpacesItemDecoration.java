package com.android.open9527.recycleview.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    protected static final int HORIZONTAL = RecyclerView.HORIZONTAL;
    protected static final int VERTICAL = RecyclerView.VERTICAL;
    private static final String TAG = "SpacesItemDecoration";
    private Context mContext;
    private Drawable mDivider;
    private Rect mBounds = new Rect();
    /**
     * AppTheme  android:listDivider
     */
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private int mHeaderNoShowSize = 0;

    private int mFooterNoShowSize = 0;
    /**
     * Current orientation. Either {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    private int mOrientation;
    private Paint mPaint;

    private int mDividerSpacing;

    private int mLeftTopPadding;

    private int mRightBottomPadding;

    public SpacesItemDecoration(Context context) {
        this(context, VERTICAL, 0, 0);
    }

    public SpacesItemDecoration(Context context, int orientation) {
        this(context, orientation, 0, 0);
    }

    public SpacesItemDecoration(Context context, int orientation, int headerNoShowSize) {
        this(context, orientation, headerNoShowSize, 0);
    }


    public SpacesItemDecoration(Context context, int orientation, int headerNoShowSize, int footerNoShowSize) {
        mContext = context;
        mHeaderNoShowSize = headerNoShowSize;
        mFooterNoShowSize = footerNoShowSize;
        setOrientation(orientation);
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }


    public SpacesItemDecoration setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        mOrientation = orientation;
        return this;
    }


    public SpacesItemDecoration setDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("drawable cannot be null.");
        }
        mDivider = drawable;
        return this;
    }

    public SpacesItemDecoration setDrawable(@DrawableRes int id) {
        setDrawable(ContextCompat.getDrawable(mContext, id));
        return this;
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() == null || (mDivider == null && mPaint == null)) {
            return;
        }
        if (mOrientation == VERTICAL) {
            drawVertical(canvas, parent, state);
        } else {
            drawHorizontal(canvas, parent, state);
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        canvas.save();
        final int left;
        final int right;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        final int childCount = parent.getChildCount();
        final int lastPosition = state.getItemCount() - 1;
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int childRealPosition = parent.getChildAdapterPosition(child);


            if (childRealPosition < mHeaderNoShowSize) {
                continue;
            }

            if (childRealPosition <= lastPosition - mFooterNoShowSize) {
                if (mDivider != null) {
                    parent.getDecoratedBoundsWithMargins(child, mBounds);
                    final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
                    final int top = bottom - mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }

                if (mPaint != null) {
                    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    int left1 = left + mLeftTopPadding;
                    int right1 = right - mRightBottomPadding;
                    int top1 = child.getBottom() + params.bottomMargin;
                    int bottom1 = top1 + mDividerSpacing;
                    canvas.drawRect(left1, top1, right1, bottom1, mPaint);
                }
            }
        }
        canvas.restore();
    }

    private void drawHorizontal(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        canvas.save();
        final int top;
        final int bottom;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        final int childCount = parent.getChildCount();
        final int lastPosition = state.getItemCount() - 1;
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int childRealPosition = parent.getChildAdapterPosition(child);


            if (childRealPosition < mHeaderNoShowSize) {
                continue;
            }

            if (childRealPosition <= lastPosition - mFooterNoShowSize) {
                if (mDivider != null) {
                    parent.getDecoratedBoundsWithMargins(child, mBounds);
                    final int right = mBounds.right + Math.round(child.getTranslationX());
                    final int left = right - mDivider.getIntrinsicWidth();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }

                if (mPaint != null) {
                    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    int left1 = child.getRight() + params.rightMargin;
                    int right1 = left1 + mDividerSpacing;
                    int top1 = top + mLeftTopPadding;
                    int bottom1 = bottom - mRightBottomPadding;
                    canvas.drawRect(left1, top1, right1, bottom1, mPaint);
                }
            }
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mDivider == null && mPaint == null) {
            outRect.set(0, 0, 0, 0);
            return;
        }

        int lastPosition = state.getItemCount() - 1;
        int position = parent.getChildAdapterPosition(view);

        boolean isShowDivider = mHeaderNoShowSize <= position && position <= lastPosition - mFooterNoShowSize;

        if (mOrientation == VERTICAL) {
            if (isShowDivider) {
                outRect.set(0, 0, 0, mDivider != null ? mDivider.getIntrinsicHeight() : mDividerSpacing);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        } else {
            if (isShowDivider) {
                outRect.set(0, 0, mDivider != null ? mDivider.getIntrinsicWidth() : mDividerSpacing, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }


    public SpacesItemDecoration setNoShowDivider(int headerNoShowSize, int footerNoShowSize) {
        this.mHeaderNoShowSize = headerNoShowSize;
        this.mFooterNoShowSize = footerNoShowSize;
        return this;
    }


    public SpacesItemDecoration setHeaderNoShowDivider(int headerNoShowSize) {
        this.mHeaderNoShowSize = headerNoShowSize;
        return this;
    }

    public SpacesItemDecoration setFooterNoShowDivider(int footerNoShowSize) {
        this.mFooterNoShowSize = footerNoShowSize;
        return this;
    }

    public SpacesItemDecoration setParam(int dividerColor, int dividerSpacing) {
        return setParam(dividerColor, dividerSpacing, 0, 0);
    }


    public SpacesItemDecoration setParam(int dividerColor, int dividerSpacing, float leftTopPaddingDp, float rightBottomPaddingDp) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ContextCompat.getColor(mContext, dividerColor));
        mDividerSpacing = dividerSpacing;
        mLeftTopPadding = dp2px(leftTopPaddingDp);
        mRightBottomPadding = dp2px(rightBottomPaddingDp);
        mDivider = null;
        return this;
    }


    public static int dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}