package com.android.open9527.okhttp.config;

import com.android.open9527.okhttp.model.BodyType;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public interface IRequestType {

    /**
     * 参数提交类型
     */
    BodyType getType();
}