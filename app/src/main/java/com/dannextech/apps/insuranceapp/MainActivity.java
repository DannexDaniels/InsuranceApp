package com.dannextech.apps.insuranceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MAIN ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createAccount(View v){
        startActivity(new Intent(MainActivity.this, CreateAccount.class));
    }
    public void login(View v){
        startActivity(new Intent(MainActivity.this,LoginUser.class));
    }
}
