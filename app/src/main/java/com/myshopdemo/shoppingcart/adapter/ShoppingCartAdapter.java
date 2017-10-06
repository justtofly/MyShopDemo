package com.myshopdemo.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.myshopdemo.home.bean.GoodsBean;

import java.util.List;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/6 0006 10:47
 * 作用	      购物车商品RecyclerView的适配器
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter {
    private final Context         mContext;
    private final List<GoodsBean> goodsBeanList;

    public ShoppingCartAdapter(Context context, List<GoodsBean> goodsBeanList) {
        this.mContext = context;
        this.goodsBeanList = goodsBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return goodsBeanList.size();
    }
}
