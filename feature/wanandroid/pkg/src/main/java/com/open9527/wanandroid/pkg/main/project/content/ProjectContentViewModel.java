package com.open9527.wanandroid.pkg.main.project.content;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.common.cell.CommonEmptyCell;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.blankj.utilcode.util.CollectionUtils;
import com.open9527.wanandroid.pkg.cell.ProjectCell;
import com.open9527.wanandroid.pkg.net.ContentVo;
import com.open9527.wanandroid.pkg.net.project.ProjectRequest;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/18
 **/
public class ProjectContentViewModel extends ViewModel {


    public final ObservableField<String> valueTitle = new ObservableField<>();


    public final ObservableBoolean valueIsRefresh = new ObservableBoolean(true);
    public final ObservableBoolean valueCloseHeaderOrFooter = new ObservableBoolean(false);

    public final ObservableBoolean valueNoMoreData = new ObservableBoolean(false);
    public final ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<>();

    public final ObservableField<ProjectCell.ICellClick> valueICellClick = new ObservableField<>();


    public final ProjectRequest projectRequest = new ProjectRequest();


    protected void onCreateCells(int page, List<ContentVo> contentVos) {
        if (page == 0 && valueCells.size() > 0) valueCells.clear();
        valueIsRefresh.set(page == 0);

        if (CollectionUtils.isNotEmpty(contentVos)) {
            for (ContentVo contentVo : contentVos) {
                if (contentVo == null) continue;
                ProjectCell projectCell=   new ProjectCell(contentVo);
                projectCell.valueICellClick.set(valueICellClick.get());
                valueCells.add(projectCell);
            }
            valueNoMoreData.set(false);
        } else {
            if (page == 0) valueCells.add(new CommonEmptyCell());
            valueNoMoreData.set(true);
        }
        valueCloseHeaderOrFooter.set(true);
    }
}
