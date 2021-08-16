package com.android.open9527.image.pkg.album;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.blankj.utilcode.util.ThreadUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author open_9527
 * Create at 2021/1/27
 **/
public class MediaUtils {

    private static final String TAG = "MediaUtils";

    private static final Uri QUERY_URI = MediaStore.Files.getContentUri("external");
    private static final String ORDER_BY = MediaStore.Files.FileColumns._ID + " DESC";

    private static final String NOT_GIF = "!='image/gif'";
    private static final String DURATION = "duration";

    public final static int TYPE_ALL = 0;
    public final static int TYPE_IMAGE = 1;
    public final static int TYPE_VIDEO = 2;
    public final static int TYPE_AUDIO = 3;


    /**
     * 获取媒体文件数据
     *
     * @param context
     * @param selection
     * @param selectionArgs
     * @param type
     * @param iLoadData
     */
    public static void getMediaList(final Context context, final String selection, final String[] selectionArgs, final int type, final ILoadData<List<LocalMediaFolder>> iLoadData) {
        //添加异步处理
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<LocalMediaFolder>>() {
            @Override
            public List<LocalMediaFolder> doInBackground() throws Throwable {
                Cursor cursor = context.getContentResolver().query(QUERY_URI, PROJECTION, selection, selectionArgs, ORDER_BY);
                List<LocalMediaFolder> imageFolders = new ArrayList<>();
                LocalMediaFolder allImageFolder = new LocalMediaFolder();
                List<LocalMedia> latelyImages = new ArrayList<>();
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    do {
                        int pathIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);

                        String path = cursor.getString(pathIndex);
                        if (TextUtils.isEmpty(path)) {
                            continue;
                        }

                        String pictureType = cursor.getString(cursor.getColumnIndexOrThrow(PROJECTION[2]));

                        int width = cursor.getInt(cursor.getColumnIndexOrThrow(PROJECTION[3]));

                        int high = cursor.getInt(cursor.getColumnIndexOrThrow(PROJECTION[4]));

                        int duration = cursor.getInt(cursor.getColumnIndexOrThrow(PROJECTION[5]));

                        LocalMedia image = new LocalMedia(path, duration, type, pictureType, width, high);

                        LocalMediaFolder folder = getImageFolder(path, imageFolders);

                        List<LocalMedia> images = folder.getImages();
                        images.add(image);

                        folder.setImageNum(folder.getImageNum() + 1);
                        latelyImages.add(image);

                        int imageNum = allImageFolder.getImageNum();

                        allImageFolder.setImageNum(imageNum + 1);

                    } while (cursor.moveToNext());

                    cursor.close();
                }
                return imageFolders;
            }

