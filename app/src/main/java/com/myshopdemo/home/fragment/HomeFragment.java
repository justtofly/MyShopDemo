package com.myshopdemo.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    //声明控件
    EditText     mEtHome;
    TextView     mTvHome;
    RecyclerView mRvHome;
    ImageView    mIvHome;

    @Override
    public View initView() {
        Log.e("TAG", "主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        //实例化控件
        mEtHome= (EditText) view.findViewById(R.id.et_home);
        mRvHome= (RecyclerView) view.findViewById(R.id.rv_home);
        mTvHome= (TextView) view.findViewById(R.id.tv_home);
        mIvHome= (ImageView) view.findViewById(R.id.iv_home);
        //设置点击监听
        initListener();
        return view;
    }

    private void initListener() {
        //置顶的监听
        mIvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
//                Toast.makeText(mContext,"回到顶部",Toast.LENGTH_SHORT).show();
                //RecyclerView回到顶部
                mRvHome.scrollToPosition(0);
            }
        });
        mEtHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"搜索",Toast.LENGTH_SHORT).show();
            }
        });
        mTvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }
}
