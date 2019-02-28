package com.example.eltaysser.mymovie_part2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.eltaysser.mymovie_part2.dataBase.FavoriteList;
import com.example.eltaysser.mymovie_part2.dataBase.RepositoryData;
import com.example.eltaysser.mymovie_part2.dataBase.MyViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private String name;
    private String moveId;
    private RepositoryData repositoryData;
    private MyViewModel myViewModel;
    private ListView list_Reviews;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView imageView = findViewById(R.id.imageView);
        TextView tName = findViewById(R.id.tName);
        TextView tOverView = findViewById(R.id.tDesc);
        TextView tVote = findViewById(R.id.vote);
        TextView tYear = findViewById(R.id.year);
        TextView tAverage = findViewById(R.id.average);
        list_Reviews = findViewById(R.id.list_reviews);
        Button bFavorite = findViewById(R.id.button);
        myViewModel =ViewModelProviders.of(Main2Activity.this).get(MyViewModel.class);
        listView = findViewById(R.id.list_Trailer);

        // here use getParcelableExtra instead of using getBundle
        Intent intent = getIntent();
        LayoutContent layoutContent = intent.getParcelableExtra("layoutContent");
        if (layoutContent != null) {
            name = layoutContent.getMovieName();
            String postImage = layoutContent.getImageUrlForPicasso();
            String desc = layoutContent.getDescription();
            String vote = layoutContent.getVoteCount();
            String year = layoutContent.getYear().substring(0, 4);
            String average = layoutContent.getVoteAverage();
            String path = ConstantValue.pathForImage;
            moveId = String.valueOf(layoutContent.getMoveID());
            Picasso.get().load(path + postImage).into(imageView);
            tName.setText(name);
            tOverView.setText(desc);
            tVote.setText(vote + R.string.min);
            tYear.setText(year);
            tAverage.setText(average + R.string.Value);
            final String trailer = "Trailer";

            String urlTrailer = "http://api.themoviedb.org/3/movie/" + moveId + "/videos?api_key=092042c5d3956048aa1d5341d1571bdf";
            new AsyncClass(Main2Activity.this.getApplication(), new AsyncClass.GetTrailer() {
                @Override
                public void sendAdapter(List<TrailerInfo> trailerInfos) {
                    listView.setAdapter(new Adapter(Main2Activity.this,trailerInfos));
                }
            },trailer).execute(urlTrailer);


                    String urlReviews = "https://api.themoviedb.org/3/movie/" + moveId + "/reviews?api_key=4d9c9de3bdf0d3b6837c49c086e3b190";
            new AsyncClass(Main2Activity.this.getApplication(), new AsyncClass.getMyReviews() {
                @Override
                public void sendReviews(List<Reviews> reviews) {
                    list_Reviews.setAdapter(new Adapter_Reviews(Main2Activity.this.getApplication(),reviews));

                }
            },"Reviews").execute(urlReviews);

        }


        bFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newmune,menu);
        return true;
    }
    private FavoriteList favoriteList;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.favorite){
            Intent intent=new Intent(Main2Activity.this,ShowFavoriteMovie.class);
            startActivity(intent);
            myViewModel.get_favorite().observe(this, new Observer<List<FavoriteList>>() {
                @Override
                public void onChanged(@Nullable List<FavoriteList> favoriteLists) {

                }});

        }

        return super.onOptionsItemSelected(item);
    }
    // this method to create alterDialog , and set this movie as favorite
    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
        RatingBar ratingBar=new RatingBar(this);
        LinearLayout linearLayout=new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        ratingBar.setLayoutParams(layoutParams);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(1);

        linearLayout.addView(ratingBar);
        builder.setMessage("Choice Rating");
        builder.setCancelable(true);
        builder.setView(linearLayout);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                final String value=String.valueOf(rating);
                favoriteList=new FavoriteList(name,moveId,value);
            }
        });

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        repositoryData=new RepositoryData(Main2Activity.this.getApplication());
                        repositoryData.InsertFavoriteMovie(favoriteList);
                        dialog.cancel();
                    }
                });
                builder.show();


    }




    class Adapter extends ArrayAdapter<TrailerInfo>{
        final List<TrailerInfo> arrayList;
        final Context context;
        Adapter(Context context, List<TrailerInfo> arrayList){
            super(context,R.layout.custom_trailer,arrayList);
            this.arrayList=arrayList;
            this.context=context;
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.custom_trailer,parent,false);
            TextView textView=view.findViewById(R.id.textTrailer);
            textView.setText(arrayList.get(position).getKey());
            ImageButton imageButton=view.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value=arrayList.get(position).getKey();
                    // to start webcast of movie
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v="+value)));
                }
            });
            return view;
        }
    }
    class Adapter_Reviews extends ArrayAdapter<Reviews> {
        final List<Reviews> all_reviews;
        final Context context;
        Adapter_Reviews(Context context, List<Reviews> reviews) {
            super(context,R.layout.reviews,reviews);
            this.context=context;
            this.all_reviews=reviews;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.reviews,parent,false);
            TextView textView=view.findViewById(R.id.textReview);
            textView.setText(all_reviews.get(position).getReviews());
            return view;

        }

    }

}