package com.android.open9527.image.pkg.album;

/**
 * @author open_9527
 * Create at 2021/1/27
 **/
public interface ILoadData<T> {

    /**
     * 配置接口回调数据
     * @param t
     */

    default void loadComplete(T t) {
    }
}
