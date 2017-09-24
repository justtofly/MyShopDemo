package com.myshopdemo.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myshopdemo.home.bean.ResultBeanData;
import com.myshopdemo.utils.Constants;

import java.util.List;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/9/24 0024 8:00
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class ActAdapter extends PagerAdapter {
    private final Context mContext;
    private List<ResultBeanData.ResultEntity.ActInfoEntity> datas;
    public ActAdapter(Context context,List<ResultBeanData.ResultEntity.ActInfoEntity> act_info) {
        this.mContext=context;
        this.datas=act_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    /**
     *
     * @param container ViewPager
     * @param position 对应当前的页面
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView=new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        //联网请求图片
        ResultBeanData.ResultEntity.ActInfoEntity actInfoEntity = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + actInfoEntity.getIcon_url()).into(imageView);

        //添加到容器中
        container.addView(imageView);

        //设置点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"position="+position,Toast.LENGTH_SHORT).show();
            }
        });

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
