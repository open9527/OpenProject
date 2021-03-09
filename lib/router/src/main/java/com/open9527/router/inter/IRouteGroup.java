package com.open9527.router.inter;

import com.open9527.annotation.router.RouteMeta;

import java.util.Map;


/**
 * @author open_9527
 * Create at 2021/3/1
 **/
public interface IRouteGroup {
    void loadInto(Map<String, RouteMeta> atlas);
}
