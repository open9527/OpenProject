package com.android.open9527.common.binding.viewpage;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;

import java.util.List;


/**
 * @author open_9527
 * Create at 2021/1/25
 **/
public class ViewPagerBindingAdapter {

    @BindingAdapter(value = {"bindVpAdapter", "bindVpIndex","bindVpSmoothScroll", "bindVpChangeChangeListener"}, requireAll = false)
    public static void setBindingViewPager(ViewPager viewPager, PagerAdapter pagerAdapter, int index,boolean smoothScroll, ViewPager.OnPageChangeListener onPageChangeListener) {
        if (viewPager == null) return;
        if (pagerAdapter != null && viewPager.getAdapter() == null) {
            viewPager.setAdapter(pagerAdapter);
        }
        if (onPageChangeListener != null) {
            viewPager.addOnPageChangeListener(onPageChangeListener);
        }
        viewPager.setCurrentItem(index, smoothScroll);
    }

    @BindingAdapter(value = {"bindVpAdapter"}, requireAll = false)
    public static void setBindingViewPager2(ViewPager2 viewPager, RecyclerView.Adapter mAdapter) {
        if (viewPager == null || mAdapter == null) return;
        viewPager.setAdapter(mAdapter);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"bindVpList", "bindVpIndex", "bindVpSmoothScroll", "bindVpChangeCallback"}, requireAll = false)
    public static void setBindingViewPager2(ViewPager2 viewPager, List list, int index, boolean smoothScroll, ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        if (viewPager == null) return;
        if (viewPager.getAdapter() != null) {
            RecyclerView.Adapter adapter = viewPager.getAdapter();
            if (adapter instanceof ListAdapter) {
                ListAdapter listAdapter = (ListAdapter) adapter;
                listAdapter.submitList(list);
            }
        }
        if (onPageChangeCallback != null) {
            viewPager.registerOnPageChangeCallback(onPageChangeCallback);
        }
        viewPager.setCurrentItem(index, smoothScroll);
    }

}
