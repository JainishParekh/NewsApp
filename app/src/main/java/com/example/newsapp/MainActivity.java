package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdaptor.CategoryInterface {

//    002e7a8b8a9b4f5a99471e47a2ee1ac3
    private RecyclerView newsRV , categoryRV;
    private ProgressBar pb;
    private ArrayList<Articles> articleList;
    private ArrayList<CategoryModel> categoryList;
    private CategoryAdaptor ca;
    private NewsAdaptor na;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV = findViewById(R.id.idRVViews);
        categoryRV = findViewById(R.id.idRVCategories);
        pb = findViewById(R.id.idProgressbar);
        articleList = new ArrayList<>();
        categoryList = new ArrayList<>();
        na = new NewsAdaptor(articleList , this);
        ca = new CategoryAdaptor(categoryList , this::onCategoryClick , this);
        newsRV.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
        categoryRV.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        newsRV.setAdapter(na);
        categoryRV.setAdapter(ca);
        getCategories();
        getNews("All");
        na.notifyDataSetChanged();
    }

    public void getCategories(){
        categoryList.add(new CategoryModel("All" , "https://images.unsplash.com/photo-1529243856184-fd5465488984?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTl8fG5ld3NwYXBlcnxlbnwwfDB8MHx8&w=1000&q=80"));
        categoryList.add(new CategoryModel("Technology" , "https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80"));
        categoryList.add(new CategoryModel("Science" , "https://images.unsplash.com/photo-1507413245164-6160d8298b31?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c2NpZW5jZXxlbnwwfDB8MHx8&w=1000&q=80"));
        categoryList.add(new CategoryModel("General" , "https://images.unsplash.com/photo-1585829365295-ab7cd400c167?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bmV3c3xlbnwwfDB8MHx8&w=1000&q=80"));
        categoryList.add(new CategoryModel("Sports" , "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80"));
        categoryList.add(new CategoryModel("Business" , "https://images.unsplash.com/photo-1579532536935-619928decd08?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8bmV3c3BhcGVyfGVufDB8MHwwfHw%3D&w=1000&q=80\n"));
        categoryList.add(new CategoryModel("Entertainment" , "https://images.unsplash.com/photo-1514525253161-7a46d19cd819?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80"));
        categoryList.add(new CategoryModel("Health" , "https://images.unsplash.com/photo-1505751172876-fa1923c5c528?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8aGVhbHRofGVufDB8MHwwfHw%3D&w=1000&q=80"));

        ca.notifyDataSetChanged();
    }

    public void getNews(String Category){
        pb.setVisibility(View.VISIBLE);
        articleList.clear();
        String categoryUrl = "https://newsapi.org/v2/top-headlines?country=in&category="+ Category +"&apikey=002e7a8b8a9b4f5a99471e47a2ee1ac3";
        String allNews = "https://newsapi.org/v2/top-headlines?excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=002e7a8b8a9b4f5a99471e47a2ee1ac3";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroFitApi retroFitApi = retrofit.create(RetroFitApi.class);
        Call<NewsModel> call;
        if(Category.equals("All")){
            call = retroFitApi.getAllNews(allNews);
        }else{
            call = retroFitApi.getNewsByCategory(categoryUrl);
        }

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                pb.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModel.getArticles();
                for(int i = 0 ; i < articles.size() ; ++i){
                    articleList.add(new Articles(articles.get(i).getAuthor(),
                            articles.get(i).getTitle(),
                            articles.get(i).getDesc(),
                            articles.get(i).getUrl(),
                            articles.get(i).getUrlToImg(),
                            articles.get(i).getContent()
                            ));
                }
                na.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "FAILED TO GET NEWS", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryList.get(position).getCategory_name();
        getNews(category);
    }
}