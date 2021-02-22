package com.android.open9527.image.pkg.preview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.android.open9527.common.bundle.BaseBundleData;
import com.android.open9527.common.bundle.BundleUtils;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.image.pkg.BR;
import com.android.open9527.image.pkg.EnterSharedElementCallback;
import com.android.open9527.image.pkg.LocationChangeEvent;
import com.android.open9527.image.pkg.R;
import com.android.open9527.image.pkg.SharedElementUtils;
import com.android.open9527.image.pkg.SharedElementViewModel;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/25
 **/
public class PreviewActivity extends BaseCommonActivity {

    private PreviewViewModel mViewModel;
    private EnterSharedElementCallback mEnterSharedElementCallback;

    private SharedElementViewModel sharedElementViewModel;
    private BundleData bundleData;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(PreviewViewModel.class);
        sharedElementViewModel = getApplicationScopeViewModel(SharedElementViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.preview_activity, BR.vm, mViewModel)
                .addBindingParam(BR.vpChangeListener, onPageChangeListener);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        bundleData = BundleUtils.getBundleData(bundle);
        mViewModel.valueTitle.set(getTitle().toString());
        if (bundleData == null) {
            throw new IllegalArgumentException("bundleData is null");
        }
        mViewModel.valueTransitionName.set(bundleData.getList().get(bundleData.getIndex()).toString());
        mViewModel.valuePageAdapter.set(new PhotoViewPageAdapter(this, bundleData.getList(), back));
        mViewModel.valuePageIndex.set(bundleData.getIndex());

        mEnterSharedElementCallback = SharedElementUtils.createEnterSharedElementCallback(this, findViewById(R.id.photo_view_pager));
    }


    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            LogUtils.i(TAG, "onPageSelected:" + position);
            mViewModel.valuePageIndex.set(position);
            mEnterSharedElementCallback.setLocationChangeEvent(new LocationChangeEvent(bundleData.getIndex(), position));
            mEnterSharedElementCallback.setTransitionName(bundleData.getList().get(position).toString());
            mViewModel.valueTransitionName.set(bundleData.getList().get(position).toString());

            sharedElementViewModel.requestLocationChangeEventLiveData(new LocationChangeEvent(bundleData.getIndex(), mViewModel.valuePageIndex.get()));

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private View.OnClickListener back = v -> {
        finishAfterTransition();
    };


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedElementUtils.finishSharedElement(this);
    }


    public static class BundleData extends BaseBundleData {
        private int index;
        private List list;

        public BundleData(int index, List list) {
            this.index = index;
            this.list = list;
        }

        public int getIndex() {
            return index;
        }

        public List getList() {
            return list;
        }

    }

    public static void start(int index, List list) {
        ActivityUtils.startActivity(BundleUtils.create(new BundleData(index, list)), PreviewActivity.class);
    }

    public static void start(int index, List list, Bundle bundle) {
        ActivityUtils.startActivity(BundleUtils.create(new BundleData(index, list)), PreviewActivity.class, bundle);
    }
}
