package com.android.open9527.crash.compat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ActivityKillerV21_V23 implements IActivityKiller {


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

        try {
            finish((IBinder) message.obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void finishPauseActivity(Message message) {

        try {
            finish((IBinder) message.obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finishStopActivity(Message message) {
        try {
            finish((IBinder) message.obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void finish(IBinder binder) throws Exception {

        @SuppressLint("PrivateApi") Class activityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");

        @SuppressLint({"DiscouragedPrivateApi", "PrivateApi"}) Method getDefaultMethod = activityManagerNativeClass.getDeclaredMethod("getDefault");

        Object activityManager = getDefaultMethod.invoke(null);


        Method finishActivityMethod = activityManager.getClass().getDeclaredMethod("finishActivity", IBinder.class, int.class, Intent.class, boolean.class);
        finishActivityMethod.invoke(activityManager, binder, Activity.RESULT_CANCELED, null, false);

    }

}
