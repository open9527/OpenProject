package com.android.open9527.common.widget.image;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;


/**
 * @author open_9527
 * Create at 2021/1/4
 **/
public class StateSwitchImageView extends AppCompatImageView {
    private static final String TAG = "StateSwitchImageView";

    private StateSwitchImageConfig mStateSwitchImageConfig;

    public StateSwitchImageView(@NonNull Context context) {
        this(context, null, 0);
    }

    public StateSwitchImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateSwitchImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }
    }

    public void init() {
//        LogUtils.i(TAG, "StateSwitchImageConfig:" + mStateSwitchImageConfig.toString());
        setImageResId();
        setImageDrawable();
    }


    public void setStateSwitchImageConfig(StateSwitchImageConfig mStateSwitchImageConfig) {
        this.mStateSwitchImageConfig = mStateSwitchImageConfig;
    }

    private void setImageResId() {
        if (mStateSwitchImageConfig.getDefaultImageResId() > 0 || mStateSwitchImageConfig.getSelectedImageResId() > 0) {
            setImageResource(mStateSwitchImageConfig.getSelect() ? mStateSwitchImageConfig.getSelectedImageResId() : mStateSwitchImageConfig.getDefaultImageResId());

        }
//        setImageDrawable(ContextCompat.getDrawable(getContext(), mStateSwitchImageConfig.getSelect() ? mStateSwitchImageConfig.getSelectedImageResId() : mStateSwitchImageConfig.getDefaultImageResId()));
    }

    private void setImageDrawable() {
        if (mStateSwitchImageConfig.getDefaultImageDrawable() != null || mStateSwitchImageConfig.getSelectedImageDrawable() != null) {
            setImageDrawable(mStateSwitchImageConfig.getSelect() ? mStateSwitchImageConfig.getSelectedImageDrawable() : mStateSwitchImageConfig.getDefaultImageDrawable());

        }

    }

//    private void setImageUrl() {
//        if (TextUtils.isEmpty(selectedImageUrl) || TextUtils.isEmpty(defaultImageUrl)) {
//            return;
//        }
//        //TODO:
//    }


}
