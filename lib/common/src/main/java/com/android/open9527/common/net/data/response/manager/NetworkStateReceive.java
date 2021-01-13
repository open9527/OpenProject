package com.android.open9527.common.net.data.response.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.android.open9527.common.R;
import com.blankj.utilcode.util.NetworkUtils;

import java.util.Objects;


/**
 * @author open_9527
 * Create at 2021/1/4
 **/

public class NetworkStateReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (!NetworkUtils.isConnected()) {
                Toast.makeText(context, context.getString(R.string.network_not_good), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
