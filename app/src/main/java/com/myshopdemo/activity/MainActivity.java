package com.myshopdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @Bind(R.id.fl)
    FrameLayout mFl;
    @Bind(R.id.rb_main)
    RadioButton mRbMain;
    @Bind(R.id.rb_type)
    RadioButton mRbType;
    @Bind(R.id.rb_find)
    RadioButton mRbFind;
    @Bind(R.id.rb_cart)
    RadioButton mRbCart;
    @Bind(R.id.rb_center)
    RadioButton mRbCenter;
    @Bind(R.id.rg)
    RadioGroup  mRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
}
