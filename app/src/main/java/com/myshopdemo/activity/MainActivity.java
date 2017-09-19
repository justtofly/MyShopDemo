package com.myshopdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myshopdemo.base.BaseFragment;
import com.myshopdemo.center.fragment.CenterFragment;
import com.myshopdemo.find.fragment.FindFragment;
import com.myshopdemo.home.fragment.HomeFragment;
import com.myshopdemo.shoppingcart.fragment.ShoppingcartFragment;
import com.myshopdemo.type.fragment.TypeFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
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

    /**
     * 定义装多个Fragment的实例集合
     */
    private ArrayList<BaseFragment> mFragments;
    /**
     * 取的位置，默认是0
     */
    private int position = 0;
    /**
     * 缓存的Fragment或者上次显示的Fragment
     */
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife和当前Activity绑定
        ButterKnife.bind(this);
        /**
         * 初始化Fragment
         */
        initFragment();
        //        mRg.check(R.id.rb_main);
        initListener();
    }

    /**
     * 设置RadioGroup的切换改变监听
     */
    private void initListener() {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_find:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_center:
                        position = 4;
                        break;
                    default:
                        position = 1;
                        break;
                }
                //根据位置取不同的fragment
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragment, baseFragment);
            }
        });
        mRg.check(R.id.rb_main);
    }

    /**
     * 添加每个Fragment页面到集合，要按顺序
     */
    private void initFragment() {
        //创建集合
        mFragments=new ArrayList<>();
        mFragments.add(new HomeFragment());//主页
        mFragments.add(new TypeFragment());//分类
        mFragments.add(new FindFragment());//发现
        mFragments.add(new ShoppingcartFragment());//购物车
        mFragments.add(new CenterFragment());//用户中心
    }

    /**
     * 根据不同的位置，取不同的fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position){
        if(mFragments!=null&&mFragments.size()>0){
            BaseFragment baseFragment=mFragments.get(position);
            return baseFragment;
        }
        return null;
    }
    private void switchFragment(Fragment fromFragment,BaseFragment nextFragment){
        if (tempFragment!=nextFragment){
            tempFragment=nextFragment;
            if (nextFragment!=null){
                FragmentTransaction transation=getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if(!nextFragment.isAdded()){
                    //隐藏当前的Fragment,添加下一个Fragment,并显示下一个Fragment
                    if(fromFragment!=null){
                        transation.hide(fromFragment);
                    }
                    transation.add(R.id.fl,nextFragment).commit();
                }else {
                    //隐藏当前的Fragment
                    if(fromFragment!=null){
                        transation.hide(fromFragment);
                    }
                    transation.show(nextFragment).commit();
                }
            }
        }
    }
}
