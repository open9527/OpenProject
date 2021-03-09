package com.open9527.router.inter;

import java.util.Map;


/**
 * @author open_9527
 * Create at 2021/3/1
 **/
public interface IRouteRoot {
    void loadInto(Map<String, Class<? extends IRouteGroup>> routes);
}
