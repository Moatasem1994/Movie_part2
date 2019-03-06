package com.example.eltaysser.mymovie_part2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eltaysser.mymovie_part2.dataBase.Top_Rated;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopRated_List_Adapter extends RecyclerView.Adapter<TopRated_List_Adapter.Holder> {
    List<Top_Rated>top_rateds;
    Context context;
    OnItemClickListener onItemClickListener;

    public TopRated_List_Adapter(List<Top_Rated> top_rateds, Context context, OnItemClickListener onItemClickListener) {
        this.top_rateds = top_rateds;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.showmy_items,viewGroup,false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.tName.setText(top_rateds.get(position).getMovieName());
        Picasso.get().load(ConstantValue.path+top_rateds.get(position).getImageUrlForPicasso()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return top_rateds.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        final ImageView imageView;
        final TextView tName;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            tName=itemView.findViewById(R.id.moviename);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
