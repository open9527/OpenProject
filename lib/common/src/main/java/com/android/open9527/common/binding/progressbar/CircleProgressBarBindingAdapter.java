package com.android.open9527.common.binding.progressbar;


import android.annotation.SuppressLint;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.android.open9527.common.widget.textview.CircleProgressbar;


public class CircleProgressBarBindingAdapter {

    @SuppressLint("SetTextI18n")
    @BindingAdapter(value = {
            "bindCircleProgressBarSolidColor",
            "bindCircleProgressBarStrokeColor",
            "bindCircleProgressBarStrokeWidth",
            "bindCircleProgressBarProgressStrokeWidth",
            "bindCircleProgressBarProgress",
            "bindCircleProgressBarProgressStrokeColor",
            "bindCircleProgressBarProgressText",
    },
            requireAll = false)
    public static void setBindingCircleProgressBarAdapter(@NonNull CircleProgressbar circleProgressbar,
                                                          @ColorInt Integer solidColor,
                                                          @ColorInt Integer strokeColor,
                                                          int strokeWidth,
                                                          int progressStrokeWidth,
                                                          int progress,
                                                          @ColorInt Integer progressStrokeColor,
                                                          String text
    ) {
        //设置圆形的填充颜色
        circleProgressbar.setInCircleColor(solidColor);
        //设置外部轮廓的颜色
        circleProgressbar.setOutLineColor(strokeColor);
        //设置外部轮廓的颜色
        circleProgressbar.setOutLineWidth(strokeWidth);
        //设置进度条线的宽度
        circleProgressbar.setProgressLineWidth(progressStrokeWidth);
        //设置进度
        circleProgressbar.setProgress(progress);
        //设置倒计时总时间
//        binding.circleProgressbar.setTimeMillis(3000);
        //设置进度条颜色
        circleProgressbar.setProgressColor(progressStrokeColor);
        //开始
//        binding.circleProgressbar.start();
        //暂停
//        binding.circleProgressbar.stop();
        //重新开始
//        binding.circleProgressbar.reStart();
        //
        circleProgressbar.setText(progress + "%");

        //设置进度监听
//        binding.circleProgressbar.setCountdownProgressListener(1, (what, progress) -> {
//            binding.circleProgressbar.setText(progress + "%");
////
//        });
    }

}
