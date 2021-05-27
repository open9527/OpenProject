package com.android.custom.pkg;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.Utils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author open_9527
 * Create at 2021/5/27
 **/
public class IntentManage {

    /**
     * Activity 对象
     */
    private Activity mActivity;


    private IntentManage(@NonNull Activity activity) {
        mActivity = activity;
    }


    public static IntentManage with(@NonNull Context context) {
        return with(Objects.requireNonNull(getActivityByContext(context)));
    }





    /*------------------------------------------------------------------------------------------------*/

    private static Activity getActivityByContext(Context context) {
        Activity activity = getActivityByContextInner(context);
        if (!isActivityAlive(activity)) return null;
        return activity;
    }

    private static Activity getActivityByContextInner(Context context) {
        if (context == null) return null;
        List<Context> list = new ArrayList<>();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            Activity activity = getActivityFromDecorContext(context);
            if (activity != null) return activity;
            list.add(context);
            context = ((ContextWrapper) context).getBaseContext();
            if (context == null) {
                return null;
            }
            if (list.contains(context)) {
                // loop context
                return null;
            }
        }
        return null;
    }

    private static Activity getActivityFromDecorContext(Context context) {
        if (context == null) return null;
        if (context.getClass().getName().equals("com.android.internal.policy.DecorContext")) {
            try {
                Field mActivityContextField = context.getClass().getDeclaredField("mActivityContext");
                mActivityContextField.setAccessible(true);
                //noinspection ConstantConditions,unchecked
                return ((WeakReference<Activity>) mActivityContextField.get(context)).get();
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    private static boolean isActivityExists(@NonNull final String pkg,
                                            @NonNull final String cls) {
        Intent intent = new Intent();
        intent.setClassName(pkg, cls);
        PackageManager pm = Utils.getApp().getPackageManager();
        return !(pm.resolveActivity(intent, 0) == null ||
                intent.resolveActivity(pm) == null ||
                pm.queryIntentActivities(intent, 0).size() == 0);
    }

    private static boolean isActivityAlive(final Context context) {
        return isActivityAlive(getActivityByContext(context));
    }


    private static boolean isActivityAlive(final Activity activity) {
        return activity != null && !activity.isFinishing()
                && (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1 || !activity.isDestroyed());
    }
}
