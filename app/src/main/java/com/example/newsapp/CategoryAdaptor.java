package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {

    private ArrayList<CategoryModel> category;
    private Context context;
    private CategoryInterface categoryInterface;

    public CategoryAdaptor(ArrayList<CategoryModel> category, CategoryInterface ci,Context context) {
        this.category = category;
        this.context = context;
        this.categoryInterface = ci;
    }

    public ArrayList<CategoryModel> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<CategoryModel> category) {
        this.category = category;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_catagories_item , parent,false);
        return new CategoryAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryModel cm = category.get(position);
        holder.tv.setText(cm.getCategory_name());
        Picasso.get().load(cm.getImgSrc()).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryInterface.onCategoryClick(position);
            }
        });
    }

    /**
     * This interface is created as on clicking the category we change the feed  present inside the main activity
     * so this interface will be implemented in the main activity .
     */
    public interface CategoryInterface {
        void onCategoryClick(int position);
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.idIVCategory);
            tv = itemView.findViewById(R.id.idTVCategory);
        }
    }
}
