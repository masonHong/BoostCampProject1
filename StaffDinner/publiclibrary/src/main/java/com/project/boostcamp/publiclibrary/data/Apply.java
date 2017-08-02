package com.project.boostcamp.publiclibrary.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Hong Tae Joon on 2017-07-27.
 */

public class Apply implements Parcelable{
    public static final int STATE_EDIT = 0x0;
    public static final int STATE_APPLY = 0x1;
    public static final int STATE_FAIL = 0x2;

    private String id;
    private String title;
    private int number;
    private long wantedTime;
    private String wantedStyle;
    private String wantedMenu;
    private Geo geo;
    private double distance;
    private long writedTime;
    private int state;

    public Apply() {
    }

    protected Apply(Parcel in) {
        id = in.readString();
        title = in.readString();
        number = in.readInt();
        wantedTime = in.readLong();
        wantedStyle = in.readString();
        wantedMenu = in.readString();
        geo = (Geo)in.readSerializable();
        distance = in.readDouble();
        writedTime = in.readLong();
        state = in.readInt();
    }

    public static final Creator<Apply> CREATOR = new Creator<Apply>() {
        @Override
        public Apply createFromParcel(Parcel in) {
            return new Apply(in);
        }

        @Override
        public Apply[] newArray(int size) {
            return new Apply[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeInt(number);
        parcel.writeLong(wantedTime);
        parcel.writeString(wantedStyle);
        parcel.writeString(wantedMenu);
        parcel.writeSerializable(geo);
        parcel.writeDouble(distance);
        parcel.writeLong(writedTime);
        parcel.writeInt(state);
    }

    public LatLng getLatLng() {
        return new LatLng(
                geo.getCoordinates()[1],
                geo.getCoordinates()[0]
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getWantedTime() {
        return wantedTime;
    }

    public void setWantedTime(long wantedTime) {
        this.wantedTime = wantedTime;
    }

    public String getWantedStyle() {
        return wantedStyle;
    }

    public void setWantedStyle(String wantedStyle) {
        this.wantedStyle = wantedStyle;
    }

    public String getWantedMenu() {
        return wantedMenu;
    }

    public void setWantedMenu(String wantedMenu) {
        this.wantedMenu = wantedMenu;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getWritedTime() {
        return writedTime;
    }

    public void setWritedTime(long writedTime) {
        this.writedTime = writedTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
