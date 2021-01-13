package com.android.open9527.recycleview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class RecycleViewUtils {

    /**
     * close Default Animator
     *
     * @param recyclerView rv
     */
    public static void closeDefaultAnimator(@NonNull RecyclerView recyclerView) {
        if (recyclerView.getItemAnimator() == null) return;
        recyclerView.getItemAnimator().setAddDuration(0);
        recyclerView.getItemAnimator().setChangeDuration(0);
        recyclerView.getItemAnimator().setMoveDuration(0);
        recyclerView.getItemAnimator().setRemoveDuration(0);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }
}
