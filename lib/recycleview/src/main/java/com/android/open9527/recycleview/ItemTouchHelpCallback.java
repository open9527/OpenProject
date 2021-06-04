package com.android.open9527.recycleview;

import android.graphics.Canvas;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author open_9527
 * Create at 2021/6/3
 * <p>
 **/
public class ItemTouchHelpCallback extends ItemTouchHelper.Callback {

    /**
     * Item操作的回调，去更新UI和数据源
     */
    private OnItemTouchCallbackListener onItemTouchCallbackListener;
    /**
     * 是否可以拖拽
     */
    private boolean isCanDrag = false;
    /**
     * 是否可以被滑动
     */
    private boolean isCanSwipe = false;
    /**
     * 按住拖动item的颜色
     */
    private int color = 0;

    public ItemTouchHelpCallback(OnItemTouchCallbackListener onItemTouchCallbackListener) {
        this.onItemTouchCallbackListener = onItemTouchCallbackListener;
    }

    /**
     * 设置是否可以被拖拽
     *
     * @param canDrag 是true，否false
     */
    public void setDragEnable(boolean canDrag) {
        isCanDrag = canDrag;
    }

    /**
     * 设置是否可以被滑动
     *
     * @param canSwipe 是true，否false
     */
    public void setSwipeEnable(boolean canSwipe) {
        isCanSwipe = canSwipe;
    }

    /**
     * 设置按住拖动item的颜色
     *
     * @param color 颜色
     */
    public void setColor(@ColorInt int color) {
        this.color = color;
    }

    /**
     * 当Item被长按的时候是否可以被拖拽
     *
     * @return true
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return isCanDrag;
    }

    /**
     * Item是否可以被滑动(H：左右滑动，V：上下滑动)
     * isItemViewSwipeEnabled()返回值是否可以拖拽排序，true可以，false不可以
     *
     * @return true
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return isCanSwipe;
    }

    /**
     * 当用户拖拽或者滑动Item的时候需要我们告诉系统滑动或者拖拽的方向
     * 动作标识分：dragFlags和swipeFlags
     * dragFlags：列表滚动方向的动作标识（如竖直列表就是上和下，水平列表就是左和右）
     * wipeFlags：与列表滚动方向垂直的动作标识（如竖直列表就是左和右，水平列表就是上和下）
     * <p>
     * 思路：如果你不想上下拖动，可以将 dragFlags = 0
     * 如果你不想左右滑动，可以将 swipeFlags = 0
     * 最终的动作标识（flags）必须要用makeMovementFlags()方法生成
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            // flag如果值是0，相当于这个功能被关闭
            int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                    | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlag = 0;
            //处理spanCount=1时, GridLayoutManager  支持侧滑删除
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            if (spanCount == 1) {
                swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            // create make
            return makeMovementFlags(dragFlag, swipeFlag);
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int orientation = linearLayoutManager.getOrientation();

            int dragFlag = 0;
            int swipeFlag = 0;

            // 如果是横向的布局
            if (orientation == LinearLayoutManager.HORIZONTAL) {
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else if (orientation == LinearLayoutManager.VERTICAL) {
                // 如果是竖向的布局，相当于ListView
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            //第一个参数是拖拽flag，第二个是滑动的flag
            return makeMovementFlags(dragFlag, swipeFlag);
        }
        return 0;
    }


    /**
     * 当Item被拖拽的时候被回调
     *
     * @param recyclerView     recyclerView
     * @param srcViewHolder    当前被拖拽的item的viewHolder
     * @param targetViewHolder 当前被拖拽的item下方的另一个item的viewHolder
     * @return 是否被移动
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder srcViewHolder,
                          @NonNull RecyclerView.ViewHolder targetViewHolder) {
        if (onItemTouchCallbackListener != null) {
            int srcPosition = srcViewHolder.getBindingAdapterPosition();
            int targetPosition = targetViewHolder.getBindingAdapterPosition();
            return onItemTouchCallbackListener.onMove(srcPosition, targetPosition);
        }
        return false;
    }


    /**
     * 当item侧滑出去时触发（竖直列表是侧滑，水平列表是竖滑）
     *
     * @param viewHolder viewHolder
     * @param direction  滑动的方向
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (onItemTouchCallbackListener != null) {
            onItemTouchCallbackListener.onSwiped(viewHolder.getBindingAdapterPosition());
        }
    }

    /**
     * 当item被拖拽或侧滑时触发
     *
     * @param viewHolder  viewHolder
     * @param actionState 当前item的状态
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        //不管是拖拽或是侧滑，背景色都要变化
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (color == 0) {
                viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext()
                        .getResources().getColor(android.R.color.darker_gray));
            } else {
                viewHolder.itemView.setBackgroundColor(color);
            }
        }
    }


    /**
     * 当item的交互动画结束时触发
     *
     * @param recyclerView recyclerView
     * @param viewHolder   viewHolder
     */
    @Override
    public void clearView(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources()
                .getColor(android.R.color.white));
        viewHolder.itemView.setAlpha(1);
        viewHolder.itemView.setScaleY(1);
    }


    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float value = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(value);
            viewHolder.itemView.setScaleY(value);
        }
    }


    public interface OnItemTouchCallbackListener {
        /**
         * 当某个Item被滑动删除的时候
         *
         * @param adapterPosition item的position
         */
        default void onSwiped(int adapterPosition) {
        }

        /**
         * 当两个Item位置互换的时候被回调
         *
         * @param srcPosition    拖拽的item的position
         * @param targetPosition 目的地的Item的position
         * @return 开发者处理了操作应该返回true，开发者没有处理就返回false
         */
        default boolean onMove(int srcPosition, int targetPosition) {
            return false;
        }
    }
}
