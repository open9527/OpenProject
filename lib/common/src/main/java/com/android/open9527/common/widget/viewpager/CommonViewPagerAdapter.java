
package com.android.open9527.common.widget.viewpager;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/

public class CommonViewPagerAdapter extends PagerAdapter {

    private final int count;
    private final boolean enableDestroyItem;
    private final String[] title;

    public CommonViewPagerAdapter(int count, boolean enableDestroyItem, String[] title) {
        this.count = count;
        this.enableDestroyItem = enableDestroyItem;
        this.title = title;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return container.getChildAt(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (enableDestroyItem) {
            container.removeView((View) object);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}

