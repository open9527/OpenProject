package com.open9527.webview.bridge.impl.vo;

import androidx.annotation.StringDef;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author open_9527
 * Create at 2021/4/22
 **/
public class BridgeControlType {

    /**
     * 按钮。
     * dismiss:不显示；
     * disable：显示不可操作；
     * enable：显示且可操作。
     * light：显示且已收藏，可取消收藏；
     * dark：显示且未收藏，可收藏。
     * light：显示且已点赞，可取消赞；
     * dark：显示且未点赞，可点赞。
     */

    public static final String BTN_CONTROL_TYPE_DISMISS = "dismiss";
    public static final String BTN_CONTROL_TYPE_DISABLE = "disable";
    public static final String BTN_CONTROL_TYPE_ENABLE = "enable";
    public static final String BTN_CONTROL_TYPE_LIGHT = "light";
    public static final String BTN_CONTROL_TYPE_DARK = "dark";


    @StringDef({BTN_CONTROL_TYPE_DISMISS, BTN_CONTROL_TYPE_DISABLE, BTN_CONTROL_TYPE_ENABLE, BTN_CONTROL_TYPE_LIGHT, BTN_CONTROL_TYPE_DARK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BtnControlType {

    }


    public static boolean isShow(@BridgeControlType.BtnControlType String type) {
        if (BTN_CONTROL_TYPE_DISABLE.equals(type) || BTN_CONTROL_TYPE_ENABLE.equals(type)
                || BTN_CONTROL_TYPE_LIGHT.equals(type) || BTN_CONTROL_TYPE_DARK.equals(type)) {
            return true;
        }
        return false;
    }

    public static boolean isEnable(@BridgeControlType.BtnControlType String type) {
        if (BTN_CONTROL_TYPE_ENABLE.equals(type)
                || BTN_CONTROL_TYPE_LIGHT.equals(type) || BTN_CONTROL_TYPE_DARK.equals(type)) {
            return true;
        }
        return false;
    }

    public static boolean isCollect(@BridgeControlType.BtnControlType String type) {
        return BTN_CONTROL_TYPE_LIGHT.equals(type);
    }

    public static boolean isLike(@BridgeControlType.BtnControlType String type) {
        return BTN_CONTROL_TYPE_LIGHT.equals(type);
    }
}
