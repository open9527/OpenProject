package com.android.open9527.image.pkg.load;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.open9527.common.net.glide.ImageLoadUtils;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.image.pkg.BR;
import com.android.open9527.image.pkg.ExitSharedElementCallback;
import com.android.open9527.image.pkg.R;
import com.android.open9527.image.pkg.SharedElementUtils;
import com.android.open9527.image.pkg.SharedElementViewModel;
import com.android.open9527.image.pkg.gallery.GalleryActivity;
import com.android.open9527.image.pkg.preview.Preview2Activity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.android.open9527.recycleview.decoration.GridSpaceItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentGridLayoutManager;
import com.android.open9527.recycleview.scroll.RecycleViewScrollListener;
import com.android.open9527.recycleview.scroll.RecycleViewScrollListener.IScrollListener;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;


/**
 * @author open_9527
 * Create at 2021/1/25
 **/

public class ImageLoadActivity extends BaseCommonActivity {


    private ImageViewLoadModel mViewModel;
    private ExitSharedElementCallback sharedElementCallback;
    private SharedElementViewModel sharedElementViewModel;


    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(ImageViewLoadModel.class);
        sharedElementViewModel = getApplicationScopeViewModel(SharedElementViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.image_load_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.scrollListener, new RecycleViewScrollListener(iScrollListener))
                .addBindingParam(BR.layoutManager, new WrapContentGridLayoutManager(this, 2))
                .addBindingParam(BR.itemDecoration, new GridSpaceItemDecoration(10))
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        sharedElementCallback = SharedElementUtils.createExitSharedElementCallback(this, findViewById(R.id.recyclerView));
    }


    @Override
    public void initRequest() {
        mViewModel.valueICellClick.set(iCellClick);
        mViewModel.getImageUrl();
    }

    @Override
    public void initEvent() {
        sharedElementViewModel.getLocationChangeEventLiveData().observe(this, locationChangeEvent -> {
            LogUtils.i(TAG, "getLocationChangeEventLiveData:");
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.smoothScrollToPosition(locationChangeEvent.getCurrentPosition());

            sharedElementCallback.setLocationChangeEvent(locationChangeEvent);
            sharedElementCallback.setTransitionName(mViewModel.valueImageUrls.get(locationChangeEvent.getCurrentPosition()));
        });
    }

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };

        public View.OnClickListener menuClick = v -> {
            ActivityUtils.startActivity(GalleryActivity.class);
        };

//            ApiUtils.getApi(PermissionApi.class).startPermission(new PermissionParam(" Permission", "Param"));

//            PermissionResult param = ApiUtils.getApi(PermissionApi.class).startPermissionForResult(new PermissionParam(" Permission", "Param"));
//            LogUtils.i(TAG, "param:" + param.toString());

//            iCommonCallBack = ApiUtils.getApi(PermissionApi.class).startPermissionCallBack(new PermissionParam(" Permission", "Param"));
    }


    public IScrollListener iScrollListener = new IScrollListener() {
        @Override
        public void onImageLoadChange(@NonNull RecyclerView recyclerView, boolean loadImage) {
            if (mActivity == null || mActivity.isFinishing() || mActivity.isDestroyed()) return;
            if (loadImage) {
                ImageLoadUtils.resumeLoad(mActivity);
            } else {
                ImageLoadUtils.pauseLoad(mActivity);
            }
        }
    };



    public ImageCell.ICellClick iCellClick = new ImageCell.ICellClick() {
        @Override
        public void onCellClick(View view, int index) {
//            PreviewActivity.start(index, mViewModel.valueImageUrls);
//            PreviewActivity.start(index, mViewModel.valueImageUrls, SharedElementUtils.createActivityOptionsCompat(mActivity, view).toBundle());
//            Preview2Activity.start(index, mViewModel.valueImageUrls);
            Preview2Activity.start(index, mViewModel.valueImageUrls, SharedElementUtils.createActivityOptionsCompat(mActivity, view).toBundle());
//            Preview2Activity.start(mActivity, index, mViewModel.valueImageUrls, 2333,  SharedElementUtils.createActivityOptionsCompat(mActivity,view).toBundle());


        }
    };
}
