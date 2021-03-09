package com.open9527.router.callback;


import com.open9527.router.info.Postcard;

/**
 * @author open_9527
 * Create at 2021/3/1
 *
 * 路由跳用回调处理
 **/


public interface NavigationCallback {

    /**
     * 找到跳转页面
     * @param postcard
     */
    void onFound(Postcard postcard);

    /**
     * 未找到
     * @param postcard
     */
    void onLost(Postcard postcard);

    /**
     * 成功跳转
     * @param postcard
     */
    void onArrival(Postcard postcard);


}
