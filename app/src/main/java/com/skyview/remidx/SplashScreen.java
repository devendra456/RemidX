package com.skyview.remidx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.splash_screen);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                Boolean isloggedIn=sharedPreferences.getBoolean("islogin", false);
                if(isloggedIn){
                    startActivity(new Intent(SplashScreen.this,HomePage.class));
                }
                else {
                    startActivity(new Intent(SplashScreen.this,LoginActivity.class));
                }
                finish();
            }
        }, 3000);
    }
}