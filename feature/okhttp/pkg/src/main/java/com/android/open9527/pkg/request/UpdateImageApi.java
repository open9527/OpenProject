package com.android.open9527.pkg.request;

import com.android.open9527.okhttp.annotation.HttpRename;
import com.android.open9527.okhttp.config.IRequestApi;
import com.android.open9527.okhttp.config.IRequestServer;

import java.io.File;

/**
 * @author open_9527
 * Create at 2021/1/8
 **/
public class UpdateImageApi implements IRequestServer , IRequestApi {
    @Override
    public String getHost() {

        return "https://graph.baidu.com/";
    }

    @Override
    public String getApi() {
        return "upload/";
    }

    /** 本地图片 */
    private File image;

    public UpdateImageApi(File image) {
        this.image = image;
    }

    public UpdateImageApi setImage(File image) {
        this.image = image;
        return this;
    }


}
