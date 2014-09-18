package com.oidar.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mbeloded on 9/18/14.
 */
public class Radio implements Parcelable {
    private long date;
    private String name;
    private String time;
    private int likeStatus;
    private int bookmarkStatus;

    public Radio(long date, String name, String time, int likeStatus, int bookmarkStatus) {
        this.date = date;
        this.name = name;
        this.time = time;
        this.likeStatus = likeStatus;
        this.bookmarkStatus = bookmarkStatus;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int isLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(int likeStatus) {
        this.likeStatus = likeStatus;
    }

    public int isBookmarkStatus() {
        return bookmarkStatus;
    }

    public void setBookmarkStatus(int bookmarkStatus) {
        this.bookmarkStatus = bookmarkStatus;
    }

    /**
     * Constructor for parcelable.
     */
    protected Radio(Parcel in) {
        this.date = in.readLong();
        this.name = in.readString();
        this.time = in.readString();
        this.likeStatus = in.readInt();
        this.bookmarkStatus = in.readInt();
    }

    /**
     * Used for parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Used for saving values to a parcel.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.date);
        dest.writeString(this.name);
        dest.writeString(this.time);
        dest.writeInt(this.likeStatus);
        dest.writeInt(this.bookmarkStatus);
    }

    /**
     * Used for parcelable.
     */
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Radio> CREATOR = new Parcelable.Creator<Radio>() {
        @Override
        public Radio createFromParcel(Parcel in) {
            return new Radio(in);
        }

        @Override
        public Radio[] newArray(int size) {
            return new Radio[size];
        }
    };
}
