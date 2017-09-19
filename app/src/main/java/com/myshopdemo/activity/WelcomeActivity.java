package com.myshopdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.myshopdemo.R;

/**
 * 欢迎界面
 * 功能：延时两秒进入到主页面
 */
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //两秒进入主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //在主线程中执行
                //启动主页面
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                //关闭当前页面
                finish();
            }
        },2000);
    }
}
