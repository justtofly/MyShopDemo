package com.myshopdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myshopdemo.R;
import com.myshopdemo.home.bean.GoodsBean;
import com.myshopdemo.shoppingcart.utils.CartStorage;
import com.myshopdemo.utils.Constants;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/1 0001 18:09
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class GoodsInfoActivity extends Activity implements View.OnClickListener {
    private ImageButton  ibGoodInfoBack;
    private ImageButton  ibGoodInfoMore;
    private ImageView    ivGoodInfoImage;
    private TextView     tvGoodInfoName;
    private TextView     tvGoodInfoDesc;
    private TextView     tvGoodInfoPrice;
    private TextView     tvGoodInfoStore;
    private TextView     tvGoodInfoStyle;
    private WebView      wbGoodMore;
    private LinearLayout llGoodsRoot;
    private TextView     tvGoodInfoCallcenter;
    private TextView     tvGoodInfoCollection;
    private TextView     tvGoodInfoCart;
    private Button       btnGoodInfoAddcart;

    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;

    private GoodsBean mGoodsBean;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-05 15:00:16 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ibGoodInfoBack = (ImageButton) findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = (ImageButton) findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = (TextView)findViewById( R.id.tv_good_info_store );
        tvGoodInfoStyle = (TextView)findViewById( R.id.tv_good_info_style );
        wbGoodMore = (WebView)findViewById( R.id.wb_good_more );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );
        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );
        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );

        tv_more_share = (TextView)findViewById( R.id.tv_more_share );
        tv_more_search = (TextView)findViewById( R.id.tv_more_search );
        tv_more_home = (TextView)findViewById( R.id.tv_more_home );

        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );
        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);

        tv_more_share.setOnClickListener(this);
        tv_more_search.setOnClickListener(this);
        tv_more_home.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-10-05 15:00:16 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {
            // Handle clicks for ibGoodInfoBack
            finish();
        } else if ( v == ibGoodInfoMore ) {
            // Handle clicks for ibGoodInfoMore
            Toast.makeText(this,"更多",Toast.LENGTH_SHORT).show();
        } else if ( v == btnGoodInfoAddcart ) {
            // Handle clicks for btnGoodInfoAddcart
//            Toast.makeText(this,"加入购物车",Toast.LENGTH_SHORT).show();
            CartStorage.getInstance().addDate(mGoodsBean);
            Toast.makeText(this, "成功添加到了购物车", Toast.LENGTH_SHORT).show();
            Log.e("TAG","添加的商品：mGoodsBean=="+mGoodsBean);
        }else if(v==tvGoodInfoCallcenter){
            Toast.makeText(this,"联系客服",Toast.LENGTH_SHORT).show();
        }else if(v==tvGoodInfoCollection){
            Toast.makeText(this,"收藏",Toast.LENGTH_SHORT).show();
        }else if(v==tvGoodInfoCart){
            Toast.makeText(this,"购物车",Toast.LENGTH_SHORT).show();
        }else if(v==tv_more_share){
            Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
        }else if(v==tv_more_search){
            Toast.makeText(this,"搜索",Toast.LENGTH_SHORT).show();
        }else if(v==tv_more_home){
            Toast.makeText(this,"回到主页",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        //初始化
        findViews();

        //接收数据
        mGoodsBean= (GoodsBean) getIntent().getSerializableExtra("goodsBean");
        if (mGoodsBean!=null){
//            Toast.makeText(this,"goodsBean=="+mGoodsBean.toString(),Toast.LENGTH_SHORT).show();
            setDataForView(mGoodsBean);
        }
    }

    private void setDataForView(GoodsBean goodsBean) {
        //设置图片,使用Glide加载图片
        Glide.with(this).load(Constants.BASE_URL_IMAGE+mGoodsBean.getFigure()).into(ivGoodInfoImage);

        //设置文本
        tvGoodInfoName.setText(goodsBean.getName());

        //设置价格
        tvGoodInfoPrice.setText(goodsBean.getCover_price());

        setWebViewData(goodsBean.getProduct_id());
    }

    //webview数据的设置加载H5页面,java与javascript的互调
    private void setWebViewData(String product_id) {
        if (product_id!=null){
            wbGoodMore.loadUrl("http://www.baidu.com");
            //设置支持JavaScript
            WebSettings webSettings=wbGoodMore.getSettings();
            webSettings.setUseWideViewPort(true);//支持双击页面变大变小
            webSettings.setJavaScriptEnabled(true);//设置支持javascript

            //优先使用缓存
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

            wbGoodMore.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                /*@Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        view.loadUrl(request.getUrl().toString());
                    }
//                    return super.shouldOverrideUrlLoading(view, url);
                    return true;
                }*/
            });
        }
    }
}
