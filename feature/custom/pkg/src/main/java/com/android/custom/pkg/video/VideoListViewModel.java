package com.android.custom.pkg.video;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.open9527.recycleview.adapter.BaseBindingCell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/5/11
 **/
public class VideoListViewModel extends ViewModel {
    public final ObservableField<String> valueTitle = new ObservableField<>("VideoPlayer");

    public final ObservableBoolean valueNoMoreData = new ObservableBoolean(false);

    public final ObservableArrayList<BaseBindingCell> valueCellList = new ObservableArrayList<>();


    public final ObservableField<String> valueVideoTitle = new ObservableField<>("7日，韶关市公安局浈江分局发布关于“特斯拉追尾货车”的情况通报");
    public final ObservableField<String> valueVideoUrl = new ObservableField<>("https://shanwei.storage.gdmedia.tech/55959ac1bb90428186a8e8ada9781d17.mp4");
    public final ObservableField<String> valueVideoCoverUrl = new ObservableField<>("https://shanwei.storage.gdmedia.tech/55959ac1bb90428186a8e8ada9781d17.mp4?vframe/png/offset/1");

    //"contentType": "video",
    //    			"sourceUrl": "https://shanwei.storage.gdmedia.tech/55959ac1bb90428186a8e8ada9781d17.mp4",
    //    			"thumbUrl": "https://shanwei.storage.gdmedia.tech/55959ac1bb90428186a8e8ada9781d17.mp4?vframe/png/offset/1"

    void getCells() {
        for (int i = 0; i < 10; i++) {
            valueCellList.add(new VideoListCell(valueVideoUrl.get(), valueVideoTitle.get(), valueVideoCoverUrl.get()));
        }
    }
}
