package com.open9527.wanandroid.pkg.bean;

import android.os.Bundle;

/**
 * @author open_9527
 * Create at 2021/1/18
 **/
public class BundleCreate {

    public static Bundle create(BaseBundleData baseBundleData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BaseBundleData.BUNDLE_NAME, baseBundleData);
        return bundle;
    }



}