package com.myshopdemo.home.fragment;

import android.util.Log;
import android.view.View;

import com.myshopdemo.activity.R;
import com.myshopdemo.base.BaseFragment;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/9/19 0019 9:17
 * 作用	      主页面
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class HomeFragment extends BaseFragment {

    @Override
    public View initView() {
        Log.e("TAG", "主页面的Fragment的UI被初始化了");
        View view=View.inflate(mContext, R.layout.fragment_home,null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
