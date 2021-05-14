package com.android.video.pkg.tiktok;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.android.open9527.video.common.player.VideoView;
import com.android.video.export.Utils;
import com.android.video.export.component.TikTokView;
import com.android.video.export.controller.TikTokController;
import com.android.video.export.render.TikTokRenderViewFactory;
import com.android.video.pkg.BR;
import com.android.video.pkg.R;
import com.android.video.pkg.databinding.TikTokActivityBinding;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;

/**
 * @author open_9527
 * Create at 2021/5/14
 **/
public class TikTokActivity extends BaseCommonActivity {

    private TikTokViewModel mViewModel;
    private BaseBindingCellListAdapter mAdapter;
    private ViewPager2 mViewPager;

    private int mCurPos;
    private RecyclerView mViewPagerImpl;
    private VideoView mVideoView;
    private TikTokController mController;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(TikTokViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.tik_tok_activity, BR.vm, mViewModel);
    }

    @Override
    public void initStatusBar() {
        BarUtils.transparentStatusBar(this);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        initViewPager();
        initVideoView();
        mViewPager.post(() -> startPlay(0));
    }

    @Override
    public void initRequest() {
        mViewModel.requestVideo();
    }

    private void initViewPager() {
        mViewPager = ((TikTokActivityBinding) getBinding()).viewPager;
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mViewPager.setAdapter(mAdapter = new BaseBindingCellListAdapter<BaseBindingCell>() {
            @Override
            public void onViewAttachedToWindow(@NonNull BaseBindingCellViewHolder holder) {
                super.onViewAttachedToWindow(holder);

            }

            @Override
            public void onViewDetachedFromWindow(@NonNull BaseBindingCellViewHolder holder) {
                super.onViewDetachedFromWindow(holder);

            }
        });
        mAdapter.submitList(mViewModel.valueCells);

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            private int mCurItem;
            /**
             * ViewPager2是否反向滑动
             */
            private boolean mIsReverseScroll;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == mCurItem) {
                    return;
                }
                mIsReverseScroll = position < mCurItem;
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == mCurPos) return;
                mViewPager.post(() -> startPlay(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    mCurItem = mViewPager.getCurrentItem();
                }
            }
        });
        //ViewPage2内部是通过RecyclerView去实现的，它位于ViewPager2的第0个位置
        mViewPagerImpl = (RecyclerView) mViewPager.getChildAt(0);
    }

    private void initVideoView() {
        mVideoView = new VideoView(this);
        mVideoView.setLooping(true);
        mVideoView.setRenderViewFactory(TikTokRenderViewFactory.create());
//        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);

        mController = new TikTokController(this);
        mVideoView.setVideoController(mController);

    }

    private void startPlay(int position) {
        int count = mViewPagerImpl.getChildCount();
        for (int i = 0; i < count; i++) {
            View itemView = mViewPagerImpl.getChildAt(i);

            TikTokCell tikTokCell = (TikTokCell) itemView.getTag();
            FrameLayout videoContainer = (FrameLayout) itemView.getTag(R.id.container);
            TikTokView videoTikTokView = (TikTokView) itemView.getTag(R.id.tik_tok_view);

            if (tikTokCell.valueVideoIndex.get() == position) {
                mVideoView.release();
                Utils.removeViewFormParent(mVideoView);
                LogUtils.i(TAG, "startPlay: " + "position: " + position + "  url: " + tikTokCell.valueVideoUrl.get());
                mVideoView.setUrl(tikTokCell.valueVideoUrl.get());
                mController.addControlComponent(videoTikTokView, true);
                videoContainer.addView(mVideoView, 0);
                mVideoView.start();
                mCurPos = position;
                break;
            }
        }
    }

}
