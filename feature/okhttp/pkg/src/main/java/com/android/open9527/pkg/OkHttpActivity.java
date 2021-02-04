package com.android.open9527.pkg;

import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.open9527.common.net.data.HttpData;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.okhttp.OkHttpUtils;
import com.android.open9527.okhttp.listener.HttpCallback;
import com.android.open9527.okhttp.listener.OnDownloadListener;
import com.android.open9527.okhttp.listener.OnHttpListener;
import com.android.open9527.okhttp.listener.OnUpdateListener;
import com.android.open9527.okhttp.model.HttpMethod;
import com.android.open9527.okhttp.model.ResponseClass;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.permission.OnPermissionCallback;
import com.android.open9527.permission.Permission;
import com.android.open9527.permission.PermissionsManage;
import com.android.open9527.pkg.request.SearchAuthorApi;
import com.android.open9527.pkg.request.SearchBlogsApi;
import com.android.open9527.pkg.request.UpdateImageApi;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.File;
import java.util.List;

import okhttp3.Call;

/**
 * @author open_9527
 * Create at 2021/1/7
 **/
public class OkHttpActivity extends BaseCommonActivity implements OnHttpListener {

    private String mFilePath;

    private OkHttpViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(OkHttpViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.okhttp_activity, BR.vm, mViewModel);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);

        findViewById(R.id.btn_main_get).setOnClickListener(onClickListener);
        findViewById(R.id.btn_main_post).setOnClickListener(onClickListener);
        findViewById(R.id.btn_main_exec).setOnClickListener(onClickListener);
        findViewById(R.id.btn_main_update).setOnClickListener(onClickListener);
        findViewById(R.id.btn_main_download).setOnClickListener(onClickListener);

    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_main_get) {
                onGetRequest();

            } else if (view.getId() == R.id.btn_main_post) {
                onPostRequest();

            } else if (view.getId() == R.id.btn_main_exec) {
                onSyncRequest();

            } else if (view.getId() == R.id.btn_main_update) {

                PermissionsManage.with(mActivity)
                        // 不适配 Android 11 可以这样写
                        //.permission(Permission.Group.STORAGE)
                        // 适配 Android 11 需要这样写，这里无需再写 Permission.Group.STORAGE
                        .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                        .request(new OnPermissionCallback() {

                            @Override
                            public void onGranted(List<String> permissions, boolean all) {
                                if (all) {
                                    ToastUtils.showShort("获取存储权限成功");
                                    onUpdateRequest();
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


            } else if (view.getId() == R.id.btn_main_download) {
                PermissionsManage.with(mActivity)
                        // 适配 Android 11
                        .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                        .request(new OnPermissionCallback() {

                            @Override
                            public void onGranted(List<String> permissions, boolean all) {
                                if (all) {
                                    ToastUtils.showShort("获取存储权限成功");
                                    onDownloadRequest();
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
        }
    };


    private void onGetRequest() {
        OkHttpUtils.get(this)
                .api(new SearchAuthorApi().setAuthor("鸿洋"))
                .request(new HttpCallback<HttpData>(this) {
                    @Override
                    public void onSucceed(HttpData result) {

                        ToastUtils.showShort("Get 请求成功，请看日志");
                    }
                });
    }

    private void onPostRequest() {
        OkHttpUtils.post(this)
                .api(new SearchBlogsApi()
                        .setKeyword("搬砖不再有"))
                .request(new HttpCallback<HttpData>(this) {

                    @Override
                    public void onSucceed(HttpData result) {
                        ToastUtils.showShort("Post 请求成功，请看日志");
                    }
                });
    }

    private void onSyncRequest() {
        // 在主线程中不能做耗时操作
        new Thread(() -> {
//            runOnUiThread(this::showDialog);
            try {
                HttpData data = OkHttpUtils.post(this)
                        .api(new SearchBlogsApi()
                                .setKeyword("搬砖不再有"))
                        .execute(new ResponseClass<HttpData>() {
                        });
                ToastUtils.showShort("同步请求成功，请看日志");
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showShort(e.getMessage());
            }
//            runOnUiThread(this::hideDialog);
        }).start();

    }

    private void onUpdateRequest() {
        if (TextUtils.isEmpty(mFilePath)) {
            ToastUtils.showShort("请先操作下载!");
            return;
        }

        OkHttpUtils.post(this)
                .api(new UpdateImageApi(FileUtils.getFileByPath(mFilePath)))
                .request(new OnUpdateListener<Void>() {

                    @Override
                    public void onStart(Call call) {
//                        mProgressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onProgress(int progress) {
                        LogUtils.i(TAG, "progress-->" + progress);
//                        mProgressBar.setProgress(progress);
                    }

                    @Override
                    public void onSucceed(Void result) {
                        ToastUtils.showShort("上传成功");
                    }

                    @Override
                    public void onFail(Exception e) {
                        ToastUtils.showShort("上传失败");
                    }

                    @Override
                    public void onEnd(Call call) {
//                        mProgressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void onDownloadRequest() {
        String url = "https://images.unsplash.com/photo-1495360010541-f48722b34f7d?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=alexander-london-mJaD10XeD7w-unsplash.jpg";
        String fileName = url.substring(url.lastIndexOf("-") + 1);
        //系统相册目录
        String Pictures = Environment.getExternalStorageDirectory()
                //系统图片
                + File.separator + Environment.DIRECTORY_PICTURES
                //系统下载
//                + File.separator + Environment.DIRECTORY_DOWNLOADS
                //可创建文件夹
                + File.separator + "Open9527" + File.separator;

        OkHttpUtils.download(this)
                .method(HttpMethod.GET)
                .file(new File(new File(Pictures), fileName))
                .url(url)
//                .md5("")//md5校验
                .listener(new OnDownloadListener() {

                    @Override
                    public void onStart(File file) {
//                        mProgressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onProgress(File file, int progress) {
//                        LogUtils.i(TAG, "progress-->" + progress);
//                        mProgressBar.setProgress(progress);
                    }

                    @Override
                    public void onComplete(File file) {
                        ToastUtils.showShort("下载完成：" + file.getPath());
                        mFilePath = file.getPath();
//                        LogUtils.i(TAG, "progress-->" + file.getPath());

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
//                        mProgressBar.setVisibility(View.GONE);
                    }

                }).start();
    }
}
