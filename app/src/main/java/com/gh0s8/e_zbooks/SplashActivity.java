package com.gh0s8.e_zbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_EZbooks_FullScreen);
        setContentView(R.layout.activity_splash);



        ImageView spLogo = findViewById(R.id.splash_logo);

        Glide.with(this)
                .load(R.drawable.books)
                .override(300,300)
                .into(spLogo);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.progressBar2).setVisibility(View.VISIBLE);
            }
        },5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.progressBar2).setVisibility(View.INVISIBLE);
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },10000);

    }
}