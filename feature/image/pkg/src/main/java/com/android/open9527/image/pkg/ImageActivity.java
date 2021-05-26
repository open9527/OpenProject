package com.android.open9527.image.pkg;

import android.view.View;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.image.pkg.gif.GifActivity;
import com.android.open9527.image.pkg.load.ImageLoadActivity;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.ActivityUtils;
import com.open9527.annotation.router.Router;

/**
 * @author open_9527
 * Create at 2021/5/25
 **/
@Router(path = "/image/ImageActivity")
public class ImageActivity extends BaseCommonActivity {

    private ImageViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(ImageViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.image_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }


    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };
        public View.OnClickListener imageClick = v -> {
            ActivityUtils.startActivity(ImageLoadActivity.class);

        };
        public View.OnClickListener gifClick = v -> {
            ActivityUtils.startActivity(GifActivity.class);
        };
    }


}
