package com.myshopdemo.shoppingcart.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
public class ShoppingcartFragment extends BaseFragment implements View.OnClickListener {
    //    TextView textView;
    private TextView     rlEditShopcart;
    private LinearLayout llCheckAll;
    private CheckBox     checkboxAll;
    private TextView     tvShopcartTotal;
    private Button       btnShopcartCheckOut;
    private LinearLayout llDelete;
    private CheckBox     cbAll;
    private Button       btnDelete;
    private Button       btnCollection;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-06 10:22:01 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        rlEditShopcart = (TextView) view.findViewById(R.id.rl_edit_shopcart);
        llCheckAll = (LinearLayout) view.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnShopcartCheckOut = (Button)view.findViewById(R.id.btn_shopcart_check_out);
        llDelete = (LinearLayout)view.findViewById(R.id.ll_delete);
        cbAll = (CheckBox)view.findViewById(R.id.cb_all);
        btnDelete = (Button)view.findViewById(R.id.btn_delete);
        btnCollection = (Button)view.findViewById(R.id.btn_collection);

        btnShopcartCheckOut.setOnClickListener( this );
        btnDelete.setOnClickListener( this );
        btnCollection.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-10-06 10:22:01 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == btnShopcartCheckOut ) {//去结算
            Toast.makeText(mContext,"去结算",Toast.LENGTH_SHORT).show();
        } else if ( v == btnDelete ) {//删除
            Toast.makeText(mContext,"删除",Toast.LENGTH_SHORT).show();
            // Handle clicks for btnDelete
        } else if ( v == btnCollection ) {//收藏
            Toast.makeText(mContext,"收藏",Toast.LENGTH_SHORT).show();
            // Handle clicks for btnCollection
        }else if ( v == rlEditShopcart ) {//编辑
            Toast.makeText(mContext,"编辑",Toast.LENGTH_SHORT).show();
            // Handle clicks for btnCollection
        }
    }


    @Override
    public View initView() {
        Log.e("TAG", "购物车页面的Fragment的UI被初始化了");
//        textView=new TextView(mContext);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextSize(25);

        View view=View.inflate(mContext, R.layout.fragment_shopping_cart,null);
        findViews(view);

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
