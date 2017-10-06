package com.myshopdemo.shoppingcart.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myshopdemo.R;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/5 0005 18:52
 * 作用	      自定义控件
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class AddSubView extends LinearLayout implements View.OnClickListener {

    private final Context   mContext;
    //声明控件对象
    private       ImageView iv_sub;
    private       ImageView iv_add;
    private       TextView  tv_value;

    /**
     * 商品数量初始值
     */
    private int value    = 1;
    /**
     * 商品数量最小值
     */
    private int minValue = 1;
    /**
     * 商品数量最大值
     */
    private int maxValue = 5;

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    //把tv_value封装到value中
    public int getValue() {
        String valueStr = tv_value.getText().toString().trim();
        if (!TextUtils.isEmpty(valueStr)) {
            value = Integer.parseInt(valueStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value + "");
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;

        //把布局文件实例化，并且加载到AddSubView类中
        View.inflate(context, R.layout.add_sub_view,this);

        //实例化控件
        iv_sub= (ImageView) findViewById(R.id.iv_sub);
        iv_add= (ImageView) findViewById(R.id.iv_add);
        tv_value= (TextView) findViewById(R.id.tv_value);

        int value=getValue();//这是数量

        //设置点击事件
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_sub:
                //减
                subNumber();
                break;
            case R.id.iv_add:
                //加
                addNumber();
                break;
        }

        //更新数据
        setValue(value);

        if (mOnNumberChangeListener!=null){
            //回调接口方法
            mOnNumberChangeListener.onNumberChange(value);
        }
    }

    private void addNumber() {
        if(value<maxValue){
            value++;
        }
    }

    private void subNumber() {
        if (value>minValue){
            value--;
        }

    }

    /**
     * 当数据发生变化的时候回调
     */
    public interface OnNumberChangeListener{
        /**
         * 当数据发生变化的时候回调这个方法
         * @param value
         */
        public void onNumberChange(int value);
    }
    //创建接口对象
    private OnNumberChangeListener mOnNumberChangeListener;

    /**
     * 设置数据变化的监听
     * 定义了接口之后，由外界来实例化，回传接口对象
     * @param onNumberChangeListener
     */
    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener){
        this.mOnNumberChangeListener=onNumberChangeListener;
    }
}
