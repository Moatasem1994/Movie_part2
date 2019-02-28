package com.example.eltaysser.mymovie_part2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.eltaysser.mymovie_part2.dataBase.FavoriteList;

import java.util.List;

public class AdapterFavoriteMovie extends RecyclerView.Adapter<AdapterFavoriteMovie.HoldFavoriteItem> {
 private final List<FavoriteList>favoriteLists;
private final Context context;

    public AdapterFavoriteMovie(List<FavoriteList> favoriteLists, Context context) {
        this.favoriteLists = favoriteLists;
        this.context = context;
    }

    @NonNull
    @Override
    public HoldFavoriteItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.custom_recycelview_favorite,viewGroup,false);
        return new HoldFavoriteItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoldFavoriteItem holdFavoriteItem, int position) {
        holdFavoriteItem.textName.setText(favoriteLists.get(position).getMovieName());
        holdFavoriteItem.ratingBar.setRating(Float.parseFloat(favoriteLists.get(position).getMyRate()));
        holdFavoriteItem.ratingBar.setIsIndicator(true);
    }

    @Override
    public int getItemCount() {
        return favoriteLists.size();
    }

    public class HoldFavoriteItem extends RecyclerView.ViewHolder{
          final TextView textName;
          final RatingBar ratingBar;
        HoldFavoriteItem(@NonNull View itemView) {
            super(itemView);
            textName=itemView.findViewById(R.id.MovieName_Favorite);
            ratingBar=itemView.findViewById(R.id.ratingBar_Favorite);
        }
    }
}
