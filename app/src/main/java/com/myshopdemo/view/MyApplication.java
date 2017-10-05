package com.myshopdemo.view;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.x;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/9/19 0019 14:53
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class MyApplication extends Application {

    private static Context mContex;
    public static Context getcoContext(){
        return mContex;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContex=this;
        /**
         * 初始化OkHttpUtils
         */
        initOkhttpClient();
        /**
         * 初始化xUtils3
         */
        x.Ext.init(this);
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                        //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
