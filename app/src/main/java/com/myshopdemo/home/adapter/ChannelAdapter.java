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
 * 创建时间   2017/9/23 0023 20:29
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class ChannelAdapter extends BaseAdapter {
    private final Context                                             mContext;
    private final List<ResultBeanData.ResultEntity.ChannelInfoEntity> datas;

    public ChannelAdapter(Context context, List<ResultBeanData.ResultEntity.ChannelInfoEntity> channel_info) {
        this.mContext = context;
        this.datas = channel_info;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=View.inflate(mContext, R.layout.item_channel,null);
            viewHolder=new ViewHolder();
            viewHolder.mImageView= (ImageView) convertView.findViewById(R.id.iv_channel);
            viewHolder.mTextView= (TextView) convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        ResultBeanData.ResultEntity.ChannelInfoEntity channelInfoEntity = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+channelInfoEntity.getImage()).into(viewHolder.mImageView);
        return convertView;
    }
    static class ViewHolder{
        ImageView mImageView;
        TextView mTextView;
    }
}
