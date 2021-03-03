package com.android.feature.webview.pkg;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author open_9527
 * Create at 2021/3/2
 **/
public final class DimenHelper {
    public static final int NOT_CHANGE = -100;

    public static void updateLayout(View view, int w, int h) {
        if (view == null) {
            return;
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            return;
        }

        boolean changed = false;
        if (w != NOT_CHANGE && params.width != w) {
            changed = true;
            params.width = w;
        }
        if (h != NOT_CHANGE && params.height != h) {
            changed = true;
            params.height = h;
        }

        if (changed) {
            view.setLayoutParams(params);
        }
    }
}
