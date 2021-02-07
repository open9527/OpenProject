package com.android.open9527.image.pkg.load;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.feature.permission.export.PermissionApi;
import com.android.feature.permission.export.PermissionParam;
import com.android.feature.permission.export.PermissionResult;
import com.android.open9527.common.callback.ICommonCallBack;
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
import com.android.open9527.recycleview.adapter.BaseBindingCellAdapter;
import com.android.open9527.recycleview.decoration.GridSpaceItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentGridLayoutManager;
import com.android.open9527.recycleview.scroll.RecycleViewScrollListener;
import com.android.open9527.recycleview.scroll.RecycleViewScrollListener.IScrollListener;
import com.android.open9527.titlebar.OnTitleBarListener;
import com.blankj.utilcode.util.ApiUtils;
import com.blankj.utilcode.util.LogUtils;

/**
 * @author open_9527
 * Create at 2021/1/25
 **/
public class ImageActivity extends BaseCommonActivity {


    private ImageViewModel mViewModel;
    private ExitSharedElementCallback sharedElementCallback;
    private SharedElementViewModel sharedElementViewModel;
//    private ICommonCallBack iCommonCallBack;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(ImageViewModel.class);
        sharedElementViewModel = getApplicationScopeViewModel(SharedElementViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.image_activity, BR.vm, mViewModel)
                .addBindingParam(BR.titleBarListener, onTitleBarListener)
                .addBindingParam(BR.scrollListener, new RecycleViewScrollListener(iScrollListener))
                .addBindingParam(BR.layoutManager, new WrapContentGridLayoutManager(this, 2))
                .addBindingParam(BR.itemDecoration, new GridSpaceItemDecoration(10))
                .addBindingParam(BR.adapter, new BaseBindingCellAdapter<>());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        sharedElementCallback = SharedElementUtils.createExitSharedElementCallback(this, findViewById(R.id.recyclerView));
    }

    @Override
    public void initRequest() {
        mViewModel.valueTitle.set(getTitle().toString());
        mViewModel.valueICellClick.set(iCellClick);
        mViewModel.getImageUrl("json/image.json");
//        mViewModel.getImageUrl("json/image_imageslim.json");
    }

    @Override
    public void initEvent() {
        sharedElementViewModel.getLocationChangeEventLiveData().observeInActivity(this, locationChangeEvent -> {
            LogUtils.i(TAG, "getLocationChangeEventLiveData:");
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.smoothScrollToPosition(locationChangeEvent.getCurrentPosition());

            sharedElementCallback.setLocationChangeEvent(locationChangeEvent);
            sharedElementCallback.setTransitionName(mViewModel.valueImageUrls.get(locationChangeEvent.getCurrentPosition()));
        });
    }

    public OnTitleBarListener onTitleBarListener = new OnTitleBarListener() {
        @Override
        public void onLeftClick(View v) {
            finish();
        }

        @Override
        public void onRightClick(View v) {
//            GalleryActivity.start();
//            ApiUtils.getApi(PermissionApi.class).startPermission(new PermissionParam(" Permission", "Param"));

            PermissionResult param = ApiUtils.getApi(PermissionApi.class).startPermissionForResult(new PermissionParam(" Permission", "Param"));
            LogUtils.i(TAG, "param:" + param.toString());

//            iCommonCallBack = ApiUtils.getApi(PermissionApi.class).startPermissionCallBack(new PermissionParam(" Permission", "Param"));


        }
    };

    public IScrollListener iScrollListener = new IScrollListener() {
        @Override
        public void onImageLoadChange(@NonNull RecyclerView recyclerView, boolean loadImage) {
//            LogUtils.i(TAG, "onImageLoadChange:" + loadImage);
            if (mActivity == null || mActivity.isFinishing() || mActivity.isDestroyed()) return;
            if (loadImage) {
                ImageLoadUtils.resumeLoad(recyclerView);
            } else {
                ImageLoadUtils.pauseLoad(recyclerView);
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

//            if (iCommonCallBack != null) {
//                iCommonCallBack.onCallBack();
//            }

        }
    };
}
