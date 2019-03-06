package com.example.eltaysser.mymovie_part2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eltaysser.mymovie_part2.dataBase.Top_Rated;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterView extends RecyclerView.Adapter<AdapterView.HoldMyView> {


    private final List<LayoutContent> arrayList;
    private final Context context;
    private final OnItemClickListener mOnItemClickListener;

    public AdapterView(List<LayoutContent> arrayList, Context context,OnItemClickListener mOnItemClickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.mOnItemClickListener=mOnItemClickListener;
    }

    @Override
    public HoldMyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view=layoutInflater.inflate(R.layout.showmy_items,viewGroup,false);
        return new HoldMyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoldMyView holdMyView, final int position) {
        Picasso.get().load(ConstantValue.path+arrayList.get(position).getImageUrlForPicasso()).into(holdMyView.imageView);
        holdMyView.tName.setText(arrayList.get(position).getMovieName());
        holdMyView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }

    class HoldMyView extends RecyclerView.ViewHolder{
    final ImageView imageView;
    final TextView tName;
    HoldMyView(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageView);
        tName=itemView.findViewById(R.id.moviename);
    }
}

    public interface OnItemClickListener {
         void onItemClick(int position);
    }

}