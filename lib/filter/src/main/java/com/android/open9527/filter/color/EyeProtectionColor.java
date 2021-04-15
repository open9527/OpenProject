package com.android.open9527.filter.color;

import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;
import android.view.Window;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

import com.android.open9527.filter.FilterColor;


public class EyeProtectionColor extends FilterColor {

    private float weight;

    /**
     * @param weight 去蓝光权重，取值越大蓝光保留的就越少
     */
    public EyeProtectionColor(@FloatRange(from = 0.0f, to = 1.0f) float weight) {
        this.weight = weight;
    }

    @Override
    public void tint(@NonNull Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        View view = window.getDecorView();
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1 - weight, 0, 0,
                0, 0, 0, 1, 0
        });
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
    }

    @Override
    public void clear(@NonNull Activity activity) {

    }
}
