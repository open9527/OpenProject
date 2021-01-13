package com.android.open9527.common.net.data.response.manager;

import android.content.IntentFilter;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.Utils;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/

public class NetworkStateManager implements DefaultLifecycleObserver {

    private static final NetworkStateManager S_MANAGER = new NetworkStateManager();
    private NetworkStateReceive mNetworkStateReceive;

    private NetworkStateManager() {
    }

    public static NetworkStateManager getInstance() {
        return S_MANAGER;
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        mNetworkStateReceive = new NetworkStateReceive();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        Utils.getApp().getApplicationContext().registerReceiver(mNetworkStateReceive, filter);
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Utils.getApp().getApplicationContext().unregisterReceiver(mNetworkStateReceive);
    }
}
