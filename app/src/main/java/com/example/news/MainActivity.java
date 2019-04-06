package com.example.news;


import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    TextInputEditText TextInputEditText_email, TextInputEditText_password;
    LinearLayout LinearLayout_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText_email = findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password = findViewById(R.id.TextInputEditText_password);
        LinearLayout_login = findViewById(R.id.LinearLayout_login);

        //1.값을 가져온다

        LinearLayout_login.setClickable(true);

        //2.클릭 이벤트
        LinearLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = TextInputEditText_email.getText().toString();
                String password = TextInputEditText_password.getText().toString();

//                Intent intent = new Intent(MainActivity.this, LoginResultActivity.class);
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);

                startActivity(intent);
            }
        });

        //3.가져온 값을 다음 액티비티로 넘긴다
    }
}
