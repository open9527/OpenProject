package com.android.open9527.filter.color;

import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;


import com.android.open9527.filter.AppFilter;
import com.android.open9527.filter.FilterColor;
import com.android.open9527.filter.NightColorFilter;
import com.android.open9527.filter.ObservableNightColorFilter;

import java.util.ArrayList;
import java.util.List;

public class NightColor extends FilterColor {

    private static List<ObservableNightColorFilter> filters = new ArrayList<>();

    public static void tintDialogFragment(@NonNull View view, @NonNull DialogFragment dialogFragment) {
        FilterColor filterColor = AppFilter.getColor();
        if (filterColor instanceof NightColor) {
            Paint paint = new Paint();
            ColorMatrix cm = new ColorMatrix(new float[]{
                    -1, 0, 0, 0, 255,
                    0, -1, 0, 0, 255,
                    0, 0, -1, 0, 255,
                    0, 0, 0, 1, 0
            });
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);

            if (view instanceof ViewGroup && dialogFragment instanceof NightColorFilter) {
                filters.add(new ObservableNightColorFilter((NightColorFilter) dialogFragment,
                        (ViewGroup) view));
            }
        } else {
            view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
    }

    public static void tintPopupWindow(@NonNull View view, NightColorFilter nightColorFilter) {
        FilterColor filterColor = AppFilter.getColor();
        if (filterColor instanceof NightColor) {
            Paint paint = new Paint();
            ColorMatrix cm = new ColorMatrix(new float[]{
                    -1, 0, 0, 0, 255,
                    0, -1, 0, 0, 255,
                    0, 0, -1, 0, 255,
                    0, 0, 0, 1, 0
            });
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);

            //TODO:
            if (view instanceof ViewGroup && nightColorFilter != null) {
                filters.add(new ObservableNightColorFilter(nightColorFilter,
                        (ViewGroup) view));
            }
        } else {
            view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

    }

    @Override
    public void tint(@NonNull Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        View view = window.getDecorView();
        Paint rootPaint = new Paint();
        ColorMatrix cm = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        });

        rootPaint.setColorFilter(new ColorMatrixColorFilter(cm));

        view.setLayerType(View.LAYER_TYPE_HARDWARE, rootPaint);
        if (view instanceof ViewGroup && activity instanceof NightColorFilter) {
            filters.add(new ObservableNightColorFilter((NightColorFilter) activity,
                    (ViewGroup) view));
        }

    }


    public static void revert(@NonNull View view) {
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        });

        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
    }

    @Override
    public void clear(@NonNull Activity activity) {
        for (ObservableNightColorFilter filter : filters) {
            if (filter != null && filter.match(activity)) {
                filter.destroy(activity);
            }
        }
    }
}
