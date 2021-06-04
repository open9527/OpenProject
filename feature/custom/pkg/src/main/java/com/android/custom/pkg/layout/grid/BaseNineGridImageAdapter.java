package com.android.custom.pkg.layout.grid;

import android.content.Context;
import android.widget.ImageView;

import com.android.open9527.common.widget.image.LoadImageView;

/**
 * @author open_9527
 * Create at 2021/6/3
 **/
public abstract class BaseNineGridImageAdapter<E> extends BaseNineGridAdapter<LoadImageView, E> {

    @Override
    public LoadImageView onCreateChildView(Context context, NineGridLayout<LoadImageView, E> veNineGridLayout, int position) {
        LoadImageView imageView = new LoadImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}
