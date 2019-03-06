package com.example.eltaysser.mymovie_part2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.eltaysser.mymovie_part2.dataBase.MyViewModel;
import com.example.eltaysser.mymovie_part2.dataBase.RepositoryData;
import com.example.eltaysser.mymovie_part2.dataBase.Top_Rated;

import java.util.List;

public class Main3Activity extends AppCompatActivity {
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        recyclerView=findViewById(R.id.Top_Rated_List);
        recyclerView.setLayoutManager(new GridLayoutManager(Main3Activity.this,2));
        recyclerView.setHasFixedSize(true);
        MyViewModel viewModel=ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getTopRated().observe(this, new Observer<List<Top_Rated>>() {
            @Override
            public void onChanged(@Nullable final List<Top_Rated> top_rateds) {
                Toast.makeText(Main3Activity.this,""+top_rateds.size(),Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(new TopRated_List_Adapter(top_rateds, Main3Activity.this, new TopRated_List_Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                        Top_Rated topRated=top_rateds.get(position);
                        intent.putExtra("topR",topRated);
                        startActivity(intent);
                    }
                }));
            }
        });
        new workBackGround().execute();
    }

    class workBackGround extends AsyncTask<Void,Void,Void>{
       RepositoryData repositoryData;
        @Override
        protected Void doInBackground(Void... voids) {
            repositoryData=new RepositoryData(Main3Activity.this.getApplication());
            new AsyncClass(Main3Activity.this.getApplication(), new AsyncClass.GetTopRates() {
                @Override
                public void sendTop_Rated(List<Top_Rated> top_rateds) {
                    repositoryData.InsertTopRatedItems(top_rateds);
                }
            },"Top_Rated").execute(ConstantValue.urlTopRated);
            return null;
        }
    }
}
