package com.example.eltaysser.mymovie_part2.dataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "favorite" , indices =@Index(value = {"MovieID"},unique =true))
public class FavoriteList implements Parcelable {
private final String MovieName;



    public static final Creator<FavoriteList> CREATOR = new Creator<FavoriteList>() {
        @Override
        public FavoriteList createFromParcel(Parcel in) {
            return new FavoriteList(in);
        }

        @Override
        public FavoriteList[] newArray(int size) {
            return new FavoriteList[size];
        }
    };

    public String getMovieName() {
        return MovieName;
    }

// --Commented out by Inspection START (27/02/2019 02:16 م):
//    public void setMovieName(String movieName) {
//        MovieName = movieName;
//    }
// --Commented out by Inspection STOP (27/02/2019 02:16 م)

    public String getMovieID() {
        return MovieID;
    }

// --Commented out by Inspection START (27/02/2019 02:16 م):
//// --Commented out by Inspection START (27/02/2019 02:16 م):
////    public void setMovieID(String movieID) {
// --Commented out by Inspection STOP (27/02/2019 02:16 م)
//        MovieID = movieID;
//    }
// --Commented out by Inspection STOP (27/02/2019 02:16 م)

    public String getMyRate() {
        return MyRate;
    }

    public void setMyRate(String myRate) {
        MyRate = myRate;
    }

    private final String MovieID;
private String MyRate;
    @PrimaryKey(autoGenerate = true)
    private
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FavoriteList(String MovieName, String MovieID, String MyRate) {
        this.MovieName=MovieName;
        this.MovieID=MovieID;
        this.MyRate=MyRate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
          dest.writeString(MovieName);
          dest.writeString(MyRate);
          dest.writeString(MovieID);
    }

// --Commented out by Inspection START (27/02/2019 02:16 م):
//    public final static Creator<FavoriteList> CREATOR_MY_FAVORITE=new Creator<FavoriteList>() {
//        @Override
//        public FavoriteList createFromParcel(Parcel source) {
//            return new FavoriteList(source);
//        }
//
//        @Override
//        public FavoriteList[] newArray(int size) {
//            return new FavoriteList[0];
//        }
//    };
// --Commented out by Inspection STOP (27/02/2019 02:16 م)
    public FavoriteList(Parcel source) {
        MovieName=source.readString();
        MyRate=source.readString();
        MovieID=source.readString();
    }


}
