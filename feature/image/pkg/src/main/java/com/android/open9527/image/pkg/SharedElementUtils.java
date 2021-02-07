package com.android.open9527.image.pkg;

import android.app.Activity;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author open_9527
 * Create at 2021/2/2
 **/
public final class SharedElementUtils {


    public static ActivityOptionsCompat createActivityOptionsCompat(@NonNull Activity activity, @NonNull View view) {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, view.getTransitionName());
    }

    /**
     * 进入 监听
     *
     * @param activity :
     * @param view     :
     * @return :
     */
    public static EnterSharedElementCallback createEnterSharedElementCallback(@NonNull Activity activity, @NonNull View view) {
        EnterSharedElementCallback enterSharedElementCallback = new EnterSharedElementCallback(view);
        ActivityCompat.setEnterSharedElementCallback(activity, enterSharedElementCallback);
        return enterSharedElementCallback;
    }

    /**
     * 退出 监听
     *
     * @param activity     :
     * @param recyclerView :
     * @return :
     */
    public static ExitSharedElementCallback createExitSharedElementCallback(@NonNull Activity activity, @NonNull RecyclerView recyclerView) {
        ExitSharedElementCallback exitSharedElementCallback = new ExitSharedElementCallback(recyclerView);
        ActivityCompat.setExitSharedElementCallback(activity, exitSharedElementCallback);
        return exitSharedElementCallback;
    }


    /**
     * 退出过度动画
     *
     * @param activity :
     */
    public static void finishSharedElement(@NonNull Activity activity) {
        ActivityCompat.finishAfterTransition(activity);
    }


    /**
     * 创建动画
     * 在super.onCreate之前 调用
     *
     * setEnterTransition() - ActivityA 跳转到 ActivityB，ActivityB中的View进入场景的transition。
     * setExitTransition() - ActivityA 跳转到 ActivityB，ActivityA中的View退出场景的transition。
     * setReturnTransition() - 从ActivityB 返回Activity A时，ActivityB中的View退出场景的transition。
     * setReenterTransition() - 从ActivityB 返回Activity A时，ActivityA中的View进入场景的transition
     *
     * //对应setEnterTransition
     * <item name="android:windowEnterTransition"></item>
     * //对应setExitTransition
     * <item name="android:windowExitTransition"></item>
     * //对应setReturnTransition
     * <item name="android:windowReturnTransition"></item>
     * //对应setReenterTransition
     * <item name="android:windowReenterTransition"></item>
     *
     *
     * @param activity :
     */
    public static void createTransition(@NonNull Activity activity) {
        Window window = activity.getWindow();
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        window.setSharedElementsUseOverlay(true);
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(1000);
        ChangeClipBounds changeClipBounds = new ChangeClipBounds();
        changeClipBounds.setDuration(1000);
        ChangeImageTransform changeImageTransform = new ChangeImageTransform();
        changeImageTransform.setDuration(1000);
        ChangeTransform changeTransform = new ChangeTransform();
        changeTransform.setDuration(1000);
        window.setSharedElementEnterTransition(changeBounds);
        window.setSharedElementExitTransition(changeClipBounds);
        window.setSharedElementReenterTransition(changeImageTransform);
        window.setSharedElementReturnTransition(changeTransform);
    }

}
