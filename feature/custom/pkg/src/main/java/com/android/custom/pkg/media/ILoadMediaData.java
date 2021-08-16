package com.android.custom.pkg.media;

/**
 * @author open_9527
 * Create at 2021/1/27
 **/
public interface ILoadMediaData<T> {

    /**
     * 配置接口回调数据
     * @param t
     */

    default void loadComplete(T t) {
    }
}
