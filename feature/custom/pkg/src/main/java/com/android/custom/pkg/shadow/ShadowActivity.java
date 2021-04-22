package com.android.custom.pkg.shadow;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.filter.NightColorFilter;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ShadowUtils;
import com.blankj.utilcode.util.SizeUtils;

/**
 * @author open_9527
 * Create at 2021/4/21
 **/
public class ShadowActivity extends BaseCommonActivity implements NightColorFilter {

    private ShadowViewModel mViewModel;


    @Override
    public boolean excludeView(@NonNull View view) {
        return view instanceof ImageView;
    }

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(ShadowViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.shadow_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        ImageView imageView = findViewById(R.id.iv_shadow);
        ShadowUtils.apply(imageView,
                new ShadowUtils.Config()
                        .setShadowSize(SizeUtils.dp2px(10), SizeUtils.dp2px(10))
                        .setShadowMaxSize(SizeUtils.dp2px(10), SizeUtils.dp2px(10))
//                        .setShadowRadius(SizeUtils.dp2px(10))
//                        .setShadowColor(Color.WHITE)
                        .setShadowColor(ColorUtils.setAlphaComponent(Color.WHITE, 0.5f))
//                        .setCircle()
        );
    }


    public class ClickProxy {

        public View.OnClickListener backClick = v -> {
            finish();
        };
    }
}
