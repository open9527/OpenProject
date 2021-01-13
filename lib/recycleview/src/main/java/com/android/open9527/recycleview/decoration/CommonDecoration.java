package com.android.open9527.recycleview.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author open_9527
 * Create at 2020/12/31
 * <p>
 * TODO:根据需求自行重写
 **/
public class CommonDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
//        int lastPosition = state.getItemCount() - 1;
//        int childLayoutPosition = parent.getChildLayoutPosition(view);
//               if (childLayoutPosition % 2 == 0) {
//            outRect.left = SizeUtils.dp2px(16);
//            outRect.right = SizeUtils.dp2px(4);
//        } else {
//            outRect.left = SizeUtils.dp2px(4);
//            outRect.right = SizeUtils.dp2px(16);
//        }
//        outRect.top = SizeUtils.dp2px(9);
    }
}
