package com.open9527.router.info;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;


import com.open9527.annotation.router.RouteMeta;
import com.open9527.router.Router;
import com.open9527.router.callback.NavigationCallback;
import com.open9527.router.inter.IService;

import java.util.ArrayList;

/**
 * @author open_9527
 * Create at 2021/3/1
 **/
public class Postcard extends RouteMeta {

    private Bundle mBundle;
    private int flags = -1;
    //新版风格
    private Bundle optionsCompat;
    //转场动画
    private int enterAnim;
    private int exitAnim;
    //服务
    private IService service;

    public Postcard(String path, String group) {
        this(path, group, null);
    }

    private Postcard(String path, String group, Bundle bundle) {
        setPath(path);
        setGroup(group);
        this.mBundle = (null == bundle ? new Bundle() : bundle);
    }

    public Bundle getExtras() {
        return mBundle;
    }

    public int getEnterAnim() {
        return enterAnim;
    }

    public int getExitAnim() {
        return exitAnim;
    }

    public IService getService() {
        return service;
    }

    public void setService(IService service) {
        this.service = service;
    }

    /**
     * 设置flag
     *
     * @param flag flag
     * @return Postcard
     */
    public Postcard withFlags(int flag) {
        this.flags = flag;
        return this;
    }

    public int getFlags() {
        return flags;
    }

    /**
     * 跳转动画
     *
     * @param enterAnim 进入动画
     * @param exitAnim  退出动画
     * @return Postcard
     */
    public Postcard withTransition(int enterAnim, int exitAnim) {
        this.enterAnim = enterAnim;
        this.exitAnim = exitAnim;
        return this;
    }

    /**
     * 转场动画
     *
     * @param compat compat
     * @return Postcard
     */
    public Postcard withOptionsCompat(ActivityOptionsCompat compat) {
        if (null != compat) {
            this.optionsCompat = compat.toBundle();
        }
        return this;
    }

    public Postcard withBundle(Bundle bundle) {
        if (null != bundle) {
            mBundle = bundle;
        }
        return this;
    }

    public Postcard withString(@Nullable String key, @Nullable String value) {
        mBundle.putString(key, value);
        return this;
    }

    public Postcard withBoolean(@Nullable String key, boolean value) {
        mBundle.putBoolean(key, value);
        return this;
    }


    public Postcard withShort(@Nullable String key, short value) {
        mBundle.putShort(key, value);
        return this;
    }


    public Postcard withInt(@Nullable String key, int value) {
        mBundle.putInt(key, value);
        return this;
    }


    public Postcard withLong(@Nullable String key, long value) {
        mBundle.putLong(key, value);
        return this;
    }


    public Postcard withDouble(@Nullable String key, double value) {
        mBundle.putDouble(key, value);
        return this;
    }


    public Postcard withByte(@Nullable String key, byte value) {
        mBundle.putByte(key, value);
        return this;
    }


    public Postcard withChar(@Nullable String key, char value) {
        mBundle.putChar(key, value);
        return this;
    }


    public Postcard withFloat(@Nullable String key, float value) {
        mBundle.putFloat(key, value);
        return this;
    }


    public Postcard withParcelable(@Nullable String key, @Nullable Parcelable value) {
        mBundle.putParcelable(key, value);
        return this;
    }


    public Postcard withStringArray(@Nullable String key, @Nullable String[] value) {
        mBundle.putStringArray(key, value);
        return this;
    }


    public Postcard withBooleanArray(@Nullable String key, boolean[] value) {
        mBundle.putBooleanArray(key, value);
        return this;
    }


    public Postcard withShortArray(@Nullable String key, short[] value) {
        mBundle.putShortArray(key, value);
        return this;
    }


    public Postcard withIntArray(@Nullable String key, int[] value) {
        mBundle.putIntArray(key, value);
        return this;
    }


    public Postcard withLongArray(@Nullable String key, long[] value) {
        mBundle.putLongArray(key, value);
        return this;
    }


    public Postcard withDoubleArray(@Nullable String key, double[] value) {
        mBundle.putDoubleArray(key, value);
        return this;
    }


    public Postcard withByteArray(@Nullable String key, byte[] value) {
        mBundle.putByteArray(key, value);
        return this;
    }


    public Postcard withCharArray(@Nullable String key, char[] value) {
        mBundle.putCharArray(key, value);
        return this;
    }


    public Postcard withFloatArray(@Nullable String key, float[] value) {
        mBundle.putFloatArray(key, value);
        return this;
    }


    public Postcard withParcelableArray(@Nullable String key, @Nullable Parcelable[] value) {
        mBundle.putParcelableArray(key, value);
        return this;
    }

    public Postcard withParcelableArrayList(@Nullable String key, @Nullable ArrayList<? extends
            Parcelable> value) {
        mBundle.putParcelableArrayList(key, value);
        return this;
    }

    public Postcard withIntegerArrayList(@Nullable String key, @Nullable ArrayList<Integer> value) {
        mBundle.putIntegerArrayList(key, value);
        return this;
    }

    public Postcard withStringArrayList(@Nullable String key, @Nullable ArrayList<String> value) {
        mBundle.putStringArrayList(key, value);
        return this;
    }

    public Bundle getOptionsBundle() {
        return optionsCompat;
    }

    public Object navigation() {
        return Router.getsInstance().navigation(null, this, -1, null);
    }

    public Object navigation(Context context) {
        return Router.getsInstance().navigation(context, this, -1, null);
    }


    public Object navigation(Context context, NavigationCallback callback) {
        return Router.getsInstance().navigation(context, this, -1, callback);
    }

    public Object navigation(Context context, int requestCode) {
        return Router.getsInstance().navigation(context, this, requestCode, null);
    }

    public Object navigation(Context context, int requestCode, NavigationCallback callback) {
        return Router.getsInstance().navigation(context, this, requestCode, callback);
    }


}
