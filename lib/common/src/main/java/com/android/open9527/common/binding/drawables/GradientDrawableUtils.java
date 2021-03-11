package com.android.open9527.common.binding.drawables;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;

/**
 * @author open_9527
 * Create at 2021/1/11
 **/
public class GradientDrawableUtils {

    public static GradientDrawable createDrawableLine(int color, int width) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.LINE);
        gradientDrawable.setStroke(width, color);
        return gradientDrawable;
    }


    public static GradientDrawable createDrawableDashLine(int color, int width, int dashWidth, int dashGap) {
        //要显示虚线一定要关闭硬件加速
//        view.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.LINE);
        //第一个参数为线的宽度  第二个参数是线的颜色 第三个参数是虚线段的长度 第四个参数是虚线段之间的间距长度
        gradientDrawable.setStroke(width, color, dashWidth, dashGap);
        return gradientDrawable;
    }


    public static GradientDrawable createDrawableOval(int color, int width) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(color);
        gradientDrawable.setSize(width, width);
        return gradientDrawable;
    }

    public static GradientDrawable createDrawableOval(int color, int width, int strokeColor, int strokeWidth) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        gradientDrawable.setSize(width, width);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    public static GradientDrawable createDrawableRectangle(int color, int width, int height, int radius, int strokeColor, int strokeWidth) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
//        gradientDrawable.setStroke(strokeWidth, strokeColor);
        gradientDrawable.setCornerRadius(radius);
        gradientDrawable.setSize(width, height);
        //TODO: View的背景会导致有圆角外有黑背景因为设置colors顺序的问题
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }


    public static GradientDrawable createDrawableRectangle(int[] colors, int gradient, GradientDrawable.Orientation orientation, int width, int height) {
        //int[] colors = {Color.YELLOW, Color.GREEN, Color.BLUE};
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);


        //设置线性渐变
        //gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        //设置渐变方向
        //gradientDrawable.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);

        //设置半径渐变
        //gradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        //渐变的半径值
        //gradientDrawable.setGradientRadius(50);

        //设置扫描渐变 ,
        // gradientDrawable.setGradientType(GradientDrawable.SWEEP_GRADIENT);
        //渐变中心点
        // gradientDrawable.setGradientCenter(0.5f,0.5f);

        gradientDrawable.setGradientType(gradient);
        gradientDrawable.setOrientation(orientation);
        gradientDrawable.setSize(width, height);
        //添加颜色组
        gradientDrawable.setColors(colors);
        return gradientDrawable;
    }


    @SuppressLint("WrongConstant")
    public static GradientDrawable createDrawableLinearGradient(int color, int width, int dashWidth, int dashGap) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.LINEAR_GRADIENT);
        //第一个参数为线的宽度  第二个参数是线的颜色 第三个参数是虚线段的长度 第四个参数是虚线段之间的间距长度
        gradientDrawable.setStroke(width, color, dashWidth, dashGap);
        return gradientDrawable;
    }


}
