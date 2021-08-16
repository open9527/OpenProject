package com.android.custom.pkg.media;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.custom.pkg.media.cell.MediaCell;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.blankj.utilcode.util.CollectionUtils;

import java.util.List;


/**
 * @author open_9527
 * Create at 2021/7/8
 **/
public class MediaViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Media");

    public final ObservableArrayList<BaseBindingCell> valueCellList = new ObservableArrayList<>();


    protected void getCells(List<MediaData> mediaDataList) {
        if (CollectionUtils.isNotEmpty(mediaDataList)) {
            for (MediaData mediaData : mediaDataList) {
                valueCellList.add(new MediaCell(mediaData.getUri(), mediaData.toString()));
            }
        }

    }
}