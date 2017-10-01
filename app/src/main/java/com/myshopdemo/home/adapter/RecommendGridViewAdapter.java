package com.myshopdemo.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myshopdemo.R;
import com.myshopdemo.home.bean.ResultBeanData;
import com.myshopdemo.utils.Constants;

import java.util.List;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/1 0001 15:55
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class RecommendGridViewAdapter extends BaseAdapter {
    private final Context                                               context;
    private final List<ResultBeanData.ResultEntity.RecommendInfoEntity> datas;

    public RecommendGridViewAdapter(Context context, List<ResultBeanData.ResultEntity.RecommendInfoEntity> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 在getView方法里面对convertView进行判空操作，对布局进行缓存，重用convertView,使用ViewHolder管理控件，避免控件多次被实例化，提高性能。
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //对convertView进行判空操作，复用布局，进行性能优化
        if(convertView==null){
            convertView=View.inflate(context, R.layout.item_recommend_grid_view,null);
            viewHolder=new ViewHolder();
            //实例化控件
            viewHolder.iv_recommend= (ImageView) convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price= (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //根据位置获取对应的数据
        ResultBeanData.ResultEntity.RecommendInfoEntity recommendInfoEntity = datas.get(position);
        //加载图片，使用Glide
        Glide.with(context).load(Constants.BASE_URL_IMAGE+recommendInfoEntity.getFigure()).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(recommendInfoEntity.getName());
        viewHolder.tv_price.setText("￥"+recommendInfoEntity.getCover_price());

        return convertView;
    }
    static class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
