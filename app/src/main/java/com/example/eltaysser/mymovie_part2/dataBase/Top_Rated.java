package com.example.eltaysser.mymovie_part2.dataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "top")
public class Top_Rated implements Parcelable {
    //implement the parcelable interface
    private  String imageUrlForPicasso;
    private String movieName;
    private final String description;
    private int voteCount;
    private String voteAverage;

    @PrimaryKey
    private int moveID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public int getMoveID() {
        return moveID;
    }

    public void setMoveID(int moveID) {
        this.moveID = moveID;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private String year;
    private Top_Rated(Parcel in) {
        imageUrlForPicasso = in.readString();
        movieName = in.readString();
        description = in.readString();
        voteCount = Integer.parseInt(in.readString());
        year=in.readString();
        voteAverage=in.readString();
        moveID= Integer.parseInt(in.readString());
    }

    public static final Creator<Top_Rated> CREATOR = new Creator<Top_Rated>() {
        @Override
        public Top_Rated createFromParcel(Parcel in) {
            return new Top_Rated(in);
        }

        @Override
        public Top_Rated[] newArray(int size) {
            return new Top_Rated[size];
        }

    };

    public String getDescription() {
        return description;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Top_Rated(String imageUrlForPicasso, String movieName, String description, int voteCount,String year,String voteAverage,int moveID) {
        this.imageUrlForPicasso = imageUrlForPicasso;
        this.movieName = movieName;
        this.description = description;
        this.voteCount = voteCount;
        this.year=year;
        this.voteAverage=voteAverage;
        this.moveID=moveID;
    }

    public String getImageUrlForPicasso() {
        return imageUrlForPicasso;
    }

    public void setImageUrlForPicasso(String imageUrlForPicasso) {
        this.imageUrlForPicasso = imageUrlForPicasso;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrlForPicasso);
        dest.writeString(movieName);
        dest.writeString(description);
        dest.writeString(String.valueOf(voteCount));
        dest.writeString(year);
        dest.writeString(voteAverage);
        dest.writeString(String.valueOf(moveID));
    }
}
