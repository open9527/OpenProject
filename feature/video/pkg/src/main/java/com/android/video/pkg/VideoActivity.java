package com.android.video.pkg;

import android.view.View;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.video.pkg.live.LandscapeActivity;
import com.android.video.pkg.live.PortraitActivity;
import com.android.video.pkg.other.PipActivity;
import com.android.video.pkg.tiktok.TikTokActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.open9527.annotation.router.Router;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/

@Router(path = "/video/VideoActivity")
public class VideoActivity extends BaseCommonActivity {

    private VideoViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(VideoViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.video_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };
        public View.OnClickListener tikTokClick = v -> {
            ActivityUtils.startActivity(TikTokActivity.class);
        };

        public View.OnClickListener landscapeLiveClick = v -> {
            ActivityUtils.startActivity(LandscapeActivity.class);
        };

        public View.OnClickListener portraitLiveClick = v -> {
            ActivityUtils.startActivity(PortraitActivity.class);
        };

        public View.OnClickListener pipClick = v -> {
            ActivityUtils.startActivity(PipActivity.class);
        };
    }
}
