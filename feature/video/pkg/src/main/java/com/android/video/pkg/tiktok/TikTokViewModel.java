package com.android.video.pkg.tiktok;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.video.pkg.R;
import com.android.video.pkg.vo.TikTokVo;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/5/14
 **/
public class TikTokViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("TikTok");
    public final ObservableArrayList<String> valueVideoList = new ObservableArrayList<>();
    public final ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<>();


    protected void requestVideo() {
        List<TikTokVo> tikTokVos = getRawFileList();
        if (CollectionUtils.isNotEmpty(tikTokVos)) {
            for (int i = 0; i < tikTokVos.size(); i++) {
                TikTokVo tikTokVo = tikTokVos.get(i);
//                valueVideoList.add(tikTokVo.getVideoUrl());
                valueCells.add(new TikTokCell(tikTokVo, i));
            }
        }

    }

    private List<TikTokVo> getRawFileList() {
        return GsonUtils.fromJson(ResourceUtils.readRaw2String(R.raw.tiktok_data), new TypeToken<List<TikTokVo>>() {
        }.getType());
    }
}
