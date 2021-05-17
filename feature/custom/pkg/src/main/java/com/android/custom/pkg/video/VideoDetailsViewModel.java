package com.android.custom.pkg.video;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/5/12
 **/
public class VideoDetailsViewModel extends ViewModel {
    public final ObservableField<String> valueTitle = new ObservableField<>("VideoDetails");

    public final ObservableField<String> valueVideoTitle = new ObservableField<>("陈翔六点半-斗鱼");
    public final ObservableField<String> valueVideoUrl = new ObservableField<>("http://tx2play1.douyucdn.cn/live/2132902rMfyxirEj.flv?uuid=2132902");
    public final ObservableField<String> valueVideoCoverUrl = new ObservableField<>("https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1222355009,1580767892&fm=26&gp=0.jpg");



}
