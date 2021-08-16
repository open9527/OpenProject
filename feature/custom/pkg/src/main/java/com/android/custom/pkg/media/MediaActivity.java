package com.android.custom.pkg.media;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.permission.OnPermissionCallback;
import com.android.open9527.permission.Permission;
import com.android.open9527.permission.PermissionsManage;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.android.open9527.recycleview.decoration.GridSpaceItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentGridLayoutManager;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/7/8
 **/
public class MediaActivity extends BaseCommonActivity {

    private MediaViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(MediaViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.media_activity, BR.vm, mViewModel)
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>())
                .addBindingParam(BR.layoutManager, new WrapContentGridLayoutManager(this, 2))
                .addBindingParam(BR.itemDecoration, new GridSpaceItemDecoration(10))
                .addBindingParam(BR.click, new ClickProxy());
    }


    @Override
    public void initView(@Nullable @org.jetbrains.annotations.Nullable Bundle bundle) {
        super.initView(bundle);
        PermissionsManage.with(this)
                .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        createCells();
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {

                    }
                });
    }

    private void createCells() {
        MediaUtils.getMediaList(mActivity, 0, new ILoadMediaData<List<MediaData>>() {
            @Override
            public void loadComplete(List<MediaData> mediaDataList) {
                LogUtils.i(TAG, "mediaDataList:" + GsonUtils.toJson(mediaDataList));
                mViewModel.getCells(mediaDataList);
            }
        });

    }

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };
    }

}
