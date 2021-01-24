package com.example.forcedofflinedemo.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.forcedofflinedemo.R;
import com.example.forcedofflinedemo.framework.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.sendBroadcastButton)
    Button sendBroadcastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sendBroadcastButton)
    public void onClick() {
        Intent intent = new Intent("com.example.forcedofflinedemo.FORCE_OFFLINE");
        sendBroadcast(intent);
    }
}