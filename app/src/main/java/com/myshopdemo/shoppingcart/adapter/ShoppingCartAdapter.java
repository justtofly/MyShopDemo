package com.myshopdemo.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myshopdemo.R;
import com.myshopdemo.home.bean.GoodsBean;
import com.myshopdemo.shoppingcart.utils.CartStorage;
import com.myshopdemo.shoppingcart.view.AddSubView;
import com.myshopdemo.utils.Constants;

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
    private final List<GoodsBean> datas;
    /**
     * 商品总价格
     */
    private final TextView        tvShopcartTotal;
    /**
     * 完成状态下全选选择框
     */
    private final CheckBox        cbAll;
    /**
     * 编辑状态下全选选择框
     */
    private       CheckBox        checkboxAll;

    public ShoppingCartAdapter(Context context, List<GoodsBean> goodsBeanList, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        this.mContext = context;
        this.datas = goodsBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll = cbAll;

        //显示所有价格
        showTotalPrice();
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("合计：" + getTotalPrice());
    }

    /**
     * 计算总价格
     *
     * @return
     */
    private double getTotalPrice() {
        double totalPrice = 0.0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isSelected()) {
                    totalPrice = totalPrice + Double.valueOf(goodsBean.getNumber()) * Double.valueOf(goodsBean.getCover_price());
                }
            }
        }
        return totalPrice;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //1.根据位置得到对应的bean对象
        final GoodsBean goodsBean = datas.get(position);
        //强转过后相当于得到它的实例
        ViewHolder viewHolder = (ViewHolder) holder;
        //2.设置数据
        viewHolder.cb_gov.setChecked(goodsBean.isSelected());
        //加载图片数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(viewHolder.iv_gov);
        //设置描述信息
        viewHolder.tv_desc_gov.setText(goodsBean.getName());
        viewHolder.tv_price_gov.setText("人民币：" + goodsBean.getCover_price());

        viewHolder.numberAddSubView.setValue(goodsBean.getNumber());
        viewHolder.numberAddSubView.setMaxValue(10);
        viewHolder.numberAddSubView.setMinValue(1);

        //注册商品数量监听
        viewHolder.numberAddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int value) {
                //1.保存在当前列表内存中
                goodsBean.setNumber(value);
                //2.本地要更新
                CartStorage.getInstance().updateData(goodsBean);
                //3.刷新适配器,点哪一条就刷新哪一个
                notifyItemChanged(position);
                //4.再次计算总价格
                showTotalPrice();

                //                //设置点击事件
                //                setListener();
                //
                //                //检验是否全选
                //                checkAll();
            }
        });

        //设置点击事件
        setListener();

        //检验是否全选
        checkAll();
    }

    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                //1.根据位置得到对应的Bean对象
                GoodsBean goodsBean = datas.get(position);
                //2.设置取反状态
                goodsBean.setSelected(!goodsBean.isSelected());

                //3.刷新状态
                notifyItemChanged(position);

                //4.重新计算总价格
                showTotalPrice();
            }
        });
        //设置CheckBox的点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Toast.makeText(mContext,"全选和非全选",Toast.LENGTH_SHORT).show();
                //1.得到状态,遍历购物车所有商品,当isCheck为true,就设置全选，如果isCheck为false，就全不选
                boolean isCheck = checkboxAll.isChecked();
                //2.根据状态设置全选和非全选
                checkAll_none(isCheck);
                //3.计算总价格
                showTotalPrice();
            }
        });
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Toast.makeText(mContext,"全选和非全选",Toast.LENGTH_SHORT).show();
                //1.得到状态,遍历购物车所有商品,当isCheck为true,就设置全选，如果isCheck为false，就全不选
                boolean isCheck = cbAll.isChecked();
                //2.根据状态设置全选和非全选
                checkAll_none(isCheck);
                //3.计算总价格
                showTotalPrice();
            }
        });
    }

    /**
     * 设置全选和非全选
     *
     * @param isCheck
     */
    public void checkAll_none(boolean isCheck) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                goodsBean.setSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }


    public void checkAll() {
        if (datas != null && datas.size() > 0) {
            //number初始值为0
            int number = 0;
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (!goodsBean.isSelected()) {
                    //非全选
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                } else {
                    //选中的
                    number++;
                    //                    Log.e("TAG",""+number);
                }
                if (number == datas.size()) {
                    //全选
                    checkboxAll.setChecked(true);
                    cbAll.setChecked(true);
                    //                    Log.e("TAG","number == datas.size()"+datas.size());
                }
            }
        } else {
            //假如没有数据
            checkboxAll.setChecked(false);
            cbAll.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void deleteData() {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                //删除选中
                if (goodsBean.isSelected()) {
                    //内存中移除
                    datas.remove(goodsBean);
                    //保存在本地
                    CartStorage.getInstance().deleteData(goodsBean);
                    //刷新
//                    notifyItemChanged(i);//出现bug,点击删除，闪退
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //声明控件对象
        private CheckBox   cb_gov;
        private ImageView  iv_gov;
        private TextView   tv_desc_gov;
        private TextView   tv_price_gov;
        private AddSubView numberAddSubView;

        public ViewHolder(View itemView) {
            super(itemView);

            //实例化控件
            cb_gov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            numberAddSubView = (AddSubView) itemView.findViewById(R.id.numberAddSubView);

            //设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 点击item的监听
     */
    public interface OnItemClickListener {
        /**
         * 当点击某个条目的时候回调
         *
         * @param position
         */
        public void onItemClick(int position);
    }

    //创建接口对象
    private OnItemClickListener mOnItemClickListener;

    /**
     * 设置item的监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
