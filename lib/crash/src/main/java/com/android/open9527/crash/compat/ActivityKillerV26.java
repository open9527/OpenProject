package com.android.open9527.crash.compat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ActivityKillerV26 implements IActivityKiller {


    @Override
    public void finishLaunchActivity(Message message) {
        try {
            Object activityClientRecord = message.obj;

            Field tokenField = activityClientRecord.getClass().getDeclaredField("token");

            tokenField.setAccessible(true);
            IBinder binder = (IBinder) tokenField.get(activityClientRecord);
            finish(binder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void finishResumeActivity(Message message) {

        finishSomeArgs(message);
    }


    @Override
    public void finishPauseActivity(Message message) {

        finishSomeArgs(message);
    }

    @Override
    public void finishStopActivity(Message message) {
        finishSomeArgs(message);
    }


    private void finishSomeArgs(Message message) {
        try {
            Object someArgs = message.obj;
            Field arg1Field = someArgs.getClass().getDeclaredField("arg1");
            arg1Field.setAccessible(true);
            IBinder binder = (IBinder) arg1Field.get(someArgs);
            finish(binder);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void finish(IBinder binder) throws Exception {
        @SuppressLint("DiscouragedPrivateApi") Method getServiceMethod = ActivityManager.class.getDeclaredMethod("getService");
        Object activityManager = getServiceMethod.invoke(null);

        Method finishActivityMethod = activityManager.getClass().getDeclaredMethod("finishActivity", IBinder.class, int.class, Intent.class, int.class);
        finishActivityMethod.setAccessible(true);
        int DONT_FINISH_TASK_WITH_ACTIVITY = 0;
        finishActivityMethod.invoke(activityManager, binder, Activity.RESULT_CANCELED, null, DONT_FINISH_TASK_WITH_ACTIVITY);

    }
}
