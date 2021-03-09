package com.open9527.router.utils;

import com.open9527.annotation.router.RouteMeta;
import com.open9527.router.inter.IRouteGroup;
import com.open9527.router.inter.IService;

import java.util.HashMap;
import java.util.Map;


/**
 * @author open_9527
 * Create at 2021/3/1
 **/
public class Warehouse {

    /**
     * root 映射表 保存分组信息
     */
    public static Map<String, Class<? extends IRouteGroup>> groupsIndex = new HashMap<>();

    /**
     * group 映射表 保存组中的所有数据
     */
    public static Map<String, RouteMeta> routes = new HashMap<>();

    /**
     * group 映射表 保存组中的所有数据
     */
    public static Map<Class, IService> services = new HashMap<>();
}
