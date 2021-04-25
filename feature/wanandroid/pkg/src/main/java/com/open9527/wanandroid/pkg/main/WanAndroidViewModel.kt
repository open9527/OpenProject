package com.open9527.wanandroid.pkg.main

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.StringUtils
import com.open9527.wanandroid.pkg.R
import com.open9527.wanandroid.pkg.bean.TabBean
import com.open9527.wanandroid.pkg.main.article.ArticleFragment
import com.open9527.wanandroid.pkg.main.project.ProjectFragment
import com.open9527.wanandroid.pkg.main.share.ShareFragment

/**
 * @author open_9527
 * Create at 2021/1/12
 */
class WanAndroidViewModel : ViewModel() {

    val valueTabList = ObservableArrayList<TabBean>()

    val valueFragments = ObservableArrayList<Fragment>()

    val valueFragmentManager = ObservableField<FragmentManager>()

    val valueDefaultIndex = ObservableInt(1)



    fun initTab(fragmentManager: FragmentManager) {
        valueTabList.clear()
        valueTabList.add(
            TabBean(
                StringUtils.getString(R.string.wan_main_tab_project),
                R.drawable.project_tab_icon
            )
        )
        valueTabList.add(
            TabBean(
                StringUtils.getString(R.string.wan_main_tab_article),
                R.drawable.article_tab_icon
            )
        )
        valueTabList.add(
            TabBean(
                StringUtils.getString(R.string.wan_main_tab_share),
                R.drawable.share_tab_icon
            )
        )
        valueFragments.clear()
        valueFragments.add(ProjectFragment.newInstance())
        valueFragments.add(ArticleFragment.newInstance())
        valueFragments.add(ShareFragment.newInstance())
        valueFragmentManager.set(fragmentManager)
    }
}