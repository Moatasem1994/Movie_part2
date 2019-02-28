package com.example.eltaysser.mymovie_part2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

@Entity(tableName = "table2", indices =@Index(value = {"moveID"},unique = true))
public class LayoutContent implements Parcelable {
    //implement the parcelable interface
   private  String imageUrlForPicasso;
   private String movieName;
   private final String description;
   private String voteCount;
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
    private LayoutContent(Parcel in) {
        imageUrlForPicasso = in.readString();
        movieName = in.readString();
        description = in.readString();
        voteCount = in.readString();
        year=in.readString();
        voteAverage=in.readString();
        moveID= Integer.parseInt(Objects.requireNonNull(in.readString()));
    }

    public static final Creator<LayoutContent> CREATOR = new Creator<LayoutContent>() {
        @Override
        public LayoutContent createFromParcel(Parcel in) {
            return new LayoutContent(in);
        }

        @Override
        public LayoutContent[] newArray(int size) {
            return new LayoutContent[size];
        }

};

    public String getDescription() {
        return description;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public LayoutContent(String imageUrlForPicasso, String movieName, String description, String voteCount,String year,String voteAverage,int moveID) {
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
        dest.writeString(voteCount);
        dest.writeString(year);
        dest.writeString(voteAverage);
        dest.writeString(String.valueOf(moveID));
    }
}
