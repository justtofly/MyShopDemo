package com.myshopdemo.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myshopdemo.home.bean.GoodsBean;
import com.myshopdemo.utils.CacheUtils;
import com.myshopdemo.view.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/5 0005 17:22
 * 作用	     购物车存储器
 * 单例模式（在全局只得到一个实例的对象）
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class CartStorage {
    private static final String JSON_CART = "gouwuche";
    private static CartStorage instance;
    private final  Context     mContext;

    //SparseArray的性能优于HashMap
    private SparseArray<GoodsBean> mSparseArray;
    private List<GoodsBean>        mAllData;

    private CartStorage(Context context) {
        this.mContext = context;

        //把之前存储的数据读取
        mSparseArray = new SparseArray<>(100);

        listToSparseArray();
    }

    /**
     * 从本地读取的数据加入到SparseArray中
     */
    private void listToSparseArray() {
        List<GoodsBean> goodsBeanList = getAllData();

        //把list数据转换成SparseArray
        for (int i = 0; i < goodsBeanList.size(); i++) {
            GoodsBean goodsBean=goodsBeanList.get(i);
            mSparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        }
    }

    /**
     * 得到购物车实例
     * @return
     */
    public static CartStorage getInstance() {
        if (instance == null) {
            instance = new CartStorage(MyApplication.getcoContext());
        }
        return instance;
    }

    /**
     * 添加数据
     * @param goodsBean
     */
    public void addDate(GoodsBean goodsBean){
        //1.添加到内存中SparseArray
        //如果当前数据已经存在，就修改成number递增
        GoodsBean tempData=mSparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if (tempData!=null){
            //内存中有了这条数据
            tempData.setNumber(tempData.getNumber()+1);
        }else {
            tempData=goodsBean;
        }

        //同步到内存中
        mSparseArray.put(Integer.parseInt(tempData.getProduct_id()),tempData);

        //2.同步到本地
        saveLocal();
    }

    public void updateData(GoodsBean goodsBean){
        //内存中更新
        mSparseArray.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);

        //同步到本地
        saveLocal();
    }
    /**
     * 保存数据到本地
     */
    public void saveLocal() {
        //1.SparseArray转换成list
        List<GoodsBean> goodsBeanList=sparseToList();
        //2.使用Gson把列表转换成String类型
        String json=new Gson().toJson(goodsBeanList);
        //3.把String数据保存
        CacheUtils.saveString(mContext, JSON_CART, json);
    }

    private List<GoodsBean> sparseToList() {
        List<GoodsBean> goodsBeanList=new ArrayList<>();
        for (int i = 0; i < mSparseArray.size(); i++) {
            GoodsBean goodsBean=mSparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }

    /**
     * 获取本地所有数据
     * @return
     */
    public List<GoodsBean> getAllData() {
        List<GoodsBean> goodsBeanList=new ArrayList<>();
        //1.从本地获取
        String json= CacheUtils.getString(mContext, JSON_CART);
        //2.使用Gson转换成列表
        if (!TextUtils.isEmpty(json)){
            goodsBeanList=new Gson().fromJson(json,new TypeToken<List<GoodsBean>>(){}.getType());
        }
        return goodsBeanList;
    }
}
