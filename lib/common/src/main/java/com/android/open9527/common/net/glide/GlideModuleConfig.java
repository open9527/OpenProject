package com.android.open9527.common.net.glide;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.open9527.common.R;
import com.android.open9527.common.net.okhttp.OkHttpClientUtils;
import com.android.open9527.glide.GlideHeadInterceptor;
import com.android.open9527.glide.OkHttpLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * @author open_9527
 * Create at 2020/12/31
 * <p>
 * <p>
 * TODO:
 * 1. 实际项目中最好配置在common中
 * 2.统一配置okHttpClient
 * 3.统一配置 加载失败,错误,预览图
 **/
@GlideModule
public class GlideModuleConfig extends AppGlideModule {

    /**
     * 本地图片缓存文件最大值
     */
    private static final int IMAGE_DISK_CACHE_MAX_SIZE = 300 * 1024 * 1024;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        // 读写外部缓存目录不需要申请存储权限
        File diskCacheFile = new File(context.getCacheDir(), "image_helper_glide");
        // 如果这个路径是一个文件
        if (diskCacheFile.exists() && diskCacheFile.isFile()) {
            // 执行删除操作
            diskCacheFile.delete();
        }
        // 如果这个路径不存在
        if (!diskCacheFile.exists()) {
            // 创建多级目录
            diskCacheFile.mkdirs();
        }
        builder.setDiskCache(() -> DiskLruCacheWrapper.create(diskCacheFile, IMAGE_DISK_CACHE_MAX_SIZE));

        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

        // 设置默认的加载占位图和加载出错图
        builder.setDefaultRequestOptions(new RequestOptions()
//                .fallback()
                .placeholder(R.drawable.common_image_loading)
                .error(R.drawable.common_image_load_err));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        // Glide 默认采用的是 HttpURLConnection 来做网络请求，这里切换成更高效的 OkHttp
        //可以配置全局请求头,同时可以和网络请求统一获取OkHttpClient,
        //TODO:这里是自己创建得
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                //配置全局请求头
//                .addInterceptor(new GlideHeadInterceptor())
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .writeTimeout(30, TimeUnit.SECONDS)
//                .build();
        OkHttpClient okHttpClient = OkHttpClientUtils.getClient().newBuilder()
                .addInterceptor(new GlideHeadInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpLoader.Factory(okHttpClient));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}