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
        valueVideoTitle.set("老烟斗鬼故事-斗鱼");
        valueVideoUrl.set("http://tx2play1.douyucdn.cn/live/2337939rrPtUsSFY.flv?uuid=2337939");
        valueVideoCoverUrl.set("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1549497511,3610054167&fm=11&gp=0.jpg");
    }

    void request0() {
        valueVideoTitle.set("奶一口阿玉-斗鱼");
        valueVideoUrl.set("http://tx2play1.douyucdn.cn/live/8267019r6kXBHeVH.flv?uuid=8267019");
        valueVideoCoverUrl.set("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1549497511,3610054167&fm=11&gp=0.jpg");
    }

    void request1() {
        valueVideoTitle.set("竖屏得狐狸-斗鱼");
        valueVideoUrl.set("http://tx2play1.douyucdn.cn/live/3377456rs3WH24Kw.flv?uuid=3377456");
        valueVideoCoverUrl.set("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1549497511,3610054167&fm=11&gp=0.jpg");
    }

    void request2() {
        valueVideoTitle.set("老罗直播间-抖音");
        valueVideoUrl.set("http://pull-hls-f6.douyincdn.com/gamereplay/stream-108817068878987732_uhd/index.m3u8");
        valueVideoCoverUrl.set("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.08160.cn%2Fuploads%2Fallimg%2F170821%2F24-1FR1144910-51.jpg&refer=http%3A%2F%2Fwww.08160.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624182211&t=9c70d1f80840bd4ad6719597cd24c378");
    }

    void request3() {
        valueVideoTitle.set("牛叔说电影-斗鱼");
        valueVideoUrl.set("http://tx2play1.douyucdn.cn/live/2758565ryhpsskBn.flv?uuid=2758565");
        valueVideoCoverUrl.set("https://gimg2.baidu.com/image_search/src=http%3A%2F%2F001.img.pu.sohu.com.cn%2Fgroup3%2FM05%2FB5%2FDD%2FMTAuMTAuODguODM%3D%2FMTAwMTE0XzE1MTY5NjEyMTIzNTg%3D%2Fcut%40m%3Dcrop%2Cx%3D0%2Cy%3D0%2Cw%3D300%2Ch%3D300.jpg&refer=http%3A%2F%2F001.img.pu.sohu.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624182295&t=ce2a965c698210ad97a0ff9c2e0b8c8f");
    }

    void request4() {
        valueVideoTitle.set("4K高清修复电影 周星驰成龙周润发-B站");
        valueVideoUrl.set("https://d1--cn-gotcha04.bilivideo.com/live-bvc/517985/live_688567880_44907009.flv?cdn=cn-gotcha04&expires=1621594357&len=0&oi=975280346&pt=web&qn=10000&trid=1000fc209e12dcef4a299a8b46822eacac06&sigparams=cdn,expires,len,oi,pt,qn,trid&sign=ffc8fe7cdfb095a3ee04749e111f5de8&ptype=0&src=8&sl=1&sk=87af7c4a512d23a&order=4");
        valueVideoCoverUrl.set("https://gimg2.baidu.com/image_search/src=http%3A%2F%2F001.img.pu.sohu.com.cn%2Fgroup3%2FM05%2FB5%2FDD%2FMTAuMTAuODguODM%3D%2FMTAwMTE0XzE1MTY5NjEyMTIzNTg%3D%2Fcut%40m%3Dcrop%2Cx%3D0%2Cy%3D0%2Cw%3D300%2Ch%3D300.jpg&refer=http%3A%2F%2F001.img.pu.sohu.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624182295&t=ce2a965c698210ad97a0ff9c2e0b8c8f");
    }

}
