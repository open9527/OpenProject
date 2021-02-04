package com.android.open9527.common.bundle;

import android.os.Bundle;


/**
 * @author open_9527
 * Create at 2021/1/18
 **/
public class BundleUtils {

    public static Bundle create(BaseBundleData baseBundleData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BaseBundleData.BUNDLE_NAME, baseBundleData);
        return bundle;
    }

    @SuppressWarnings("unchecked")
    public static <T extends BaseBundleData> T getBundleData(Bundle bundle) {
        if (bundle == null) return null;
        return (T) bundle.getSerializable(BaseBundleData.BUNDLE_NAME);
    }


}