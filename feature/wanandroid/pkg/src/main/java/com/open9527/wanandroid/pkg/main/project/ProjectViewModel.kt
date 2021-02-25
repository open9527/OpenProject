package com.open9527.wanandroid.pkg.main.project

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.CollectionUtils
import com.open9527.wanandroid.pkg.bean.TabBean
import com.open9527.wanandroid.pkg.main.project.content.ProjectContentFragment
import com.open9527.wanandroid.pkg.net.project.ProjectRequest
import com.open9527.wanandroid.pkg.net.project.ProjectTreeVo

/**
 * @author open_9527
 * Create at 2021/1/12
 */
class ProjectViewModel : ViewModel() {
    val valueTitle = ObservableField("Project")

    @JvmField
    val valueTitleBarBg = ObservableField<Drawable>(ColorDrawable(Color.WHITE))

    @JvmField
    val valueTabList = ObservableArrayList<TabBean>()

    @JvmField
    val valueFragments = ObservableArrayList<Fragment>()

    @JvmField
    val valueFragmentManager = ObservableField<FragmentManager>()

    @JvmField
    val valueDefaultIndex = ObservableInt(0)

    val projectRequest = ProjectRequest()
     fun initTab(fragmentManager: FragmentManager, projectTreeVos: List<ProjectTreeVo?>) {
        valueTabList.clear()
        valueFragments.clear()
        valueFragmentManager.set(fragmentManager)
        if (CollectionUtils.isNotEmpty(projectTreeVos)) {
            for (projectTreeVo in projectTreeVos) {
                valueFragments.add(
                    ProjectContentFragment.newInstance(
                        projectTreeVo!!.id,
                        projectTreeVo.title
                    )
                )
                valueTabList.add(TabBean(projectTreeVo.title))
            }
        }
    }
}