package com.example.forcedofflinedemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcedofflinedemo.R;
import com.example.forcedofflinedemo.framework.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.accountEditView)
    EditText accountEditView;
    @BindView(R.id.passwordEditView)
    EditText passwordEditView;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.rememberCheckBox)
    CheckBox rememberCheckBox;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        boolean isRemember = sharedPreferences.getBoolean("isRemember", false);
        String account = sharedPreferences.getString("account", "");
        String password = sharedPreferences.getString("password", "");
        accountEditView.setText(account);
        if(isRemember) {
            rememberCheckBox.setChecked(true);
            passwordEditView.setText(password);
        }
    }

    @Override
    public void clearFocus() {
        accountEditView.clearFocus();
        passwordEditView.clearFocus();
    }


    @OnClick({R.id.loginButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                String account = accountEditView.getText().toString();
                String password = passwordEditView.getText().toString();
                if (account.equals("123456") && password.equals("123456")) {
                    editor.putString("account", account);
                    if(rememberCheckBox.isChecked()) {
                        editor.putBoolean("isRemember", true);
                        editor.putString("password", password);
                    } else {
                        editor.remove("isRemember");
                        editor.remove("password");
                    }
                    editor.apply();
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}