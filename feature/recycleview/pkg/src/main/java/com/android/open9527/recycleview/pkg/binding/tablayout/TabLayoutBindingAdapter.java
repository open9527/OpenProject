package com.android.open9527.recycleview.pkg.binding.tablayout;

import android.view.LayoutInflater;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.android.open9527.common.widget.CommonFragmentPagerAdapter;
import com.android.open9527.recycleview.pkg.bean.MainTabBean;
import com.android.open9527.recycleview.pkg.BR;
import com.android.open9527.recycleview.pkg.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Objects;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class TabLayoutBindingAdapter {

    @BindingAdapter(value = {
            "bindTabLayoutFragmentManager",
            "bindTabLayoutFragmentList",
            "bindTabLayoutDefaultIndex",
            "bindTabLayoutItemLayout",
            "bindTabLayoutTabBeanData"
    },
            requireAll = false)
    public static void setBindingTabLayoutAdapter
            (
                    TabLayout tabLayout,
                    FragmentManager fragmentManager,
                    List<Fragment> fragments,
                    int defaultIndex,
                    int tabItemLayout,
                    List<MainTabBean> tabBeans
            ) {
        if (tabLayout == null) return;
        ViewPager viewPager = (tabLayout.getRootView()).findViewById(R.id.view_pager);
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(fragments.size());
            viewPager.setAdapter(new CommonFragmentPagerAdapter(fragmentManager, fragments));
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.removeAllTabs();
            if (tabBeans != null && tabBeans.size() > 0) {
                for (int i = 0; i < tabBeans.size(); i++) {
                    final ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(tabLayout.getContext()), tabItemLayout, null, false);
                    binding.setVariable(BR.defaultDrawableIcon, tabBeans.get(i).getDefaultDrawableIcon());
                    binding.setVariable(BR.defaultText, tabBeans.get(i).getDefaultText());
                    binding.setVariable(BR.defaultTextColor, tabBeans.get(i).getDefaultTextColor());
                    TabLayout.Tab tab = tabLayout.newTab();
                    tab.setCustomView(binding.getRoot());
                    tab.setTag(i);
                    tabLayout.addTab(tab);
                }
            }
            Objects.requireNonNull(tabLayout.getTabAt(defaultIndex)).select();
        }
    }


    @BindingAdapter(value = {"bindTabLayoutSelectedListener"}, requireAll = false)
    public static void setBindingTabSelectedListener(TabLayout tabLayout, TabLayout.OnTabSelectedListener listener) {
        if (tabLayout == null) return;

        if (listener != null) {
            tabLayout.addOnTabSelectedListener(listener);
        }

    }

}
