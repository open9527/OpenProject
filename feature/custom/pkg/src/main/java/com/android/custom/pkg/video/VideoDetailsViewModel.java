package com.android.custom.pkg.video;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/5/12
 **/
public class VideoDetailsViewModel extends ViewModel {
    public final ObservableField<String> valueTitle = new ObservableField<>("VideoDetails");

    public final ObservableField<String> valueVideoTitle = new ObservableField<>("7日，韶关市公安局浈江分局发布关于“特斯拉追尾货车”的情况通报");
    public final ObservableField<String> valueVideoUrl = new ObservableField<>("https://shanwei.storage.gdmedia.tech/55959ac1bb90428186a8e8ada9781d17.mp4");
    public final ObservableField<String> valueVideoCoverUrl = new ObservableField<>("https://shanwei.storage.gdmedia.tech/55959ac1bb90428186a8e8ada9781d17.mp4?vframe/png/offset/1");

}
