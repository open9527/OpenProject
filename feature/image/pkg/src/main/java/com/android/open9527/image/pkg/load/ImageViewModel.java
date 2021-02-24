package com.android.open9527.image.pkg.load;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.image.pkg.R;
import com.android.open9527.image.pkg.preview.PreviewCell;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/25
 **/
public class ImageViewModel extends ViewModel {


    public final ObservableField<String> valueTitle = new ObservableField<>("Image");
    public final ObservableField<Drawable> valueTitleBarBg = new ObservableField<>(new ColorDrawable(Color.WHITE));

    public final ObservableField<String> valueLeft = new ObservableField<>("返回");
    public final ObservableField<String> valueRight = new ObservableField<>("查看本地相册");

    public final ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<>();
    public final ObservableField<ImageCell.ICellClick> valueICellClick = new ObservableField<>();

    public final ObservableArrayList<String> valueImageUrls = new ObservableArrayList<>();


    protected void getImageUrl() {
        List<ImageVo> imageVos = getRawFileList();
        if (CollectionUtils.isNotEmpty(imageVos)) {
//            valueImageUrls.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2593572861,3728381392&fm=26&gp=0.jpg");
            for (ImageVo imageVo : imageVos) {
                valueImageUrls.add(imageVo.getImageUrl());
                ImageCell imageCell = new ImageCell(imageVo, valueICellClick.get());
                valueCells.add(imageCell);
            }
        }

    }


    private List<ImageVo> getRawFileList() {
        return GsonUtils.fromJson(ResourceUtils.readRaw2String(R.raw.image), new TypeToken<List<ImageVo>>() {
        }.getType());
    }


}
