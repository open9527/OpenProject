package com.android.custom.pkg.lottie;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.databinding.LottieActivityBinding;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;

/**
 * @author open_9527
 * Create at 2021/5/27
 **/
public class LottieActivity extends BaseCommonActivity {

    private LottieViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(LottieViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.lottie_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        LottieActivityBinding  mBinding=  (LottieActivityBinding)getBinding();
//        mBinding.ivLottie.setAnimation(R.raw.bullseye);
//        mBinding.ivLottie.playAnimation();
//        mBinding.ivLottie.setRepeatCount(-1);

//        mBinding.ivLottie.setAnimation("AndroidWave.json");
//        mBinding.ivLottie.setAnimation("HamburgerArrow.json");
//        mBinding.ivLottie.setAnimation("Lottie Logo 1.json");
//        mBinding.ivLottie.setAnimation("Logo/LogoSmall.json");
        mBinding.ivLottie.setAnimation("Mobilo/A.json");

    }

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };
    }
}
