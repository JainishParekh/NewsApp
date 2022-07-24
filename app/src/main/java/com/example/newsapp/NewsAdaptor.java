package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdaptor extends RecyclerView.Adapter<NewsAdaptor.ViewHolder> {
    private ArrayList<Articles> articles;
    private Context context;

    public NewsAdaptor(ArrayList<Articles> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_news_rv_item , parent,false);
        return new NewsAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdaptor.ViewHolder holder, int position) {
        Articles article = articles.get(position);
        holder.subTitleTV.setText(article.getDesc());
        holder.titleTV.setText(article.getTitle());
        Picasso.get().load(article.getUrlToImg()).into(holder.newsIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context , NewsDetailsActivity.class);
                i.putExtra("title" , article.getTitle());
                i.putExtra("content" , article.getContent());
                i.putExtra("desc" , article.getDesc());
                i.putExtra("image" , article.getUrlToImg());
                i.putExtra("url" , article.getUrl());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTV,subTitleTV;
        private ImageView newsIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVNewsHeading);
            subTitleTV = itemView.findViewById(R.id.idTVSubTitles);
            newsIV = itemView.findViewById(R.id.idIVNews);
        }
    }
}
