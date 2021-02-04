package com.android.open9527.image.pkg.preview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.net.glide.ImageLoadUtils;
import com.blankj.utilcode.util.LogUtils;
import com.github.chrisbanes.photoview.PhotoView;


/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public class LoadPhotoView extends PhotoView {

    private static final String TAG = "LoadPhotoView";

    private ImageLoadConfig imageLoadConfig;
    private boolean gifImage;

    public LoadPhotoView(@NonNull Context context) {
        this(context, null, 0);
    }

    public LoadPhotoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadPhotoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }
    }

    public void init(View.OnClickListener clickListener, View.OnLongClickListener longClickListener) {
        if (gifImage) {
            loadGif();
        } else {
            loadImage();

        }
        /* scale:1.0--3.0*/
//        setScale(1.0f, false);
        if (clickListener != null) {
            setOnClickListener(clickListener);
        }
        if (longClickListener != null) {
            setOnLongClickListener(longClickListener);
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
