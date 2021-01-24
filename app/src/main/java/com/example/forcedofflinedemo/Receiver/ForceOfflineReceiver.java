package com.example.forcedofflinedemo.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.example.forcedofflinedemo.activity.LoginActivity;
import com.example.forcedofflinedemo.framework.ActivityController;

public class ForceOfflineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("警告");
        builder.setMessage("您的账号异地登陆。点击确认，重新登录");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityController.finishAll();
                Intent intent1 = new Intent(context, LoginActivity.class);
                context.startActivity(intent1);
            }
        });
        builder.show();
    }
}
