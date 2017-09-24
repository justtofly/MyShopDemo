package com.myshopdemo.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myshopdemo.R;
import com.myshopdemo.home.bean.ResultBeanData;
import com.myshopdemo.utils.Constants;

import java.util.List;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/9/24 0024 8:57
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.MyViewHolder> {
    private final Context                                                        mContext;
    private final List<ResultBeanData.ResultEntity.SeckillInfoEntity.ListEntity> list;

    public SeckillRecyclerViewAdapter(Context context, List<ResultBeanData.ResultEntity.SeckillInfoEntity.ListEntity> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=View.inflate(mContext, R.layout.item_seckill,null);
//        return new MyViewHolder(itemView);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //1.根据位置得到对应的数据
        ResultBeanData.ResultEntity.SeckillInfoEntity.ListEntity listEntity = list.get(position);

        //2.绑定数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listEntity.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listEntity.getCover_price());
        holder.tv_origin_price.setText(listEntity.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_figure= (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price= (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price= (TextView) itemView.findViewById(R.id.tv_origin_price);

            //设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext,"秒杀="+getLayoutPosition(),Toast.LENGTH_SHORT).show();//这一条可以实现点击事件
                    if (mOnSeckillRecyclerView!=null){
                        mOnSeckillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }

    }
    public interface OnSeckillRecyclerView{
        /**
         * 当某条被点击的时候调用
         * @param position
         */
        public void onItemClick(int position);
    }
    private OnSeckillRecyclerView mOnSeckillRecyclerView;

    /**
     * 设置item的监听
     * @param onSeckillRecyclerView
     */
    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView){
        this.mOnSeckillRecyclerView=onSeckillRecyclerView;
    }
}
