package com.open9527.wanandroid.pkg.binding.tablayout

import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.open9527.wanandroid.pkg.bean.TabBean
import com.open9527.wanandroid.pkg.widget.MainTabLayout
import com.open9527.wanandroid.pkg.widget.ProjectTabLayout

/**
 * @author open_9527
 * Create at 2021/1/12
 */
object TabLayoutBindingAdapter {
    @JvmStatic
    @BindingAdapter(
        value = ["bindTabLayoutFragmentManager", "bindTabLayoutFragmentList", "bindTabLayoutDefaultIndex", "bindTabLayoutTabBeanData"],
        requireAll = false
    )
    fun setBindingMainTabLayoutAdapter(
        tabLayout: TabLayout?,
        fragmentManager: FragmentManager?,
        fragmentList: List<Fragment?>?,
        defaultIndex: Int, tabBeans: List<TabBean?>?
    ) {
        if (tabLayout == null || fragmentManager == null) return
        if (tabLayout is MainTabLayout) {
            tabLayout.init(fragmentManager, fragmentList!!, defaultIndex, tabBeans)
        }
        if (tabLayout is ProjectTabLayout) {
            tabLayout.init(fragmentManager, fragmentList!!, defaultIndex, tabBeans)
        }
    }
}