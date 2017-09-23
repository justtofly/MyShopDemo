package com.myshopdemo.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myshopdemo.R;
import com.myshopdemo.home.bean.ResultBeanData;
import com.myshopdemo.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/9/23 0023 14:42
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter {
    /**
     * 广告条幅类型
     */
    public static final  int BANNER    = 0;
    /**
     * HI
     */
    public static final  int CHANNEL   = 1;
    /**
     * 广告条幅类型
     */
    public static final  int ACT       = 2;
    /**
     * 秒杀类型
     */
    public static final  int SCKILL    = 3;
    /**
     * 热卖
     */
    public static final  int HOT       = 5;
    /**
     * 推荐类型
     */
    private static final int RECOMMEND = 4;

    private int currentType = BANNER;

    private Context                     mContext;
    private ResultBeanData.ResultEntity resultEntity;

    /**
     * 初始化布局
     */
    private LayoutInflater mLayoutInflater;

    public HomeFragmentAdapter(Context context, ResultBeanData.ResultEntity resultBean) {
        this.mContext = context;
        this.resultEntity = resultBean;
        //得到上下文布局文件，使用LayoutInflater来布局，也可以用View.inflater,但每次使用比较消耗性能。
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    /**
     * 相当于getView，创建ViewHolder部分代码
     * 创建ViewHolder
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==BANNER){//横幅广告
            return new BannerViewHolder(mContext,mLayoutInflater.inflate(R.layout.banner_viewpager,null));
        }else if (viewType==CHANNEL) {//频道
            return new ChannelViewHolder(mContext,mLayoutInflater.inflate(R.layout.gv_channel,null));
        }
        return null;
    }

    /**
     * 相当于getView中的绑定数据模块
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==BANNER){
            //强转过后相当于得到它的实例
            BannerViewHolder bannerViewHolder= (BannerViewHolder) holder;
            bannerViewHolder.setData(resultEntity.getBanner_info());
        }else if (getItemViewType(position)==CHANNEL){
            ChannelViewHolder channelViewHolder= (ChannelViewHolder) holder;
            channelViewHolder.setData(resultEntity.getChannel_info());
        }
    }

    /**
     * 频道的ViewHolder
     */
    class ChannelViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter channelAdapter;
        public ChannelViewHolder(Context context, View itemView) {
            super(itemView);
            this.mContext=context;
            gv_channel= (GridView) itemView.findViewById(R.id.gv_channel);

            //设置item的点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position="+position,Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultEntity.ChannelInfoEntity> channel_info) {
            //得到数据
            //设置GridView的适配器
            channelAdapter = new ChannelAdapter(mContext, channel_info);
            gv_channel.setAdapter(channelAdapter);
        }
    }

    /**
     * 横幅广告的ViewHolder
     */
    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        //声明控件
        private Banner banner;

        public BannerViewHolder(Context mContext,View itemView) {
            super(itemView);
            this.mContext=mContext;
            //实例化操作
            this.banner= (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultEntity.BannerInfoEntity> banner_info) {
            //设置Banner的数据

            //得到图片地址
            List<String> imagesUrl=new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imgeUrl=banner_info.get(i).getImage();
                imagesUrl.add(imgeUrl);
            }
            banner.setImages(imagesUrl).setImageLoader(new GlideImageLoader()).start();

            //设置item的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext,"position=="+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(Constants.BASE_URL_IMAGE+path).into(imageView);

            //Picasso 加载图片简单用法
            //Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
//            Uri uri = Uri.parse((String) path);
//            imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        /*@Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
            return simpleDraweeView;
        }*/
    }

    /**
     * 得到类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType=BANNER;
                break;
            case CHANNEL:
                currentType=CHANNEL;
                break;
            case ACT:
                currentType=ACT;
                break;
            case SCKILL:
                currentType=SCKILL;
                break;
            case RECOMMEND:
                currentType=RECOMMEND;
                break;
            case HOT:
                currentType=HOT;
                break;
        }

        return currentType;
    }

    /**
     * 一共有多少个item
     * @return
     */
    @Override
    public int getItemCount() {
        //开发过程中，从1-6
        return 2;
    }
}
