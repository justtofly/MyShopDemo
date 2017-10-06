package com.myshopdemo.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.myshopdemo.R;
import com.myshopdemo.home.bean.GoodsBean;
import com.myshopdemo.shoppingcart.view.AddSubView;

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
        View itemView=View.inflate(mContext, R.layout.item_shop_cart,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return goodsBeanList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        //声明控件对象
        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView numberAddSubView;

        public ViewHolder(View itemView) {
            super(itemView);

            //实例化控件
            cb_gov= (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov= (ImageView) itemView.findViewById(R.id.iv_gov);
            tv_desc_gov= (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov= (TextView) itemView.findViewById(R.id.tv_price_gov);
            numberAddSubView= (AddSubView) itemView.findViewById(R.id.numberAddSubView);

        }
    }
}
