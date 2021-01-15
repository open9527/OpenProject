package com.open9527.wanandroid.pkg.binding.tablayout;

import android.view.LayoutInflater;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.android.open9527.common.widget.CommonFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.open9527.wanandroid.pkg.BR;
import com.open9527.wanandroid.pkg.R;
import com.open9527.wanandroid.pkg.bean.TabBean;

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
                    List<TabBean> tabBeans
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
                    binding.setVariable(BR.defaultIcon, tabBeans.get(i).getDefaultIcon());
                    binding.setVariable(BR.defaultText, tabBeans.get(i).getDefaultText());
                    binding.setVariable(BR.selectedText, "");
                    binding.setVariable(BR.selectedTextColor, tabBeans.get(i).getSelectedTextColor());
                    binding.setVariable(BR.defaultTextColor, tabBeans.get(i).getDefaultTextColor());
                    binding.setVariable(BR.select, i == defaultIndex);
                    binding.setVariable(BR.drawableWidth, i == defaultIndex ? 60 : 50);
                    binding.setVariable(BR.drawableHigh, i == defaultIndex ? 60 : 50);
                    binding.setVariable(BR.drawablePadding, i == defaultIndex ? 0 : 10);
                    TabLayout.Tab tab = tabLayout.newTab();
                    tab.setCustomView(binding.getRoot());
                    tab.setTag(i);
                    tabLayout.addTab(tab);
                }
            }
            Objects.requireNonNull(tabLayout.getTabAt(defaultIndex)).select();

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    setSelect(tab, tabBeans, true);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    setSelect(tab, tabBeans, false);
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    setSelect(tab, tabBeans, true);
                }
            });
        }
    }


    private static void setSelect(TabLayout.Tab tab, List<TabBean> tabBeans, boolean select) {
        ViewDataBinding binding = DataBindingUtil.getBinding(Objects.requireNonNull(tab.getCustomView()));
        assert binding != null;
//        binding.setVariable(BR.defaultTextColor, select ? tabBeans.get(tab.getPosition()).getSelectedTextColor() : tabBeans.get(tab.getPosition()).getDefaultTextColor());
//        binding.setVariable(BR.defaultIcon, tabBeans.get(tab.getPosition()).getDefaultIcon());
//        binding.setVariable(BR.defaultText, tabBeans.get(tab.getPosition()).getDefaultText());
//        binding.setVariable(BR.selectedTextColor, tabBeans.get(tab.getPosition()).getSelectedTextColor());
//        binding.setVariable(BR.defaultTextColor, tabBeans.get(tab.getPosition()).getDefaultTextColor());
        binding.setVariable(BR.select, select);
    }

}
