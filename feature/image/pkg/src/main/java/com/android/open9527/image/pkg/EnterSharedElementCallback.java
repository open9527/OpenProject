package com.android.open9527.image.pkg;

import android.view.View;

import androidx.core.app.SharedElementCallback;

import java.util.List;
import java.util.Map;

/**
 * @author open_9527
 * Create at 2021/2/1
 **/
public final class EnterSharedElementCallback extends SharedElementCallback {

    private String mTransitionName = "transition_name";
    private View mView;
    private LocationChangeEvent mLocationChangeEvent;

    public EnterSharedElementCallback(View mView) {
        this.mView = mView;
    }

    /**
     * 装载共享元素
     *
     * @param names          :
     * @param sharedElements :
     */
    @Override
    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
        if (mLocationChangeEvent != null) {
            int startingPosition = mLocationChangeEvent.getStartPosition();
            int currentPosition = mLocationChangeEvent.getCurrentPosition();
            if (startingPosition != currentPosition) {
                if (mView != null) {
                    names.clear();
                    names.add(mTransitionName);
                    sharedElements.clear();
                    sharedElements.put(mTransitionName, mView);
                }
            }
            mLocationChangeEvent = null;
        }
    }

    public void setView(View mView) {
        this.mView = mView;
    }

    public void setLocationChangeEvent(LocationChangeEvent mLocationChangeEvent) {
        this.mLocationChangeEvent = mLocationChangeEvent;
    }

    public void setTransitionName(String mTransitionName) {
        this.mTransitionName = mTransitionName;
    }
}