package com.open9527.wanandroid.pkg.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.android.open9527.common.widget.viewpager.CommonFragmentPagerAdapter;
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
public class ProjectTabLayout extends TabLayout {

    public ProjectTabLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public ProjectTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProjectTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(@NonNull FragmentManager fragmentManager, @NonNull List<Fragment> fragmentList, int defaultIndex, List<TabBean> tabBeans) {
        //2131230995
        ViewPager viewPager = getRootView().findViewById(R.id.project_view_page);
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(fragmentList.size());
            viewPager.setAdapter(new CommonFragmentPagerAdapter(fragmentManager, fragmentList));
            setupWithViewPager(viewPager);
            removeAllTabs();
            if (tabBeans.size() > 0) {
                for (int i = 0; i < tabBeans.size(); i++) {
                    final ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.project_tab_item, null, false);
                    binding.setVariable(BR.title, tabBeans.get(i).getDefaultText());
                    binding.setVariable(BR.select, i == defaultIndex);
                    Tab tab = newTab();
                    tab.setCustomView(binding.getRoot());
                    tab.setTag(String.valueOf(tabBeans.get(i).getDefaultText() + i));
                    addTab(tab);
                }
//                View tabStripView = getChildAt(0);
//                tabStripView.setBackground(new TabIndicatorDrawable(tabStripView, R.color.color_text_black));//设置背景 添加自定义下划线
                setSelectedTabIndicator(R.drawable.layer_list_tab_indicator);
                setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.gold));

                Objects.requireNonNull(getTabAt(defaultIndex)).select();


                addOnTabSelectedListener(new OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(Tab tab) {
                        setSelect(tab, true);
                    }

                    @Override
                    public void onTabUnselected(Tab tab) {
                        setSelect(tab, false);
                    }

                    @Override
                    public void onTabReselected(Tab tab) {
                        setSelect(tab, true);
                    }
                });
            }
        }
    }

    private void setSelect(Tab tab, boolean select) {
        ViewDataBinding binding = DataBindingUtil.getBinding(Objects.requireNonNull(tab.getCustomView()));
        assert binding != null;
        binding.setVariable(BR.select, select);
    }
}
