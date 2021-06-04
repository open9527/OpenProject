package com.android.custom.pkg.layout.grid;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.recycleview.adapter.BaseBindingCell;


/**
 * @author open_9527
 * Create at 2021/6/3
 **/
public class GridLayoutViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("GridLayout");

    public final ObservableArrayList<BaseBindingCell> valueCellList = new ObservableArrayList<>();

    void requestData() {
        for (int i = 0; i < 20; i++) {
            valueCellList.add(new GridLayoutCell("https://fs.res-storage.shmedia.tech/1403777.jpg", i));
        }

    }

}