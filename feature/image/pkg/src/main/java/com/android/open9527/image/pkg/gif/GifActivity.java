package com.android.open9527.image.pkg.gif;

import android.view.View;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.image.pkg.BR;
import com.android.open9527.image.pkg.R;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.ActivityUtils;

/**
 * @author open_9527
 * Create at 2021/5/25
 **/
public class GifActivity extends BaseCommonActivity {

    private GifViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(GifViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.gif_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }


    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };
        public View.OnClickListener customGifClick = v -> {
            ActivityUtils.startActivity(CustomGifActivity.class);

        };
        public View.OnClickListener gifListClick = v -> {
            ActivityUtils.startActivity(GifListActivity.class);
        };
    }


}
