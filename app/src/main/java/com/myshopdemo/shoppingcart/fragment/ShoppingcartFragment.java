package com.myshopdemo.shoppingcart.fragment;

import android.util.Log;
import android.view.View;

import com.myshopdemo.R;
import com.myshopdemo.base.BaseFragment;
import com.myshopdemo.home.bean.GoodsBean;
import com.myshopdemo.shoppingcart.utils.CartStorage;

import java.util.List;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/9/19 0019 9:17
 * 作用	      购物车页面
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class ShoppingcartFragment extends BaseFragment {
//    TextView textView;

    @Override
    public View initView() {
        Log.e("TAG", "购物车页面的Fragment的UI被初始化了");
//        textView=new TextView(mContext);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextSize(25);

        View view=View.inflate(mContext, R.layout.fragment_shopping_cart,null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
//        textView.setText("购物车页面内容");

        List<GoodsBean> goodsBeanList= CartStorage.getInstance().getAllData();
        for (int i = 0; i < goodsBeanList.size(); i++) {
            Log.e("TAG","ShoppingcartFragment:获取到的存储的商品数据=="+goodsBeanList.get(i).toString());
        }
    }
}
