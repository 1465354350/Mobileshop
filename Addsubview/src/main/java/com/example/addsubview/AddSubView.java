package com.example.addsubview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AddSubView extends LinearLayout implements View.OnClickListener {
    private final Context mContext;
    private ImageView iv_sub;
    private ImageView iv_add;
    private TextView tv_value;
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 5;
    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        View.inflate(context,R.layout.add_sub_view,this);
        iv_sub = (ImageView) findViewById(R.id.iv_sub);
        tv_value = (TextView) findViewById(R.id.tv_value);
        iv_add = (ImageView) findViewById(R.id.iv_add);

        int  value  = getValue();
        setValue(value);

        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_sub:
                subNumber();
                break;

            case R.id.iv_add:
                addNumebr();
                break;

        }


    }

    private void addNumebr() {
        if(value < maxValue){
            value ++;
        }
        setValue(value);

        if(onNumberChangeListener !=null){
            onNumberChangeListener.onNumberChange(value);
        }
    }


    private void subNumber() {
        if(value > minValue){
            value --;
        }
        setValue(value);

        if(onNumberChangeListener !=null){
            onNumberChangeListener.onNumberChange(value);
        }

    }

    public int getValue() {
       String valueStr =  tv_value.getText().toString().trim();
        if(!TextUtils.isEmpty(valueStr)){
            value = Integer.parseInt(valueStr) ;
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value+"");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }


    public interface OnNumberChangeListener{

        public void onNumberChange(int value);
    }

    private  OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }
}
