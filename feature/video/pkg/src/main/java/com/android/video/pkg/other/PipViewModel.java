package com.android.video.pkg.other;


import android.graphics.Bitmap;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/5/17
 **/
public class PipViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Live");
    public final ObservableField<String> valueSpeedTitle = new ObservableField<>("1.0f");
    public final ObservableField<String> valueScreenScaleType = new ObservableField<>("原始大小");
    public final ObservableField<Bitmap> valueShotBitmap = new ObservableField<>();

    public final ObservableField<String> valueVideoTitle = new ObservableField<>("#三十而立纪录片1当年的学生会主席如今怎样？第一批30岁的...");
    public final ObservableField<String> valueVideoUrl = new ObservableField<>("https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f830000bnhjh6hevctn5kvs3c90&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme");
    public final ObservableField<String> valueVideoCoverUrl = new ObservableField<>("http://p9-dy.byteimg.com/large/tos-cn-p-0015/680d4dc0c3c1453d8b62791c3483b43b_1575172317.jpeg?from=2563711402_large");


}
