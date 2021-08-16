package com.android.custom.pkg.media;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import com.android.open9527.common.action.HandlerAction;
import com.blankj.utilcode.util.ThreadUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/7/8
 **/


public class MediaUtils implements HandlerAction {

    private static final String TAG = "MediaUtils";

    private static final Uri QUERY_URI = MediaStore.Files.getContentUri("external");

    private static final String ORDER_BY_DESC = MediaStore.Files.FileColumns._ID + " DESC";
    private static final String ORDER_BY_ASC = MediaStore.Files.FileColumns._ID + " ASC";

    private static final String NOT_GIF = "!='image/gif'";

    private static final String DURATION = "duration";

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private static final String[] PROJECTION_Q = {
            MediaStore.MediaColumns.ORIENTATION,
            MediaStore.MediaColumns.BUCKET_DISPLAY_NAME
    };

    //
    private static final String[] PROJECTION = {
            MediaStore.Files.FileColumns._ID,
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.WIDTH,
            MediaStore.MediaColumns.HEIGHT,
            MediaStore.MediaColumns.SIZE,
            DURATION,
            MediaStore.MediaColumns.DISPLAY_NAME
    };


    //图片或者视频
    private static final String[] SELECTION_ALL_ARGS = {
            String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE),
            String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO),
    };

    //图片
    private static final String SELECTION_IMAGE = MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
            + " AND " + MediaStore.MediaColumns.SIZE + ">0";

    //图片或者视频
    private static final String SELECTION = "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
            + " OR "
            + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?)"
            + " AND "
            + MediaStore.MediaColumns.SIZE + ">0";

    //图片不包含gif
    private static final String SELECTION_NOT_GIF = MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
            + " AND " + MediaStore.MediaColumns.SIZE + ">0"
            + " AND " + MediaStore.MediaColumns.MIME_TYPE + NOT_GIF;

    public static void getMediaList(final Context context, final int type, final ILoadMediaData<List<MediaData>> iLoadData) {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<MediaData>>() {

            @Override
            public List<MediaData> doInBackground() throws Throwable {
                Cursor cursor = context.getContentResolver().query(QUERY_URI, PROJECTION, SELECTION, SELECTION_ALL_ARGS, ORDER_BY_DESC);
                List<MediaData> mediaDataList = new ArrayList<>();
                if (cursor != null && cursor.moveToFirst()) {

                    int idIndex = cursor.getColumnIndex(PROJECTION[0]);
                    int pathIndex = cursor.getColumnIndex(PROJECTION[1]);
                    int mimeTypeIndex = cursor.getColumnIndex(PROJECTION[2]);
                    int widthIndex = cursor.getColumnIndex(PROJECTION[3]);
                    int heightIndex = cursor.getColumnIndex(PROJECTION[4]);
                    int sizeIndex = cursor.getColumnIndex(PROJECTION[5]);
                    int durationIndex = cursor.getColumnIndex(PROJECTION[6]);
                    int displayNameIndex = cursor.getColumnIndex(PROJECTION[7]);


//                    int orientationIndex = cursor.getColumnIndex(PROJECTION_Q[0]);
//                    int bucketDisplayNameIndex = cursor.getColumnIndex(PROJECTION_Q[1]);

                    do {
                        long size = cursor.getLong(sizeIndex);
                        long duration = cursor.getLong(durationIndex);
                        String displayName = cursor.getString(displayNameIndex);
                        String mimeType = cursor.getString(mimeTypeIndex);
//                        int orientation = cursor.getInt(orientationIndex);
//                        String bucketDisplayName = cursor.getString(bucketDisplayNameIndex);
                        if (size < 1) {
                            continue;
                        }

                        String id = cursor.getString(idIndex);
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
                        MediaData mediaData = new MediaData();
                        mediaData.setId(id);
                        mediaData.setPath(path);
                        mediaData.setUri(Uri.fromFile(file));
                        mediaData.setType(mimeType);
                        mediaData.setWidth(width);
                        mediaData.setHeight(height);
                        mediaData.setSize(size);
                        mediaData.setDuration(duration);
                        mediaData.setDisplayName(displayName);
//                        mediaData.setOrientation(orientation);
//                        mediaData.setBucketDisplayName(bucketDisplayName);
                        mediaDataList.add(mediaData);

                    } while (cursor.moveToNext());
                    cursor.close();
                }
                return mediaDataList;
            }

            @Override
            public void onSuccess(List<MediaData> result) {
                if (iLoadData != null) {
                    iLoadData.loadComplete(result);
                }
            }
        });

    }

}
