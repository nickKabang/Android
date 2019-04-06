package com.example.news;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] myDataset = {"1","2"};
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        queue = Volley.newRequestQueue(this);
        getNews();
    }

    public void getNews()
    {
        // Instantiate the RequestQueue.
        String url ="https://newsapi.org/v2/top-headlines?country=kr&apiKey=5a2b5de6a27d4743b996fba94cd6dad7";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // specify an adapter (see also next example)

                        try {
                            JSONObject jsonObj = new JSONObject(response);

                            JSONArray arrayAtricles =  jsonObj.getJSONArray("articles");

                            List<NewsData> news = new ArrayList<>();
                            NewsData newsData;

                            for(int i=0; i<arrayAtricles.length(); i++)
                            {
                                JSONObject obj = arrayAtricles.getJSONObject(i);

                                //response -> NewsData 분류
                                newsData = new NewsData();
                                newsData.setTitle(obj.getString("title"));
                                newsData.setUrlToImage(obj.getString("urlToImage"));
                                newsData.setContent(obj.getString("description"));

                                news.add(newsData);
                            }



                            mAdapter = new MyAdapter(news, NewsActivity.this, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(v.getTag() != null)
                                    {
                                        int position = (int)v.getTag();
                                        NewsData newsOneData = ((MyAdapter)mAdapter).getNewsOne(position);

                                        Intent intent = new Intent(NewsActivity.this, NewOneActivity.class);
                                        intent.putExtra("serializable_extra", newsOneData);

                                        startActivity(intent);

                                    }
                                }
                            });
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
