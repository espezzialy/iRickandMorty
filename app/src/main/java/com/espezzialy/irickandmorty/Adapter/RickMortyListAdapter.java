package com.espezzialy.irickandmorty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.espezzialy.irickandmorty.Model.Result;
import com.espezzialy.irickandmorty.R;

import java.util.List;

public class RickMortyListAdapter extends RecyclerView.Adapter<RickMortyListAdapter.MyViewHolder> {

    Context context;
    List<Result> resultList;

    public RickMortyListAdapter(Context context, List<Result> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rickmorty_list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Carregamento de Imagens
        Glide.with(context).load(resultList.get(position).getImage()).into(holder.image);

        //Carregamento de nomes
        holder.name.setText(resultList.get(position).getName() );
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
