package com.android.custom.pkg.webview.bridge;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;

/**
 * @author open_9527
 * Create at 2021/4/30
 **/
public class ViewPage2WebActivity extends BaseCommonActivity {

    private BridgeViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(BridgeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.view_page2_web_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                ;
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        initFragment();
    }

    private void initFragment() {
        ViewPager2 viewPager2 = findViewById(R.id.view_page);
        viewPager2.setPageTransformer(new ScaleInTransformer(0.85f));
//        viewPager2.setPageTransformer(new OverlapPageTransformer(ViewPager2.ORIENTATION_VERTICAL, 0.85f, 0f, 1, 0));
        viewPager2.setAdapter(new BridgeWebPager2Adapter(getSupportFragmentManager(), getLifecycle()));
    }

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };
    }
}
