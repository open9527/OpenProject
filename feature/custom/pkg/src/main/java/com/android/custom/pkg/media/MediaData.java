package com.android.custom.pkg.media;

import android.net.Uri;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/7/9
 **/
public class MediaData implements Serializable {

    private String id;
    private String path;
    private Uri uri;
    private String type;
    private int width;
    private int height;
    private long size;
    private long duration;
    private int orientation;
    private String displayName;
    private String bucketDisplayName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBucketDisplayName() {
        return bucketDisplayName;
    }

    public void setBucketDisplayName(String bucketDisplayName) {
        this.bucketDisplayName = bucketDisplayName;
    }

    @Override
    public String toString() {
        return "MediaData{" +
                "id='" + id + '\'' +
                ", path='" + path + '\'' +
                ", uri=" + uri +
                ", type='" + type + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", size=" + size +
                ", duration=" + duration +
                ", orientation=" + orientation +
                ", displayName='" + displayName + '\'' +
                ", bucketDisplayName='" + bucketDisplayName + '\'' +
                '}';
    }
}
