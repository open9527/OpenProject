package com.open9527.wanandroid.pkg.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
 * Create at 2021/1/18
 **/
public class MainTabLayout extends TabLayout {

    public MainTabLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public MainTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(@NonNull FragmentManager fragmentManager, @NonNull List<Fragment> fragmentList, int defaultIndex, List<TabBean> tabBeans) {
        ViewPager viewPager = getRootView().findViewById(R.id.main_view_pager);
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(fragmentList.size());
            viewPager.setAdapter(new CommonFragmentPagerAdapter(fragmentManager, fragmentList));
            setupWithViewPager(viewPager);
            removeAllTabs();
            if (tabBeans.size() > 0) {
                for (int i = 0; i < tabBeans.size(); i++) {
                    final ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.main_tab_item, null, false);
                    binding.setVariable(BR.defaultIcon, tabBeans.get(i).getDefaultIcon());
                    binding.setVariable(BR.defaultText, tabBeans.get(i).getDefaultText());
                    binding.setVariable(BR.select, i == defaultIndex);
                    binding.setVariable(BR.drawableWidth, i == defaultIndex ? 60 : 50);
                    binding.setVariable(BR.drawableHigh, i == defaultIndex ? 60 : 50);
                    binding.setVariable(BR.drawablePadding, i == defaultIndex ? 0 : 10);
                    TabLayout.Tab tab = newTab();
                    tab.setCustomView(binding.getRoot());
                    tab.setTag(String.valueOf(tabBeans.get(i).getDefaultText() + i));
                    addTab(tab);
                }
                Objects.requireNonNull(getTabAt(defaultIndex)).select();

                addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        setSelect(tab, true);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        setSelect(tab, false);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        setSelect(tab, true);
                    }
                });
            }

        }


    }

    private void setSelect(TabLayout.Tab tab, boolean select) {
        ViewDataBinding binding = DataBindingUtil.getBinding(Objects.requireNonNull(tab.getCustomView()));
        assert binding != null;
        binding.setVariable(BR.select, select);
    }
}
