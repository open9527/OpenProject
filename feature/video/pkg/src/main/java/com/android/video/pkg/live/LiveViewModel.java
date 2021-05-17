package com.android.video.pkg.live;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/5/17
 **/
public class LiveViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Live");

    public final ObservableField<String> valueVideoTitle = new ObservableField<>("李子柒-斗鱼");
    public final ObservableField<String> valueVideoUrl = new ObservableField<>("http://tx2play1.douyucdn.cn/live/2901080rvhapD8Dd.flv?uuid=2901080");
    public final ObservableField<String> valueVideoCoverUrl = new ObservableField<>("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2119075272,1536468183&fm=26&gp=0.jpg");

    void request() {
        valueVideoTitle.set("竖屏得狐狸-斗鱼");
        valueVideoUrl.set("http://tx2play1.douyucdn.cn/live/3377456rqh2wdlrP.flv?uuid=3377456");
        valueVideoCoverUrl.set("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1549497511,3610054167&fm=11&gp=0.jpg");
    }

}
