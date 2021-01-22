package com.example.forcedofflinedemo.framework;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forcedofflinedemo.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }
}
