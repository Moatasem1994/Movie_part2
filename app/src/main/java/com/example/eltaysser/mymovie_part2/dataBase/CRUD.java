package com.example.eltaysser.mymovie_part2.dataBase;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.eltaysser.mymovie_part2.LayoutContent;

import java.util.List;

@Dao
interface CRUD {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
   void insertData(LayoutContent customData);
   @Query("SELECT * FROM `table2`")
   LiveData<List<LayoutContent>> getData();

   @Insert(onConflict =OnConflictStrategy.REPLACE)
   void insertFavoriteMovie(FavoriteList favoriteList);

   @Query("SELECT * FROM favorite ORDER BY MyRate DESC")
   LiveData<List<FavoriteList>> getAllFavoriteMovie();

   @Insert(onConflict =OnConflictStrategy.REPLACE)
   void insertTopRated(Top_Rated topRated);
   @Query("SELECT * FROM top ORDER BY voteCount DESC")
   LiveData<List<Top_Rated>> getAllTopRated();

}
