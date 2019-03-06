package com.example.eltaysser.mymovie_part2.dataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.eltaysser.mymovie_part2.LayoutContent;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private final LiveData<List<LayoutContent>> liveData;
    private final LiveData<List<FavoriteList>> favorite;
    private final LiveData<List<Top_Rated>>topRated;

    public MyViewModel(@NonNull Application application) {
        super(application);
        RepositoryData repositoryData = new RepositoryData(application);
        liveData= repositoryData.getData();
        favorite= repositoryData.getAllFavoriteList();
        topRated=repositoryData.getAllTopRated();
    }
    public LiveData<List<LayoutContent>> get_data() {
        return liveData;
    }
    public LiveData<List<FavoriteList>> get_favorite() {
        return favorite;
    }
    public LiveData<List<Top_Rated>> getTopRated(){
        return topRated;
    }




}






















