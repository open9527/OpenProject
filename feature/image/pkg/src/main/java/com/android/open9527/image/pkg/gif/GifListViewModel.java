package com.android.open9527.image.pkg.gif;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.open9527.image.pkg.R;
import com.android.open9527.image.pkg.load.ImageCell;
import com.android.open9527.image.pkg.load.ImageVo;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/5/25
 **/
public class GifListViewModel extends ViewModel {
    public final ObservableField<String> valueTitle = new ObservableField<>("GifList");

    public final ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<>();


    protected void getImageUrl() {
        List<ImageVo> imageVos = getRawFileList();
        if (CollectionUtils.isNotEmpty(imageVos)) {
            for (int i = 0; i < imageVos.size(); i++) {
                ImageVo imageVo = imageVos.get(i);
                valueCells.add(new GifListCell(imageVo, i));
            }
        }

    }


    private List<ImageVo> getRawFileList() {
        return GsonUtils.fromJson(ResourceUtils.readRaw2String(R.raw.gif), new TypeToken<List<ImageVo>>() {
        }.getType());
    }
}
