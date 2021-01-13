package com.android.open9527.okhttp.listener;

/**
 * @author open_9527
 * Create at 2021/1/4
 * <p>
 **/
public interface OnUpdateListener<T> extends OnHttpListener<T> {

    /**
     * 上传字节改变
     *
     * @param totalByte             总字节数
     * @param updateByte            已上传字节数
     */
    default void onByte(long totalByte, long updateByte) {}

    /**
     * 上传进度改变
     *
     * @param progress          上传进度值（0-100）
     */
    void onProgress(int progress);
}