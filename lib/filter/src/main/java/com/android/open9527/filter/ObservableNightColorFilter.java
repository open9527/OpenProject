package com.android.open9527.filter;

import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

public class ObservableNightColorFilter implements View.OnLayoutChangeListener {

    private WeakReference<NightColorFilter> filterWeakReference;
    private Paint revertPaint;

    public ObservableNightColorFilter(@NonNull NightColorFilter filter, @NonNull ViewGroup rootView) {
        filterWeakReference = new WeakReference<>(filter);
        revertPaint = new Paint();
        ColorMatrix rcm = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        });
        revertPaint.setColorFilter(new ColorMatrixColorFilter(rcm));
        // 遍历查找需要逆转的View，对其逆转
        applyChildColor(rootView, revertPaint);
        // 布局发生变化时，遍历查找需要逆转的View，对其逆转
        rootView.addOnLayoutChangeListener(this);
    }

    @Override
    public void onLayoutChange(@NonNull View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        // 遍历查找需要逆转的View，对其逆转
        if (view instanceof ViewGroup) {
            applyChildColor((ViewGroup) view, revertPaint);
        }
        checkNightColorFilter(view, revertPaint);
    }

    private void applyChildColor(@NonNull ViewGroup rootView, @Nullable Paint paint) {
        int childCount = rootView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = rootView.getChildAt(i);
            if (childView == null) {
                continue;
            }
            if (childView instanceof ViewGroup) {
                applyChildColor((ViewGroup) childView, paint);
            }
            checkNightColorFilter(childView, paint);
        }
    }

    private void checkNightColorFilter(@NonNull View view, @Nullable Paint paint) {
        if (filterWeakReference != null) {
            NightColorFilter filter = filterWeakReference.get();
            if (filter != null && filter.excludeView(view)) {
                view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
//                    childView.setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
            }
        }
    }


    public boolean match(@NonNull Activity activity) {
        if (filterWeakReference == null) {
            return false;
        }
        return activity == filterWeakReference.get();
    }

    public void destroy(@NonNull Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        View rootView = window.getDecorView();
        if (rootView instanceof ViewGroup) {
            rootView.removeOnLayoutChangeListener(this);
            applyChildColor((ViewGroup) rootView, null);
        }
    }
}
