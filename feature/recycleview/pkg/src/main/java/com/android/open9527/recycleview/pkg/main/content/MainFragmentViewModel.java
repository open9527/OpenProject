package com.android.open9527.recycleview.pkg.main.content;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.pkg.main.content.cell.ContentImageCell;
import com.android.open9527.recycleview.pkg.main.content.cell.ContentTextCell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/11
 **/
public class MainFragmentViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("title");
    public final ObservableField<String> valueContent = new ObservableField<>("content");


    public final ObservableBoolean valueNoMoreData = new ObservableBoolean(false);
    public final ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<>();


    public void createCells(int page) {
        if (page == 1) {
            valueCells.clear();
        }
        valueNoMoreData.set(page == 3);
        List<BaseBindingCell> cellList = new ArrayList<BaseBindingCell>() {{

            add(new ContentTextCell());
            add(new ContentImageCell());
            add(new ContentTextCell());
            add(new ContentImageCell());
            add(new ContentTextCell());
            add(new ContentImageCell());
            add(new ContentTextCell());
            add(new ContentImageCell());
            add(new ContentTextCell());
            add(new ContentImageCell());


//            add(new CommonEmptyCell());
//            add(new CommonLineCell());
//            if (valueNoMoreData.get()) {
//                add(new CommonNoMoreDataCell());
//            }
        }};
        valueCells.addAll(cellList);
    }
}
