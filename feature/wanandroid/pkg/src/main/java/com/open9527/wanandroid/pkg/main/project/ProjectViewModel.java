package com.open9527.wanandroid.pkg.main.project;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.recycleview.adapter.BaseBindingCell;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class ProjectViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("article");


    public final ObservableBoolean valueNoMoreData = new ObservableBoolean(false);
    public final ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<>();

}
