package com.example.eltaysser.mymovie_part2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.eltaysser.mymovie_part2.dataBase.MyViewModel;
import com.example.eltaysser.mymovie_part2.dataBase.RepositoryData;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // recycleView Needed it to Show The Data Received it from server.
        recyclerView=findViewById(R.id.views);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        recyclerView.setHasFixedSize(true);
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
         viewModel.get_data().observe(this, new Observer<List<LayoutContent>>() {
                    @Override
                    public void onChanged(@Nullable final List<LayoutContent> layoutContents) {
                        recyclerView.setAdapter(new AdapterView(layoutContents, MainActivity.this.getApplicationContext(), new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                LayoutContent layoutContent=layoutContents.get(position);
                                intent.putExtra("layoutContent",layoutContent);
                                startActivity(intent);

                            }
                        }));
                    }
                }
        );

        new work().execute();
    }

    class work extends AsyncTask<Void,Void,Void> {
        RepositoryData repositoryData;

        @Override
        protected Void doInBackground(Void... voids) {
            repositoryData = new RepositoryData(MainActivity.this.getApplication());
            new AsyncClass(MainActivity.this.getApplication(), new AsyncClass.Get() {
                @Override
                public void setAdapter(List<LayoutContent> layoutContents) {
                    repositoryData.InsertMyData(layoutContents);
                }
            },"Movies").execute(ConstantValue.url);

            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Get The Menu Layout in the Main Activity.
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return true;
    }

    // What Happen if User Click on any Items from Menu.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.favorite_movie:
                startActivity(new Intent(MainActivity.this,ShowFavoriteMovie.class));
                return true;
            case R.id.Top_Rated:
                  startActivity(new Intent(MainActivity.this,Main3Activity.class));
            default:return super.onOptionsItemSelected(item);
        }
    }
    

}
