package com.example.hikaioffline.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.hikaioffline.MainActivity;
import com.example.hikaioffline.R;
import com.example.hikaioffline.login.SharedPrefManager;
import com.example.hikaioffline.walkthrough.IntroActivity;

public class SplashActivity extends AppCompatActivity {
    private static int Splash_screen = 3200;

    Animation topAnim,bottomAnim;
    ImageView image;
    TextView txtDesc,txtHikai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //Making the activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


//        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
//        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        txtHikai=findViewById(R.id.txtHikai);
        image=findViewById(R.id.logo);
        txtDesc=findViewById(R.id.txtDesc);


        txtHikai.setAnimation(topAnim);
        image.setAnimation(topAnim);
        txtDesc.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
           @Override
            public void run() {
               Intent intent=new Intent(SplashActivity.this, IntroActivity.class);
               startActivity(intent);
               finish();
                }
            },Splash_screen);


    }
    public void login(){
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent=new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent=new Intent(SplashActivity.this, IntroActivity.class);
            startActivity(intent);
            finish();
        }
    }

}