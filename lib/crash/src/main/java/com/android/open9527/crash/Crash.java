package com.android.open9527.crash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import com.android.open9527.crash.compat.ActivityKillerV15_V20;
import com.android.open9527.crash.compat.ActivityKillerV21_V23;
import com.android.open9527.crash.compat.ActivityKillerV24_V25;
import com.android.open9527.crash.compat.ActivityKillerV26;
import com.android.open9527.crash.compat.ActivityKillerV28;
import com.android.open9527.crash.compat.IActivityKiller;
import com.android.open9527.crash.reflection.Reflection;

import java.lang.reflect.Field;


public final class Crash {

    private static IActivityKiller sActivityKiller;
    private static ExceptionHandler sExceptionHandler;
    //Mark bit to avoid repeated installation and uninstallation
    private static boolean sInstalled = false;
    private static boolean sIsSafeMode;

    private Crash() {
    }

    public static void install(Context ctx, ExceptionHandler exceptionHandler) {
        if (sInstalled) {
            return;
        }
        try {
            //解除 android P 反射限制
            Reflection.unseal(ctx);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        sInstalled = true;
        sExceptionHandler = exceptionHandler;

        initActivityKiller();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (sExceptionHandler != null) {
                    sExceptionHandler.uncaughtExceptionHappened(t, e);
                }
                if (t == Looper.getMainLooper().getThread()) {
                    isChoreographerException(e);
                    safeMode();
                }
            }
        });

    }

    /** * Replace ActivityThread.mH.mCallback to implement interception of the Activity life cycle. If you ignore the exception of the life cycle directly, it will cause a black screen. Currently * FinishActivity of the ActivityManager will be called to end the activity that throws the exception in the life cycle */
    private static void initActivityKiller() {
        if (Build.VERSION.SDK_INT >= 28) {
            sActivityKiller = new ActivityKillerV28();
        } else if (Build.VERSION.SDK_INT >= 26) {
            sActivityKiller = new ActivityKillerV26();
        } else if (Build.VERSION.SDK_INT == 25 || Build.VERSION.SDK_INT == 24) {
            sActivityKiller = new ActivityKillerV24_V25();
        } else if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 23) {
            sActivityKiller = new ActivityKillerV21_V23();
        } else if (Build.VERSION.SDK_INT >= 15 && Build.VERSION.SDK_INT <= 20) {
            sActivityKiller = new ActivityKillerV15_V20();
        } else if (Build.VERSION.SDK_INT < 15) {
            sActivityKiller = new ActivityKillerV15_V20();
        }

        try {
            hookmH();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void hookmH() throws Exception {

        final int LAUNCH_ACTIVITY = 100;
        final int PAUSE_ACTIVITY = 101;
        final int PAUSE_ACTIVITY_FINISHING = 102;
        final int STOP_ACTIVITY_HIDE = 104;
        final int RESUME_ACTIVITY = 107;
        final int DESTROY_ACTIVITY = 109;
        final int NEW_INTENT = 112;
        final int RELAUNCH_ACTIVITY = 126;
        @SuppressLint("PrivateApi") Class activityThreadClass = Class.forName("android.app.ActivityThread");
        @SuppressLint("DiscouragedPrivateApi") Object activityThread = activityThreadClass.getDeclaredMethod("currentActivityThread").invoke(null);

        Field mhField = activityThreadClass.getDeclaredField("mH");
        mhField.setAccessible(true);
        final Handler mhHandler = (Handler) mhField.get(activityThread);
        Field callbackField = Handler.class.getDeclaredField("mCallback");
        callbackField.setAccessible(true);
        callbackField.set(mhHandler, new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (Build.VERSION.SDK_INT >= 28) {//android P
                    final int EXECUTE_TRANSACTION = 159;
                    if (msg.what == EXECUTE_TRANSACTION) {
                        try {
                            assert mhHandler != null;
                            mhHandler.handleMessage(msg);
                        } catch (Throwable throwable) {
                            sActivityKiller.finishLaunchActivity(msg);
                            notifyException(throwable);
                        }
                        return true;
                    }
                    return false;
                }
                switch (msg.what) {
                    case LAUNCH_ACTIVITY:// startActivity--> activity.attach  activity.onCreate  r.activity!=null  activity.onStart  activity.onResume
                        try {
                            assert mhHandler != null;
                            mhHandler.handleMessage(msg);
                        } catch (Throwable throwable) {
                            sActivityKiller.finishLaunchActivity(msg);
                            notifyException(throwable);
                        }
                        return true;
                    case RESUME_ACTIVITY://
                        try {
                            assert mhHandler != null;
                            mhHandler.handleMessage(msg);
                        } catch (Throwable throwable) {
                            sActivityKiller.finishResumeActivity(msg);
                            notifyException(throwable);
                        }
                        return true;
                    case PAUSE_ACTIVITY_FINISHING://back onPause
                    case PAUSE_ACTIVITY://  activity.onPause
                        try {
                            assert mhHandler != null;
                            mhHandler.handleMessage(msg);
                        } catch (Throwable throwable) {
                            sActivityKiller.finishPauseActivity(msg);
                            notifyException(throwable);
                        }
                        return true;
                    case STOP_ACTIVITY_HIDE://  activity.onStop
                        try {
                            assert mhHandler != null;
                            mhHandler.handleMessage(msg);
                        } catch (Throwable throwable) {
                            sActivityKiller.finishStopActivity(msg);
                            notifyException(throwable);
                        }
                        return true;
                    case DESTROY_ACTIVITY:// finish activity onStop  onDestroy
                        try {
                            assert mhHandler != null;
                            mhHandler.handleMessage(msg);
                        } catch (Throwable throwable) {
                            notifyException(throwable);
                        }
                        return true;
                }
                return false;
            }
        });
    }


    private static void notifyException(Throwable throwable) {
        if (sExceptionHandler == null) {
            return;
        }
        if (isSafeMode()) {
            sExceptionHandler.bandageExceptionHappened(throwable);
        } else {
            sExceptionHandler.uncaughtExceptionHappened(Looper.getMainLooper().getThread(), throwable);
            safeMode();
        }
    }

    public static boolean isSafeMode() {
        return sIsSafeMode;
    }

    private static void safeMode() {
        sIsSafeMode = true;
        if (sExceptionHandler != null) {
            sExceptionHandler.enterSafeMode();
        }
        while (true) {
            try {
                Looper.loop();
            } catch (Throwable e) {
                isChoreographerException(e);
                if (sExceptionHandler != null) {
                    sExceptionHandler.bandageExceptionHappened(e);
                }
            }
        }
    }


    private static void isChoreographerException(Throwable e) {
        if (e == null || sExceptionHandler == null) {
            return;
        }
        StackTraceElement[] elements = e.getStackTrace();
        if (elements == null) {
            return;
        }

        for (int i = elements.length - 1; i > -1; i--) {
            if (elements.length - i > 20) {
                return;
            }
            StackTraceElement element = elements[i];
            if ("android.view.Choreographer".equals(element.getClassName())
                    && "Choreographer.java".equals(element.getFileName())
                    && "doFrame".equals(element.getMethodName())) {
                sExceptionHandler.mayBeBlackScreen(e);
                return;
            }

        }
    }


}
