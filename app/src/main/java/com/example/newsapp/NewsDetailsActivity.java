package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {
    private TextView titleTV , descTV , contentTV;
    private ImageView imgIV;
    private Button btn;
    private String title , content , image , url , desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        desc = getIntent().getStringExtra("desc");
        image = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");

        titleTV = findViewById(R.id.detailTitle);
        titleTV.setText(title);
        descTV = findViewById(R.id.detailDesc);
        descTV.setText(desc);
        contentTV = findViewById(R.id.detailContent);
        contentTV.setText(content);
        imgIV = findViewById(R.id.detailImage);
        Picasso.get().load(image).into(imgIV);
        btn = findViewById(R.id.detailButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}