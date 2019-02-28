package com.example.eltaysser.mymovie_part2;

import android.os.Parcel;
import android.os.Parcelable;

public class TrailerInfo implements Parcelable {
    private String key;
    private String name;
    private int id;

    private TrailerInfo(Parcel source) {
        key=source.readString();
        name=source.readString();
    }

    public int getId() {
        return id;
   }

    public void setId(int id) {
        this.id = id;
    }

    public TrailerInfo(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<TrailerInfo>CREATOR=new Creator<TrailerInfo>() {
        @Override
        public TrailerInfo createFromParcel(Parcel source) {
            return new TrailerInfo(source);
        }

        @Override
        public TrailerInfo[] newArray(int size) {
            return new TrailerInfo[0];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);

    }
}
