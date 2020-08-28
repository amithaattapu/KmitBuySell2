package com.example.kmitbuysell;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.BottomNavigationView;
//import android.support.v7.app.AppCompatActivity;
//import android.support.annotation.NonNull;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //ImageView imageView;
    //private TextView mTextMessage;

    //    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.navigation_Ads:
//                    mTextMessage.setText(R.string.title_ads);
//                    return true;
//                case R.id.navigation_chats:
//                    mTextMessage.setText(R.string.title_chats);
//                    return true;
//                case R.id.navigation_wishlist:
//                    mTextMessage.setText(R.string.title_WishList);
//                    return true;
//            }
//            return false;
//        }
//    };
    private static  int SPLASH_TIME_OUT=3000;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // BottomNavigationView navView = findViewById(R.id.nav_view);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(homeIntent);
            }
        },SPLASH_TIME_OUT);
        // progressBar=findViewById(R.id.progressBarHorizontal);

    }



}
