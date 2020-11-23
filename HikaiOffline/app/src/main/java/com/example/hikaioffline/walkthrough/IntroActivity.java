package com.example.hikaioffline.walkthrough;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.hikaioffline.MainActivity;
import com.example.hikaioffline.R;
import com.example.hikaioffline.login.LoginActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ViewPager screenPager;
    TabLayout tabIndicator;
    IntroViewPagerAdapter introViewPagerAdapter;
    Button btnNext,btnGetStarted;
    int position=0;

    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //Making the activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //when this activity is about to launch, check if it has been opened before

        if(restorePrefsData()){
            Intent mainActivity=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(mainActivity);
            finish();
        }

        setContentView(R.layout.activity_intro);

        //Hiding the Action Bar
        //getSupportActionBar().hide();

        btnNext=(Button)findViewById(R.id.btn_next);
        btnGetStarted=(Button)findViewById(R.id.btn_getstarted) ;
        tabIndicator=(TabLayout)findViewById(R.id.tab_indicator);
        btnAnim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);



        //fill list screen
        final List<ScreenItem> mList=new ArrayList<>();
        mList.add(new ScreenItem("Learn from experts of the preffered subjects",
                "",
                R.raw.learn));
        mList.add(new ScreenItem("Learn whenever you want and from anywhere",
                "",
                R.raw.clock));
        mList.add(new ScreenItem("Contents available offline",
                " ",
                R.raw.download));

        //setup ViewPager
        screenPager=findViewById(R.id.screen_viewpager);
        introViewPagerAdapter=new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setup layout with view pager

        tabIndicator.setupWithViewPager(screenPager);

        //next button click listner
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position=screenPager.getCurrentItem();
                if(position<mList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }
                //when we reach the end of the screen
                if(position==mList.size()-1){
                    //TODO: show the generated button and hide the next button and the indicator
                    loadLastScreen();
                }
            }
        });

        //tablayout add change listner

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==mList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //getStarted button listner
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open main activity
                Intent registerActivity=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(registerActivity);

                //save the boolean value storage for next time opening the app
                savePrefsData();
                finish();
            }
        });

    }

    private boolean restorePrefsData(){
        SharedPreferences prefs=getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore=prefs.getBoolean("isIntroOpened",false);
        return isIntroActivityOpenedBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref=getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean("isIntroOpened",true);
        editor.commit();
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        //TODO: Add animation to generated button
        //button animation
        btnGetStarted.setAnimation(btnAnim);

    }

}