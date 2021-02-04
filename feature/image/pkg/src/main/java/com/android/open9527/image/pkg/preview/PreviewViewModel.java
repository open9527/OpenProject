package com.android.open9527.image.pkg.preview;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.PagerAdapter;

import com.android.open9527.recycleview.adapter.BaseBindingCell;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/25
 **/
public class PreviewViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Preview");

    public final ObservableField<PagerAdapter> valuePageAdapter = new ObservableField<>();
    public final ObservableInt valuePageIndex = new ObservableInt(0);

    public final ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<>();

    public final ObservableField<PreviewCell.ICellClick> valueICellClick = new ObservableField<>();

    public final ObservableField<String> valueTransitionName = new ObservableField<>("TransitionName");


     void initData(List list) {
//        valueCells.add(new PreviewCell("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2593572861,3728381392&fm=26&gp=0.jpg", valueICellClick.get()));

        for (Object item : list) {
            valueCells.add(new PreviewCell(String.valueOf(item), valueICellClick.get()));
        }
    }

}
