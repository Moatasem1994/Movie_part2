package com.example.eltaysser.mymovie_part2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.eltaysser.mymovie_part2.dataBase.FavoriteList;
import com.example.eltaysser.mymovie_part2.dataBase.MyViewModel;

import java.util.List;

public class ShowFavoriteMovie extends AppCompatActivity {
   private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_favorite_movie);
        recyclerView=findViewById(R.id.recycle_Favorite);
       recyclerView.setLayoutManager(new GridLayoutManager(this,2));
       recyclerView.setHasFixedSize(true);
        MyViewModel viewModel=ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.get_favorite().observe(this, new Observer<List<FavoriteList>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteList> favoriteLists) {
                recyclerView.setAdapter(new AdapterFavoriteMovie(favoriteLists,ShowFavoriteMovie.this));
            }
        });

    }
}
