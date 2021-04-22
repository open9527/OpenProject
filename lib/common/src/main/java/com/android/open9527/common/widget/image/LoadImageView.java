package com.android.open9527.common.widget.image;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.net.glide.ImageLoadUtils;


/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public class LoadImageView extends RoundImageView {

    private static final String TAG = "LoadImageView";

    private ImageLoadConfig imageLoadConfig;
    private boolean gifImage;

    public LoadImageView(@NonNull Context context) {
        this(context, null, 0);
    }

    public LoadImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }
    }

    public void init() {
        if (gifImage) {
            loadGif();
        } else {
            loadImage();

        }
    }

    public void setImageLoadConfig(ImageLoadConfig imageLoadConfig) {
        this.imageLoadConfig = imageLoadConfig;
    }

    public void setGifImage(boolean gifImage) {
        this.gifImage = gifImage;
    }


    private void loadImage() {
        if (imageLoadConfig != null) {
            ImageLoadUtils.loadImage(imageLoadConfig);
        }

    }

    private void loadGif() {
        if (imageLoadConfig != null) {
            ImageLoadUtils.loadGifImage(imageLoadConfig);
        }
    }
}
