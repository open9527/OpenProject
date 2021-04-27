package com.android.appmanager.pkg;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CollectionUtils;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/2/5
 **/
public class AppManagerViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("AppManager");
    public final ObservableField<Drawable> valueTitleBarBg = new ObservableField<>(new ColorDrawable(Color.WHITE));


    public final ObservableBoolean valueNoMoreData = new ObservableBoolean(true);
    public final ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<>();

    void initData(List<AppUtils.AppInfo> appInfoList) {
         valueCells.clear();
        if (CollectionUtils.isNotEmpty(appInfoList)) {
            for (AppUtils.AppInfo appInfo : appInfoList) {
                valueCells.add(new AppManagerCell(appInfo));
            }
        }
    }

}
