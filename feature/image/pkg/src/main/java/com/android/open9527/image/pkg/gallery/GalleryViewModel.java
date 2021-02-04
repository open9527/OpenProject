package com.android.open9527.image.pkg.gallery;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.image.pkg.load.ImageCell;
import com.android.open9527.image.pkg.load.ImageVo;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.blankj.utilcode.util.CollectionUtils;

import java.io.File;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/27
 **/
public class GalleryViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Gallery");
    public final ObservableField<Drawable> valueTitleBarBg = new ObservableField<>(new ColorDrawable(Color.WHITE));

    public final ObservableField<String> valueLeft = new ObservableField<>("返回");
    public final ObservableField<String> valueRight = new ObservableField<>("菜单");

    public final ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<>();


    protected void getImageUrl(List<String> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (String imageUrl : list) {
                valueCells.add(new ImageCell(Uri.fromFile(new File(imageUrl))));
            }
        }


    }


}
