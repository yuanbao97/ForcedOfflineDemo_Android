package com.example.forcedofflinedemo.framework;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forcedofflinedemo.R;
import com.example.forcedofflinedemo.Receiver.ForceOfflineReceiver;

public class BaseActivity extends AppCompatActivity {

    ForceOfflineReceiver receiver;

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

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("com.example.forcedofflinedemo.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }
}
