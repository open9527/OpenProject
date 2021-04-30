package com.android.custom.pkg.webview.bridge;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/

public class BridgeWebPager2Adapter extends FragmentStateAdapter {

    public BridgeWebPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? BridgeWebFragment.newInstance() : BridgeContainerFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
