package com.android.open9527.image.pkg.album;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author open_9527
 * Create at 2021/1/27
 **/
public class Contacts implements Parcelable {
    private String id;
    private String name;
    private String number;
    private boolean hasNumber;

    public Contacts(String id, String name, boolean hasNumber) {
        this.id = id;
        this.name = name;
        this.hasNumber = hasNumber;
    }

    public Contacts(String id, String name, String number, boolean hasNumber) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.hasNumber = hasNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isHasNumber() {
        return hasNumber;
    }

    public void setHasNumber(boolean hasNumber) {
        this.hasNumber = hasNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.number);
        dest.writeByte(this.hasNumber ? (byte) 1 : (byte) 0);
    }

    public Contacts() {
    }

    protected Contacts(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.number = in.readString();
        this.hasNumber = in.readByte() != 0;
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel source) {
            return new Contacts(source);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };
}
