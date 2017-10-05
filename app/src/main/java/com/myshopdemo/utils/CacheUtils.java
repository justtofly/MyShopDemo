package com.myshopdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/5 0005 17:38
 * 作用	      缓存工具类
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class CacheUtils {
    /**
     * 得到保存的String类型的数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context,String key){
        SharedPreferences sp=context.getSharedPreferences("gouwuche",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    /**
     * 保存String类型数据
     * @param context 上下文
     * @param key
     * @param value 保存的值
     */
    public static void saveString(Context context,String key,String value){
        SharedPreferences sp=context.getSharedPreferences("gouwuche",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
}
