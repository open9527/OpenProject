package com.android.open9527.filter;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AppFilter {

    private static List<Activity> activeActivities = new ArrayList<>();
    private static List<Class> excludeActivities = new ArrayList<>();
    private static FilterColor dressColor;

    public static void with(@NonNull Application app) {
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                checkActivity(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                activeActivities.remove(activity);
            }
        });
    }

    private static void checkActivity(@NonNull Activity activity) {
        Class currentClass = activity.getClass();
        if (!excludeActivities.contains(currentClass)) {
            activeActivities.add(activity);
            if (dressColor != null) {
                dressColor.tint(activity);
            }
        }
    }

    public static void tint(@Nullable FilterColor color) {
        FilterColor oldColor = dressColor;
        dressColor = color;
        for (Activity activity : activeActivities) {
            if (oldColor != null) {
                oldColor.clear(activity);
            }
            Window window = activity.getWindow();
            if (window == null) {
                continue;
            }
            if (dressColor == null) {
                window.getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, null);
            } else {
                dressColor.tint(activity);

            }
        }


    }

    public static FilterColor getColor() {
        return dressColor;
    }

    //移除指定activity  指定全类名
    public static void excludeActivity(String... strings) {
        for (String string : strings) {
            try {
                excludeActivities.add(Class.forName(string));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
