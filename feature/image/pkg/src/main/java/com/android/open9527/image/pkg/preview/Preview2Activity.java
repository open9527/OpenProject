package com.android.open9527.image.pkg.preview;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.android.open9527.common.bundle.BaseBundleData;
import com.android.open9527.common.bundle.BundleUtils;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.image.pkg.BR;
import com.android.open9527.image.pkg.EnterSharedElementCallback;
import com.android.open9527.image.pkg.LocationChangeEvent;
import com.android.open9527.image.pkg.R;
import com.android.open9527.image.pkg.SharedElementUtils;
import com.android.open9527.image.pkg.SharedElementViewModel;
import com.android.open9527.okhttp.OkHttpUtils;
import com.android.open9527.okhttp.listener.OnDownloadListener;
import com.android.open9527.okhttp.model.HttpMethod;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.permission.OnPermissionCallback;
import com.android.open9527.permission.Permission;
import com.android.open9527.permission.PermissionsManage;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.File;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/25
 **/
public class Preview2Activity extends BaseCommonActivity {

    private SharedElementViewModel sharedElementViewModel;
    private PreviewViewModel mViewModel;
    private BundleData bundleData;
    private EnterSharedElementCallback mEnterSharedElementCallback;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(PreviewViewModel.class);
        sharedElementViewModel = getApplicationScopeViewModel(SharedElementViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.preview_2_activity, BR.vm, mViewModel)
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>())
                .addBindingParam(BR.vpCallBack, onPageChangeCallback);
    }

    @Override
    public void initStatusBar() {
//        super.initStatusBar();
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        bundleData = BundleUtils.getBundleData(bundle);
        super.initView(bundle);
        mViewModel.valueICellClick.set(iCellClick);
        if (bundleData == null) {
            throw new IllegalArgumentException("bundleData is null");
        }
        mViewModel.initData(bundleData.getList());
        mViewModel.valuePageSmoothScroll.set(false);
        mViewModel.valuePageIndex.set(bundleData.getIndex());
        mViewModel.valueTransitionName.set(bundleData.getList().get(bundleData.getIndex()).toString());

        mViewModel.valueTitle.set(getTitle().toString());
        mEnterSharedElementCallback = SharedElementUtils.createEnterSharedElementCallback(this, findViewById(R.id.photo_view_pager));
    }


    public PreviewCell.ICellClick iCellClick = new PreviewCell.ICellClick() {
        @Override
        public void onCellClick() {
            // 单击图片退出当前的 Activity
            if (!mActivity.isFinishing()) {
                SharedElementUtils.finishSharedElement(mActivity);
            }
        }

        @Override
        public void onDownload(String url) {
            PermissionsManage.with(mActivity)
                    .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                    .request(new OnPermissionCallback() {

                        @Override
                        public void onGranted(List<String> permissions, boolean all) {
                            if (all) {
                                onDownloadRequest(url);
                            }
                        }

                        @Override
                        public void onDenied(List<String> permissions, boolean never) {
                            if (never) {
                                ToastUtils.showShort("被永久拒绝授权，请手动授予存储权限");
                                // 如果是被永久拒绝就跳转到应用权限系统设置页面
                                PermissionsManage.startPermissionActivity(mActivity, permissions);
                            } else {
                                ToastUtils.showShort("获取存储权限失败");
                            }
                        }
                    });
        }
    };


    private void onDownloadRequest(String url) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        LogUtils.i(TAG, fileName);

        //1.系统相册目录
        String Pictures = Environment.getExternalStorageDirectory()

                //系统图片
                + File.separator + Environment.DIRECTORY_PICTURES
                //系统下载
//                + File.separator + Environment.DIRECTORY_DOWNLOADS
                //可创建文件夹
                + File.separator + "Open9527" + File.separator;


//        //2.指定目录
//        File fileDir = new File(Environment.getExternalStorageDirectory(), "open_pictures");
//        if (!fileDir.exists()) {
//            fileDir.mkdir();
//        }


        OkHttpUtils.download(this)
                .method(HttpMethod.GET)
                //0.根目录
//                .file(new File(Environment.getExternalStorageDirectory(), fileName))
                //1.系统相册目录
                .file(new File(new File(Pictures), fileName))
                //2.指定目录
//                .file(new File(fileDir, fileName))
                .url(url)
//                .md5("")//md5校验
                .listener(new OnDownloadListener() {

                    @Override
                    public void onStart(File file) {
//                        mProgressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onProgress(File file, int progress) {
                        LogUtils.i(TAG, "progress-->" + progress);
//                        mProgressBar.setProgress(progress);
                    }

                    @Override
                    public void onComplete(File file) {
                        ToastUtils.showShort("下载完成：" + file.getPath());
                        LogUtils.i(TAG, "progress-->" + file.getPath());
                        //更新在相册中显示
                        MediaScannerConnection.scanFile(mActivity, new String[]{file.getAbsolutePath()}, new String[]{"image/*"}, (path, uri) -> {
                            LogUtils.i(TAG, "scanFile-->" + file.getPath());
                        });


                    }

                    @Override
                    public void onError(File file, Exception e) {
                        ToastUtils.showShort("下载出错：" + e.getMessage());
                    }

                    @Override
                    public void onEnd(File file) {
                    }

                }).start();
    }


    public ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            LogUtils.i(TAG, "onPageSelected:" + position);
            super.onPageSelected(position);
            if (position == mViewModel.valuePageIndex.get()) {
                return;
            }
            mViewModel.valuePageIndex.set(position);
            mEnterSharedElementCallback.setLocationChangeEvent(new LocationChangeEvent(bundleData.getIndex(), position));
            mEnterSharedElementCallback.setTransitionName(bundleData.getList().get(position).toString());
            mViewModel.valueTransitionName.set(bundleData.getList().get(position).toString());
            sharedElementViewModel.requestLocationChangeEventLiveData(new LocationChangeEvent(bundleData.getIndex(), mViewModel.valuePageIndex.get()));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
            mViewModel.valuePageSmoothScroll.set(true);
        }
    };


    private static class BundleData extends BaseBundleData {
        private int index;
        private List list;

        private BundleData(int index, List list) {
            this.index = index;
            this.list = list;
        }

        private int getIndex() {
            return index;
        }

        private List getList() {
            return list;
        }

    }

    public static void start(int index, List list) {
        ActivityUtils.startActivity(BundleUtils.create(new BundleData(index, list)), Preview2Activity.class);

    }

    public static void start(int index, List list, @Nullable final Bundle options) {
        ActivityUtils.startActivity(BundleUtils.create(new BundleData(index, list)), Preview2Activity.class, options);

    }

    public static void start(Activity activity, int index, List list, int requestCode, @Nullable final Bundle options) {
        ActivityUtils.startActivityForResult(BundleUtils.create(new BundleData(index, list)), activity, Preview2Activity.class, requestCode, options);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        SharedElementUtils.finishSharedElement(this);
    }

}
