package com.android.open9527.common.initialize.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.open9527.crash.Crash;
import com.android.open9527.crash.ExceptionHandler;
import com.blankj.utilcode.util.AppUtils;


/**
 * @author open_9527
 * Create at 2021/1/6
 * <p>
 * <p>
 * eg:ContentProvider 初始化
 *
 * <application>
 * <provider
 * android:name=".Contextprovider"
 * android:authorities="${applicationId}.contextprovider"
 * android:exported="false" />
 * </application>
 **/


public class CrashInitProvider extends ContentProvider {

    private static String TAG = "CrashInitProvider";

    @Override
    public boolean onCreate() {
        //初始化
//        install(this.getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


    private void install(Context context) {
        Crash.install(context, new ExceptionHandler() {
            @Override
            protected void onUncaughtExceptionHappened(Thread thread, Throwable throwable) {

            }

            @Override
            protected void onBandageExceptionHappened(Throwable throwable) {

            }

            @Override
            protected void onEnterSafeMode() {

            }

            @Override
            protected void onMayBeBlackScreen(Throwable e) {
                //TODO:黑屏,最好是重启app
                AppUtils.relaunchApp(true);
            }

        });

//        Log.i(TAG, "Crash is  install !");

    }
}
