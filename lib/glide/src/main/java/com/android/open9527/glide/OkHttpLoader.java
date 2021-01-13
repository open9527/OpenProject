package com.android.open9527.glide;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

import okhttp3.Call;

/**
 * @author open_9527
 * Create at 2020/12/31
 **/
public class OkHttpLoader implements ModelLoader<GlideUrl, InputStream> {

    private final Call.Factory mFactory;

    private OkHttpLoader(@NonNull Call.Factory factory) {
        mFactory = factory;
    }

    @Override
    public boolean handles(@NonNull GlideUrl url) {
        return true;
    }

    @Override
    public LoadData<InputStream> buildLoadData(@NonNull GlideUrl model, int width, int height,
                                               @NonNull Options options) {
        return new LoadData<>(model, new OkHttpFetcher(mFactory, model));
    }

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {

        private final Call.Factory mFactory;

        public Factory(@NonNull Call.Factory factory) {
            mFactory = factory;
        }

        @NonNull
        @Override
        public ModelLoader<GlideUrl, InputStream> build(@NonNull MultiModelLoaderFactory multiFactory) {
            return new OkHttpLoader(mFactory);
        }

        @Override
        public void teardown() {}
    }
}
