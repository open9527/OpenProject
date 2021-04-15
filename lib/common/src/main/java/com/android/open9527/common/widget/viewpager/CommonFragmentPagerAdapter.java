package com.android.open9527.common.widget.viewpager;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/4
 **/

public class CommonFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> list;

    @SuppressLint("WrongConstant")
    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.list = list;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         super.destroyItem(container, position, object);
    }
//
//    @Nullable
//    @Override
//    public Parcelable saveState() {
//        return null;
//    }
}
