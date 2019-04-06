package com.example.news;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginResultActivity extends AppCompatActivity {

    TextView TextView_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView_get = findViewById(R.id.TextView_get);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        TextView_get.setText(email + " / " + password);
    }
}
