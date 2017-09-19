package com.myshopdemo.type.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.myshopdemo.base.BaseFragment;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/9/19 0019 9:17
 * 作用	      分类页面
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class TypeFragment extends BaseFragment {
    TextView textView;

    @Override
    public View initView() {
        Log.e("TAG", "分类页面的Fragment的UI被初始化了");
        textView=new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("分类页面内容");
    }
}
