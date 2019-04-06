package com.example.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;

public class NewOneActivity extends AppCompatActivity {

    public TextView TextView_title;
    public TextView TextView_content;
    public SimpleDraweeView ImageView_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);

        TextView_title = findViewById(R.id.TextView_title);
        TextView_content = findViewById(R.id.TextView_content);
        ImageView_content = findViewById(R.id.ImageView_content);

        Intent intent = getIntent();
        NewsData newsData = (NewsData)intent.getSerializableExtra("serializable_extra");

        TextView_title.setText(newsData.getTitle());
        TextView_content.setText(newsData.getContent());
        ImageView_content.setImageURI(newsData.getUrlToImage());
    }
}
