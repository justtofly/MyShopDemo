package com.myshopdemo.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.myshopdemo.R;
import com.myshopdemo.base.BaseFragment;
import com.myshopdemo.home.adapter.HomeFragmentAdapter;
import com.myshopdemo.home.bean.ResultBeanData;
import com.myshopdemo.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import okhttp3.Call;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/9/19 0019 9:17
 * 作用	      主页面
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class HomeFragment extends BaseFragment {

    //声明控件
    EditText     mEtHome;
    TextView     mTvHome;
    RecyclerView mRvHome;
    ImageView    mIvHome;

    /**
     * 返回的数据
     */
    private ResultBeanData.ResultEntity resultBean;

    @Override
    public View initView() {
        Log.e("TAG", "主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        //实例化控件
        mEtHome = (EditText) view.findViewById(R.id.et_home);
        mRvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        mTvHome = (TextView) view.findViewById(R.id.tv_home);
        mIvHome = (ImageView) view.findViewById(R.id.iv_home);
        //设置点击监听
        initListener();
        return view;
    }

    private void initListener() {
        //置顶的监听
        mIvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                //                Toast.makeText(mContext,"回到顶部",Toast.LENGTH_SHORT).show();
                //RecyclerView回到顶部
                mRvHome.scrollToPosition(0);
            }
        });
        mEtHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });
        mTvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "主页数据被初始化");

        getDataFromNet();
//        getDataFromNetByxUtils3();
    }

    /**
     * 联网请求主页数据
     */
    private void getDataFromNet() {
        String url = Constants.HOME_URL;
//        String url = "http://192.168.1.100:8080/smoke.jpg";   //请求成功

        Log.e("TAG", "url=" + url);
//        String url = "http://ask.csdn.net/questions/190852";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 当请求失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "请求失败：id==" + id + ",错误信息==" + e.getMessage());
                    }

                    /**
                     * 当请求成功的时候回调
                     * @param response 请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "请求成功：id==" + id + ",response==");
                        processData(response);
                    }
                });
    }

    /**
     * 解析数据
     * @param json
     */
    private void processData(String json) {
        //引入框架fastjson进行解析数据
        ResultBeanData resultBeanData= JSON.parseObject(json,ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if(resultBean!=null){
            //有数据
            //设置适配器
            HomeFragmentAdapter adapter=new HomeFragmentAdapter(mContext,resultBean);
            mRvHome.setAdapter(adapter);

            GridLayoutManager manager=new GridLayoutManager(mContext,1);
            //设置布局管理者
            mRvHome.setLayoutManager(manager);

            //设置跨度大小监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

                @Override
                public int getSpanSize(int position) {
                    if (position<=3){
                        //隐藏图标
                        mIvHome.setVisibility(View.GONE);
                    }else {
                        //显示图标
                        mIvHome.setVisibility(View.VISIBLE);
                    }
                    //这里只能返回1
                    return 1;
                }
            });
        }else {
            //没有数据
        }

        //打印一个log看是否解析成功
        Log.e("TAG","解析成功=="+resultBean.getHot_info().get(0).getName());
    }

    /**
     * 使用xUtils3进行网络请求
     */
    public void getDataFromNetByxUtils3() {
        String url1 = Constants.HOME_URL;
        String url2 = "https://www.baidu.com/";
        RequestParams requestParams = new RequestParams(url1);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","使用xUtils3联网请求成功：");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","使用xUtils3联网请求失败："+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG","使用xUtils3联网请求onCancelled");
            }

            @Override
            public void onFinished() {
                Log.e("TAG","使用xUtils3联网请求onFinished");
            }
        });
    }
}
