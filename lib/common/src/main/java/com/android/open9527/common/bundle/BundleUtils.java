package com.android.open9527.common.bundle;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.GsonUtils;


/**
 * @author open_9527
 * Create at 2021/1/18
 **/
public final class BundleUtils {

    //同model
    public static Bundle create(BaseBundleData baseBundleData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BaseBundleData.BUNDLE_NAME, baseBundleData);
        return bundle;
    }

    @SuppressWarnings("unchecked")
    public static <T extends BaseBundleData> T getBundleData(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return (T) bundle.getSerializable(BaseBundleData.BUNDLE_NAME);
    }


    //跨model
    public static Bundle createBundleJson(@Nullable BaseBundleData baseBundleData) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseBundleData.BUNDLE_NAME, GsonUtils.toJson(baseBundleData));
        return bundle;
    }

    public static <T extends BaseBundleData> T getBundleData(@Nullable Bundle bundle, @NonNull final Class<T> type) {
        assert bundle != null;
        String json =  bundle.getString(BaseBundleData.BUNDLE_NAME);
        if (!TextUtils.isEmpty(json)) {
            return GsonUtils.fromJson(json, type);
        }
        return null;
    }


}