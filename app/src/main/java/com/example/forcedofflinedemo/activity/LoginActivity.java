package com.example.forcedofflinedemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

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
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.loginLayout)
    ConstraintLayout loginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.button, R.id.loginLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                String account = accountEditView.getText().toString();
                String password = passwordEditView.getText().toString();
                if(account.equals("123456") && password.equals("123456")) {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.loginLayout:
                accountEditView.clearFocus();
                passwordEditView.clearFocus();
                break;
        }
    }

    //时间分发方法重写
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //如果是点击事件，获取点击的view，并判断是否要收起键盘
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //获取目前得到焦点的view
            View v = getCurrentFocus();
            //判断是否要收起并进行处理
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        //这个是activity的事件分发，一定要有，不然就不会有任何的点击事件了
        return super.dispatchTouchEvent(ev);
    }

    //判断是否要收起键盘
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        //如果目前得到焦点的这个view是editText的话进行判断点击的位置
        if (v instanceof EditText) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            // 点击EditText的事件，忽略它。
            return !(event.getX() > left) || !(event.getX() < right)
                    || !(event.getY() > top) || !(event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上
        return false;
    }

    //隐藏软键盘并让editText失去焦点
    private void hideKeyboard(IBinder token) {
        accountEditView.clearFocus();
        passwordEditView.clearFocus();
        if (token != null) {
            //这里先获取InputMethodManager再调用他的方法来关闭软键盘
            //InputMethodManager就是一个管理窗口输入的manager
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}