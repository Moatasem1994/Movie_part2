package com.example.eltaysser.mymovie_part2.dataBase;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.eltaysser.mymovie_part2.LayoutContent;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class RepositoryData {
    private final CRUD crud;
    private final LiveData<List<LayoutContent>>liveDataList;
    private final LiveData<List<FavoriteList>> favoriteList;
    private final LiveData<List<Top_Rated>>ListOf_topRated;
    public RepositoryData(Application application) {
        // First get instance from my local database
        DataBase dataBase = DataBase.getInstance(application);
        // Allow access my database by instance CRUD Class
        crud = dataBase.crud();
        liveDataList=crud.getData();
        favoriteList=crud.getAllFavoriteMovie();
        ListOf_topRated=crud.getAllTopRated();
    }

    public LiveData<List<Top_Rated>>getAllTopRated(){
        return ListOf_topRated;
    }

    public void InsertTopRatedItems(List<Top_Rated>top_rateds){
        new FetchTopRated_FromWebService(crud).execute(top_rateds);
    }


    public LiveData<List<FavoriteList>> getAllFavoriteList(){
        return favoriteList;
    }

    public  void InsertFavoriteMovie(FavoriteList favoriteLists) {
        if (favoriteLists != null) {
            new InsertFavorite(crud).execute(favoriteLists);
        }
    }
    static class InsertFavorite extends AsyncTask<FavoriteList,Void,Void>{
     final CRUD crud;

        InsertFavorite(CRUD crud) {
            this.crud = crud;
        }

        @Override
        protected Void doInBackground(FavoriteList... favoriteLists) {
           // crud.insertFavoriteMovie();

            crud.insertFavoriteMovie(favoriteLists[0]);
            return null;
        }
    }


    //get items in list from DB
    public LiveData<List<LayoutContent>> getData() {
        return liveDataList;
    }
    // execute asyncTask to insert Items comes from webservice
    public void InsertMyData(List<LayoutContent> listLiveData) {
         List<LayoutContent> newistLiveData;
         newistLiveData=listLiveData;
         FetchDataFromWebService fetchDataFromWebService=new FetchDataFromWebService(crud);
         fetchDataFromWebService.execute(newistLiveData);
    }



   // when asyncTask execute takes list<LayoutContent> that result of connection with web service
    static class FetchDataFromWebService extends AsyncTask<List<LayoutContent>, Void, Void> {
       private final CRUD crud;
       List<LayoutContent> layoutContents= new ArrayList<>();
       FetchDataFromWebService(CRUD crud) {
           this.crud = crud;
       }

       // --Commented out by Inspection (27/02/2019 02:16 م):LayoutContent mylayoutContent;
       @SafeVarargs
       @Override
       protected final Void doInBackground(List<LayoutContent>... voids) {
           layoutContents = voids[0];
            for (int i = 0; i < layoutContents.size(); i++) {
                LayoutContent layoutContent=layoutContents.get(i);
                crud.insertData(layoutContent);
        }

           return null;
       }

   }


    static class FetchTopRated_FromWebService extends AsyncTask<List<Top_Rated>, Void, Void> {
        private final CRUD crud;
        List<Top_Rated> top_rateds= new ArrayList<>();

        FetchTopRated_FromWebService(CRUD crud) {
            this.crud = crud;
        }

        @Override
        protected final Void doInBackground(List<Top_Rated>... top) {
            top_rateds = top[0];
            for (int i = 0; i < top_rateds.size(); i++) {
                crud.insertTopRated(top_rateds.get(i));
            }

            return null;
        }


    }




//
//
//public class Insert extends AsyncTask<Void,Void,Void> {
//    CRUD crud;
//   Application application;
//   List<LayoutContent>list;
//    List<LayoutContent>layoutContents;
//
//    public Insert(CRUD crud,Application applicationList,List<LayoutContent>layoutContents) {
//        this.crud = crud;
//        this.application=application;
//        this.layoutContents=layoutContents;
//    }
//    @Override
//    protected Void doInBackground(Void... voids) {
//
//
////        if (list == null||list.size()==0) {
////            for (int i = 0; i < layoutContents.size(); i++) {
////                crud.insertData(new LayoutContent(
////                        layoutContents.get(i).getImageUrlForPicasso(),
////                        layoutContents.get(i).getMovieName(),
////                        layoutContents.get(i).getDescription(),
////                        layoutContents.get(i).getVoteCount(),
////                        layoutContents.get(i).getYear(),
////                        layoutContents.get(i).getVoteAverage(),
////                        layoutContents.get(i).getMoveID()));
////        }
////        }
//
//        return null;
//
//    }
//}





}

