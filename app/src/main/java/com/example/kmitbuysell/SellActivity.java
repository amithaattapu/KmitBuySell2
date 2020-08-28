package com.example.kmitbuysell;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SellActivity extends AppCompatActivity implements SellFragment.Myinter{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
    }
    @Override
    public void onClick(int id)
    {
        if(id==0)
        {
           Intent intent=new Intent(SellActivity.this,BookSellActivity.class);
           startActivity(intent);
        }
    }
}
