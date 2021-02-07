package com.open9527.wanandroid.pkg.binding.tablayout;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.open9527.wanandroid.pkg.bean.TabBean;
import com.open9527.wanandroid.pkg.widget.MainTabLayout;
import com.open9527.wanandroid.pkg.widget.ProjectTabLayout;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class TabLayoutBindingAdapter {

    @BindingAdapter(value = {
            "bindTabLayoutFragmentManager",
            "bindTabLayoutFragmentList",
            "bindTabLayoutDefaultIndex",
            "bindTabLayoutTabBeanData"
    },
            requireAll = false)
    public static void setBindingMainTabLayoutAdapter
            (
                    TabLayout tabLayout,
                    FragmentManager fragmentManager,
                    List<Fragment> fragmentList,
                    int defaultIndex, List<TabBean> tabBeans
            ) {
        if (tabLayout == null || fragmentManager == null) return;
        if (tabLayout instanceof MainTabLayout) {
            MainTabLayout mainTabLayout = (MainTabLayout) tabLayout;
            mainTabLayout.init(fragmentManager, fragmentList, defaultIndex, tabBeans);

        }
        if (tabLayout instanceof ProjectTabLayout) {
            ProjectTabLayout projectTabLayout = (ProjectTabLayout) tabLayout;
            projectTabLayout.init(fragmentManager, fragmentList, defaultIndex, tabBeans);

        }

    }
}

