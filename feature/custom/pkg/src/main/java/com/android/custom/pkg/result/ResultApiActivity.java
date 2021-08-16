package com.android.custom.pkg.result;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.CustomActivity;
import com.android.custom.pkg.R;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;

import java.util.Map;

/**
 * @author open_9527
 * Create at 2021/7/22
 **/
public class ResultApiActivity extends BaseCommonActivity {

    private ResultApiViewModel mViewModel;
    private ActivityResultLauncher<String> mRequestPermission;
    private ActivityResultLauncher<String[]> mRequestMultiplePermissions;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(ResultApiViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.result_api_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initView(@Nullable @org.jetbrains.annotations.Nullable Bundle bundle) {
        super.initView(bundle);
        initResultApi();
    }

    // RequestPermission()请求单个权限
    // RequestMultiplePermissions()请求多个权限
    // TakePicturePreview()拍照预览，返回 Bitmap
    // TakePicture()拍照，返回 Uri
    // TakeVideo()录像，返回 Uri
    // GetContent()获取单个内容文件
    // GetMultipleContents()获取多个内容文件
    // CreateDocument()创建文档
    // OpenDocument()打开单个文档
    // OpenMultipleDocuments()打开多个文档
    // OpenDocumentTree()打开文档目录
    // PickContact()选择联系人
    // StartActivityForResult()通用协议

    private void initResultApi() {
        mRequestPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
            showLongToast(result ? "获取权限成功" : "获取权限失败");
        });

        mRequestMultiplePermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            LogUtils.i(TAG, "result:" + GsonUtils.toJson(result));
        });
    }


    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };

        public View.OnClickListener requestPermissionClick = v -> {
            mRequestPermission.launch(Manifest.permission.CAMERA);
        };

        public View.OnClickListener requestMultiplePermissionsClick = v -> {
            mRequestMultiplePermissions.launch(new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA}, ActivityOptionsCompat.makeBasic());
        };


    }


}
