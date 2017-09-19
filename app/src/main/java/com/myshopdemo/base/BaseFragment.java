package com.myshopdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/9/19 0019 9:05
 * 作用	     基类BaseFragment
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public abstract class BaseFragment extends Fragment {
    public Context mContext;
    //当该类被系统创建的时候回调
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //得到一个上下文，因为孩子要用，如果不写，会导致无法显示各个fragment
        mContext=getActivity();
    }

    /**
     * 当视图被创建的时候回调这个方法，负责UI的创建
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    public abstract View initView() ;

    /**
     * 当Activity被创建的时候回调这个方法，负责得到数据，显示UI,这个方法易于onCreateView执行，所以先有View,后得数据，才不会有空指针
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要联网请求数据的时候，可以重写该方法,在该方法中联网请求数据
     */
    public void initData(){

    }
}