            @Override
            public void onSuccess(List<LocalMediaFolder> result) {
                if (iLoadData != null) {
                    iLoadData.loadComplete(result);
                }
            }
        });


    }

    /**
     * 媒体文件数据库字段
     */
    private static final String[] PROJECTION = {
            MediaStore.Files.FileColumns._ID,
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.WIDTH,
            MediaStore.MediaColumns.HEIGHT,
            DURATION};

    /**
     * 获取所有系统资源
     *
     * @param time_condition
     * @param isGif
     * @return
     */
    public static String getAllMediaCondition(String time_condition, boolean isGif) {
        String condition = "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                + (isGif ? "" : " AND " + MediaStore.MediaColumns.MIME_TYPE + NOT_GIF)
                + " OR "
                + (MediaStore.Files.FileColumns.MEDIA_TYPE + "=? AND " + time_condition) + ")"
                + " AND " + MediaStore.MediaColumns.SIZE + ">0";
        return condition;
    }

    /**
     * 获取图片or视频
     */
    public static final String[] SELECTION_ALL_ARGS = {
            String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE),
            String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO),
    };

    /**
     * 获取手机号码
     */
    public static final String[] SELECTION_PHONE_NUMBER = {
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };


    /**
     * 图片
     */
    public static final String SELECTION = MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
            + " AND " + MediaStore.MediaColumns.SIZE + ">0";

    /**
     * 图片(不包含gif)
     */
    public static final String SELECTION_NOT_GIF = MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
            + " AND " + MediaStore.MediaColumns.SIZE + ">0"
            + " AND " + MediaStore.MediaColumns.MIME_TYPE + NOT_GIF;

    /**
     * 查询条件(音视频)
     *
     * @param time_condition
     * @return
     */
    public static String getSelectionArgsForSingleMediaCondition(String time_condition) {
        return MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                + " AND " + MediaStore.MediaColumns.SIZE + ">0"
                + " AND " + time_condition;
    }

    /**
     * 获取视频(最长或最小时间)
     *
     * @param exMaxLimit
     * @param exMinLimit
     * @param videoMaxS
     * @param videoMinS
     * @return
     */
    public static String getDurationCondition(long exMaxLimit, long exMinLimit, long videoMaxS, long videoMinS) {
        long maxS = videoMaxS == 0 ? Long.MAX_VALUE : videoMaxS;
        if (exMaxLimit != 0) maxS = Math.min(maxS, exMaxLimit);

        return String.format(Locale.CHINA, "%d <%s duration and duration <= %d",
                Math.max(exMinLimit, videoMinS),
                Math.max(exMinLimit, videoMinS) == 0 ? "" : "=",
                maxS);
    }

    /**
     * 创建相应文件夹
     *
     * @param path
     * @param imageFolders
     * @return
     */
    private static LocalMediaFolder getImageFolder(String path, List<LocalMediaFolder> imageFolders) {
        File imageFile = new File(path);
        File folderFile = imageFile.getParentFile();
        for (LocalMediaFolder folder : imageFolders) {
            // 同一个文件夹下，返回自己，否则创建新文件夹
            assert folderFile != null;
            if (folder.getName().equals(folderFile.getName())) {
                return folder;
            }
        }
        LocalMediaFolder newFolder = new LocalMediaFolder();
        assert folderFile != null;
        newFolder.setName(folderFile.getName());
        newFolder.setPath(folderFile.getAbsolutePath());
        newFolder.setFirstImagePath(path);
        imageFolders.add(newFolder);
        return newFolder;
    }


    /**
     * 获取手机联系人
     * <p>
     * <uses-permission android:name="android.permission.READ_CONTACTS" />
     * <uses-permission android:name="android.permission.WRITE_CONTACTS" />
     *
     * @param context
     */
    public static void getContacts(Context context, final ILoadData<List<Contacts>> iLoadData) {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<Contacts>>() {
            @Override
            public List<Contacts> doInBackground() throws Throwable {
                List<Contacts> contactsList = new ArrayList<>();
                //查询姓名,id
                ContentResolver contentResolver = context.getContentResolver();

                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    do {
                        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        boolean hasNamber = "1".equals(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                        //再根据 id 获取当前联系人手机号码
                        if (hasNamber) {
                            Cursor phonesCusor = contentResolver.query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    SELECTION_PHONE_NUMBER,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                                    null,
                                    null);
                            //因为每个联系人可能有多个电话号码，所以需要遍历
                            if (phonesCusor != null && phonesCusor.getCount() > 0) {
                                phonesCusor.moveToFirst();
                                do {
                                    String num = phonesCusor.getString(0);
                                    //添加手机号
                                    contactsList.add(new Contacts(id, name, num, hasNamber));
                                } while (phonesCusor.moveToNext());
                                phonesCusor.close();
                            }
                        } else {
                            // 无手机号
                            contactsList.add(new Contacts(id, name, hasNamber));
                        }
                    } while (cursor.moveToNext());
                    cursor.close();
                }
                return contactsList;
            }

            @Override
            public void onSuccess(List<Contacts> result) {
                if (iLoadData != null) {
                    iLoadData.loadComplete(result);
                }

            }
        });


    }
}
