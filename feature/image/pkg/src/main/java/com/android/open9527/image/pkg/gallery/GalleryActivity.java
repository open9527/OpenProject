package com.android.open9527.image.pkg.gallery;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.image.pkg.BR;
import com.android.open9527.image.pkg.R;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.permission.OnPermissionCallback;
import com.android.open9527.permission.Permission;
import com.android.open9527.permission.PermissionsManage;
import com.android.open9527.recycleview.adapter.BaseBindingCellAdapter;
import com.android.open9527.recycleview.decoration.GridSpaceItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentGridLayoutManager;
import com.android.open9527.titlebar.OnTitleBarListener;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/27
 **/
public class GalleryActivity extends BaseCommonActivity {

    private GalleryViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(GalleryViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.gallery_activity, BR.vm, mViewModel)
                .addBindingParam(BR.titleBarListener, onTitleBarListener)
                .addBindingParam(BR.layoutManager, new WrapContentGridLayoutManager(this, 2))
                .addBindingParam(BR.itemDecoration, new GridSpaceItemDecoration(10))
                .addBindingParam(BR.adapter, new BaseBindingCellAdapter<>());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        PermissionsManage.with(this)
                .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<Object>() {
                            @Override
                            public Object doInBackground() throws Throwable {
                                getAlbum();
                                return null;
                            }

                            @Override
                            public void onSuccess(Object result) {
                                mViewModel.getImageUrl(mAllPhoto);
                                LogUtils.i(TAG, GsonUtils.toJson(mAllPhoto));
                                LogUtils.i(TAG, GsonUtils.toJson(mAllAlbum));
                            }
                        });


                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {

                    }
                });
    }

    public OnTitleBarListener onTitleBarListener = new OnTitleBarListener() {
        @Override
        public void onLeftClick(View v) {
            finish();
        }

        @Override
        public void onRightClick(View v) {

        }
    };


    public static void start() {
        ActivityUtils.startActivity(GalleryActivity.class);
    }


    /**
     * 图片专辑
     */
    private final HashMap<String, List<String>> mAllAlbum = new HashMap<>();
    /**
     * 全部图片
     */
    ArrayList<String> mAllPhoto = new ArrayList<>();

    private void getAlbum() {


        final Uri contentUri = MediaStore.Files.getContentUri("external");
        final String sortOrder = MediaStore.Files.FileColumns.DATE_MODIFIED + " DESC";
        final String selection =
                "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                        + " OR "
                        + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?)"
                        + " AND "
                        + MediaStore.MediaColumns.SIZE + ">0";

        final String[] selectionAllArgs = {String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)};

        ContentResolver contentResolver = getContentResolver();
        String[] projections;
        projections = new String[]{MediaStore.Files.FileColumns._ID, MediaStore.MediaColumns.DATA,
                MediaStore.MediaColumns.DISPLAY_NAME, MediaStore.MediaColumns.DATE_MODIFIED,
                MediaStore.MediaColumns.MIME_TYPE, MediaStore.MediaColumns.WIDTH, MediaStore
                .MediaColumns.HEIGHT, MediaStore.MediaColumns.SIZE};

        Cursor cursor = contentResolver.query(contentUri, projections, selection, selectionAllArgs, sortOrder);
        if (cursor != null && cursor.moveToFirst()) {

            int pathIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            int mimeTypeIndex = cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE);
            int sizeIndex = cursor.getColumnIndex(MediaStore.MediaColumns.SIZE);
            int widthIndex = cursor.getColumnIndex(MediaStore.MediaColumns.WIDTH);
            int heightIndex = cursor.getColumnIndex(MediaStore.MediaColumns.HEIGHT);

            do {
                long size = cursor.getLong(sizeIndex);
                if (size < 1) {
                    continue;
                }

                String type = cursor.getString(mimeTypeIndex);
                String path = cursor.getString(pathIndex);
                if (TextUtils.isEmpty(path) || TextUtils.isEmpty(type)) {
                    continue;
                }

                int width = cursor.getInt(widthIndex);
                int height = cursor.getInt(heightIndex);
                if (width < 1 || height < 1) {
                    continue;
                }

                File file = new File(path);
                if (!file.exists() || !file.isFile()) {
                    continue;
                }

                File parentFile = file.getParentFile();
                if (parentFile != null) {
                    // 获取目录名作为专辑名称
                    String albumName = parentFile.getName();
                    List<String> files = mAllAlbum.get(albumName);
                    if (files == null) {
                        files = new ArrayList<>();
                        mAllAlbum.put(albumName, files);
                    }
                    files.add(path);
                    mAllPhoto.add(path);
                }

            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
