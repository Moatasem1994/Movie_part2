package com.example.eltaysser.mymovie_part2.dataBase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.eltaysser.mymovie_part2.LayoutContent;

@Database(entities = {LayoutContent.class,FavoriteList.class,Top_Rated.class},version =3,exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    static private DataBase myinstance;
    private static final String DATABASE_NAME="Movie2";
     abstract CRUD crud();

   public static synchronized DataBase getInstance(Context context){
       if (myinstance==null){
            myinstance= Room.databaseBuilder(context.getApplicationContext(),DataBase.class,DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build();
        }
       return myinstance;
        }

       private static final RoomDatabase.Callback callback=new Callback() {

       };

}
