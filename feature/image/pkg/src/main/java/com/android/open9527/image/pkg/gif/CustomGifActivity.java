package com.android.open9527.image.pkg.gif;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.image.pkg.BR;
import com.android.open9527.image.pkg.R;
import com.android.open9527.image.pkg.databinding.CustomGifActivityBinding;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.ThreadUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author open_9527
 * Create at 2021/5/26
 **/
public class CustomGifActivity extends BaseCommonActivity {

    private CustomGifViewModel mViewModel;
    private CustomGifActivityBinding mBinding;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(CustomGifViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.custom_gif_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }
    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        mBinding = (CustomGifActivityBinding) getBinding();
        mBinding.ivGif.setGifResource(R.drawable.gif1);
        mBinding.ivGif.play(-1);
//        mBinding.ivGif1.setGifResource(R.drawable.gif2);
//        mBinding.ivGif1.play(-1);

    }

    @Override
    public void initRequest() {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<InputStream>() {
            @Override
            public InputStream doInBackground() throws Throwable {
                InputStream inputStream = getImageStream(" https://fs.res-storage.shmedia.tech/1389970.gif");
//                InputStream inputStream = getImageStream("https://fs.res-storage.shmedia.tech/1403777.jpg");
                return inputStream;
            }

            @Override
            public void onSuccess(InputStream result) {
                mViewModel.valueInputStream.setValue(result);
            }
        });
    }

    @Override
    public void initEvent() {
        mViewModel.valueInputStream.observe(this, inputStream -> {
            mBinding.ivGif1.setGifInputStream(inputStream);
        });
    }

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };
        public View.OnClickListener playClick = v -> {

        };
    }


    /**
     * 获取网络图片流
     *
     * @param url :
     * @return :
     */
    public InputStream getImageStream(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();

                return inputStream;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
