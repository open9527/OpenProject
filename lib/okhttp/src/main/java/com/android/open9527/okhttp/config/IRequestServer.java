package com.android.open9527.okhttp.config;

import com.android.open9527.okhttp.model.BodyType;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public interface IRequestServer extends IRequestHost, IRequestPath, IRequestType {

    @Override
    default BodyType getType() {
        // 默认以表单的方式提交
        return BodyType.FORM;
    }

    @Override
    default String getPath() {
        // 服务器路径可填可不填
        return "";
    }
}