package com.android.open9527.common.bundle;

import android.os.Bundle;

import androidx.annotation.Nullable;


/**
 * @author open_9527
 * Create at 2021/1/18
 **/
public final class BundleUtils {

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


}