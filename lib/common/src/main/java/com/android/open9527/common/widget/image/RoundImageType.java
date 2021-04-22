package com.android.open9527.common.widget.image;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author open_9527
 * Create at 2021/4/22
 **/
public class RoundImageType {

    public static final String ROUND_TYPE_NORMAL = "ROUND_TYPE_NORMAL";
    public static final String ROUND_TYPE_CIRCLE = "ROUND_TYPE_CIRCLE";
    public static final String ROUND_TYPE_ROUND = "ROUND_TYPE_ROUND";

    public static final String CORNER_TYPE_ALL = "CORNER_TYPE_ALL";
    public static final String CORNER_TYPE_TOP = "CORNER_TYPE_TOP";
    public static final String CORNER_TYPE_TOP_LEFT = "CORNER_TYPE_TOP_LEFT";
    public static final String CORNER_TYPE_TOP_RIGHT = "CORNER_TYPE_TOP_RIGHT";
    public static final String CORNER_TYPE_BOTTOM = "CORNER_TYPE_BOTTOM";
    public static final String CORNER_TYPE_BOTTOM_LEFT = "CORNER_TYPE_BOTTOM_LEFT";
    public static final String CORNER_TYPE_BOTTOM_RIGHT = "CORNER_TYPE_BOTTOM_RIGHT";
    public static final String CORNER_TYPE_LEFT = "CORNER_TYPE_LEFT";
    public static final String CORNER_TYPE_LEFT_TOP = "CORNER_TYPE_LEFT_TOP";
    public static final String CORNER_TYPE_LEFT_BOTTOM = "CORNER_TYPE_LEFT_BOTTOM";
    public static final String CORNER_TYPE_RIGHT = "CORNER_TYPE_RIGHT";
    public static final String CORNER_TYPE_RIGHT_TOP = "CORNER_TYPE_RIGHT_TOP";
    public static final String CORNER_TYPE_RIGHT_BOTTOM = "CORNER_TYPE_RIGHT_BOTTOM";


    @StringDef({ROUND_TYPE_NORMAL, ROUND_TYPE_CIRCLE, ROUND_TYPE_ROUND})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RoundType {

    }


    @StringDef({CORNER_TYPE_ALL,
            CORNER_TYPE_LEFT, CORNER_TYPE_LEFT_TOP, CORNER_TYPE_LEFT_BOTTOM,
            CORNER_TYPE_TOP, CORNER_TYPE_TOP_LEFT, CORNER_TYPE_TOP_RIGHT,
            CORNER_TYPE_RIGHT, CORNER_TYPE_RIGHT_TOP, CORNER_TYPE_RIGHT_BOTTOM,
            CORNER_TYPE_BOTTOM, CORNER_TYPE_BOTTOM_LEFT, CORNER_TYPE_BOTTOM_RIGHT
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface CornerType {

    }
}
