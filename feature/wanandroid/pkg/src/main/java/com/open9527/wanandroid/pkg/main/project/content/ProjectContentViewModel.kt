package com.open9527.wanandroid.pkg.main.project.content

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.android.open9527.common.cell.CommonEmptyCell
import com.android.open9527.recycleview.adapter.BaseBindingCell
import com.blankj.utilcode.util.CollectionUtils
import com.open9527.wanandroid.pkg.cell.ProjectCell
import com.open9527.wanandroid.pkg.net.ContentVo
import com.open9527.wanandroid.pkg.net.project.ProjectRequest

/**
 * @author open_9527
 * Create at 2021/1/18
 */
class ProjectContentViewModel : ViewModel() {
    @JvmField
    val valueTitle = ObservableField<String>()

    @JvmField
    val valueNoMoreData = ObservableBoolean(false)

    @JvmField
    var valueCells = ObservableArrayList<BaseBindingCell<*>>()

    val valueICellClick = ObservableField<ProjectCell.ICellClick>()

    @JvmField
    val projectRequest = ProjectRequest()

    fun onCreateCells(page: Int, contentVos: List<ContentVo?>) {
        if (page == 0 && valueCells.size > 0) valueCells.clear()
        if (CollectionUtils.isNotEmpty(contentVos)) {
            for (contentVo in contentVos) {
                if (contentVo == null) continue
                valueCells.add(valueICellClick.get()?.let { ProjectCell(contentVo, it) })
            }
//            valueNoMoreData.set(contentVos.size < 20)
            valueNoMoreData.set(false)
        } else {
            if (page == 0) valueCells.add(CommonEmptyCell())
            valueNoMoreData.set(true)
        }
    }
}